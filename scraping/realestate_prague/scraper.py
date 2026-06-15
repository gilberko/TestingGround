import argparse
import csv
import json
import re
import sys
import time
from pathlib import Path
from urllib.parse import parse_qs, urlparse

import requests

BASE_URL = "https://www.sreality.cz/api/cs/v2/estates"
DETAIL_URL = "https://www.sreality.cz/api/cs/v2/estates/{hash_id}"
LISTING_URL = "https://www.sreality.cz/detail/{tx_type}/byt/{room_type}/{locality}/{hash_id}"

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/124.0.0.0 Safari/537.36"
    ),
    "Accept": "application/json",
    "Referer": "https://www.sreality.cz/",
}

DETAIL_SLEEP = 0.3
DEFAULT_PER_PAGE = 60
DEFAULT_MAX_RECORDS = 100
DEFAULT_OUTPUT = "sreality_results.csv"
DISTRICT_OFFSET = 5000

CSV_COLUMNS = ["link", "id", "price_czk", "size_sqm", "floor", "rooms", "district_num", "neighborhood", "date_updated", "Buy Or Sell", "seller_name"]

_ROOM_RE = re.compile(r"(\d+\+(?:kk|\d+))", re.IGNORECASE)
_SIZE_RE = re.compile(r"(\d+)\s*m²", re.IGNORECASE)
_FLOOR_NUM_RE = re.compile(r"(\d+)")
_LOKALITA_RE = re.compile(r"Praha\s+(\d+)\s*[-–]\s*(.+)", re.IGNORECASE)
_PRAHA_DISTRICT_RE = re.compile(r"/praha-(\d+)(?:\b|-)", re.IGNORECASE)
_MAKLERI_HREF_RE = re.compile(r'href=["\']([^"\']*makleri[^"\']*)["\']', re.IGNORECASE)
_TITLE_RE = re.compile(r"<title[^>]*>(.*?)</title>", re.DOTALL | re.IGNORECASE)


def resolve_output_path(filename):
    """Return filename unchanged if it doesn't exist, otherwise bla.csv -> bla_1.csv -> bla_2.csv ..."""
    p = Path(filename)
    if not p.exists():
        return str(p)
    i = 1
    while True:
        candidate = p.parent / f"{p.stem}_{i}{p.suffix}"
        if not candidate.exists():
            return str(candidate)
        i += 1


def build_parser():
    parser = argparse.ArgumentParser(
        description="Scrape apartment listings from sreality.cz and export to CSV."
    )
    parser.add_argument(
        "--districts", nargs="+", type=int, required=False, default=[], metavar="N",
        help="Prague district numbers, e.g. --districts 2 3 5",
    )
    parser.add_argument(
        "--region-url", dest="region_url", default=None, metavar="URL",
        help=(
            "Fetch a sreality.cz search URL, parse its breadcrumb, "
            "and print the extracted region name(s) and region-id(s). "
            "Exits after printing; does not run the API scraper."
        ),
    )
    parser.add_argument(
        "--min-size", type=float, default=None, metavar="SQM",
        help="Minimum usable area in m²",
    )
    parser.add_argument(
        "--max-size", type=float, default=None, metavar="SQM",
        help="Maximum usable area in m² (filtered client-side)",
    )
    parser.add_argument(
        "--min-price", type=int, default=None, metavar="CZK",
        help="Minimum price in CZK",
    )
    parser.add_argument(
        "--max-price", type=int, default=None, metavar="CZK",
        help="Maximum price in CZK",
    )
    parser.add_argument(
        "--type", dest="listing_type", choices=["buy", "rent"], default="buy",
        help="Listing type: buy (prodej) or rent (pronajem). Default: buy",
    )
    parser.add_argument(
        "--max-records", type=int, default=DEFAULT_MAX_RECORDS, metavar="N",
        help=f"Maximum total results to collect (default: {DEFAULT_MAX_RECORDS})",
    )
    parser.add_argument(
        "--file", dest="file", default=DEFAULT_OUTPUT, metavar="FILE",
        help=f"Output CSV filename (default: {DEFAULT_OUTPUT}). Auto-increments if file already exists.",
    )
    parser.add_argument(
        "--no-detail", action="store_true",
        help="Skip fetching detail pages (faster, but floor and date_updated will be empty)",
    )
    return parser


def build_params(args):
    """Returns a list of (key, value) tuples so repeated locality_district_id keys work correctly."""
    category_type = 1 if args.listing_type == "buy" else 2
    params = [
        ("category_main_cb", 1),
        ("category_type_cb", category_type),
        ("per_page", DEFAULT_PER_PAGE),
    ]
    for district in args.districts:
        params.append(("locality_district_id", DISTRICT_OFFSET + district))
    if args.min_size is not None:
        params.append(("usable_area", int(args.min_size)))
    if args.min_price is not None:
        params.append(("price_min", args.min_price))
    if args.max_price is not None:
        params.append(("price_max", args.max_price))
    return params


def fetch_page(session, base_params, page):
    params = list(base_params) + [("page", page)]
    resp = session.get(BASE_URL, params=params, timeout=15)
    resp.raise_for_status()
    return resp.json()


def fetch_json_detail(session, hash_id):
    """Fetches the JSON detail API for an estate. Returns the data dict or {} on failure."""
    try:
        url = DETAIL_URL.format(hash_id=hash_id)
        resp = session.get(url, timeout=15)
        resp.raise_for_status()
        return resp.json()
    except Exception as exc:
        print(f"  [warn] Detail fetch failed for hash_id={hash_id}: {exc}", file=sys.stderr)
        return {}


def parse_room_type(name):
    """Extracts room designation like '3+kk' or '2+1' from the estate name string."""
    m = _ROOM_RE.search(name or "")
    return m.group(1).lower() if m else ""


def parse_floor(floor_str):
    """
    Extracts floor number from a Czech floor description.
    Examples:
      '5. podlaží z celkem 5 včetně 1 podzemního' -> '5'
      'přízemí' -> '0'
      'suterén' -> '-1'
    """
    if not floor_str:
        return ""
    lower = floor_str.strip().lower()
    if lower.startswith("přízemí"):
        return "0"
    if lower.startswith("suterén"):
        return "-1"
    m = _FLOOR_NUM_RE.search(floor_str)
    return m.group(1) if m else ""


def parse_lokalita(value):
    """
    Parses a Czech locality string like 'Praha 5 - Smíchov'.
    Returns (district_num, neighborhood) strings, or ("", "") if not matched.
    """
    m = _LOKALITA_RE.match(value.strip())
    if m:
        return m.group(1), m.group(2).strip()
    return "", ""


def neighborhood_from_slug(seo):
    """
    Extracts a best-effort neighborhood name from the seo.locality slug.
    e.g. 'smichov-siklove' -> 'smichov'
    Skips leading 'praha' segments so 'praha-smichov-...' still yields 'smichov'.
    """
    slug = seo.get("locality", "")
    for part in slug.split("-"):
        if part and part.lower() != "praha" and not part.isdigit():
            return part
    return ""


def district_from_slug(seo):
    """
    Extracts the Prague district number from the seo.locality slug.
    e.g. 'praha-5-smichov-siklove' -> '5'
    Returns "" if not found.
    """
    slug = seo.get("locality", "")
    parts = slug.lower().split("-")
    for i, part in enumerate(parts):
        if part == "praha" and i + 1 < len(parts) and parts[i + 1].isdigit():
            return parts[i + 1]
    return ""


def extract_district_from_html(html):
    """
    Scans the raw HTML for occurrences of /praha-<digits> and returns the
    first district number found as a string, or "" if none.
    """
    m = _PRAHA_DISTRICT_RE.search(html)
    return m.group(1) if m else ""


def fetch_seller_name(session, html):
    """
    Finds the first href containing 'makleri' in the HTML, fetches that page,
    and returns the <title> text as the seller name. Returns "" on failure.
    """
    m = _MAKLERI_HREF_RE.search(html)
    if not m:
        return ""
    href = m.group(1)
    url = href if href.startswith("http") else "https://www.sreality.cz" + href
    try:
        time.sleep(DETAIL_SLEEP)
        resp = session.get(url, headers={"Accept": "text/html"}, timeout=15)
        resp.raise_for_status()
        tm = _TITLE_RE.search(resp.text)
        return tm.group(1).strip() if tm else ""
    except Exception as exc:
        print(f"  [warn] Seller fetch failed for {url}: {exc}", file=sys.stderr)
        return ""


def build_listing_url(estate, room_type):
    """Constructs the human-readable sreality.cz listing URL."""
    try:
        hash_id = estate["hash_id"]
        seo = estate.get("seo", {})
        locality_slug = seo.get("locality", "")
        cat_type = seo.get("category_type_cb", 1)
        tx_type = "prodej" if cat_type == 1 else "pronajem"
        room_slug = room_type if room_type else "byt"
        return LISTING_URL.format(
            tx_type=tx_type,
            room_type=room_slug,
            locality=locality_slug,
            hash_id=hash_id,
        )
    except (KeyError, TypeError):
        return ""


def extract_detail_fields(data):
    """
    Extracts detail fields from a JSON detail API response
    (https://www.sreality.cz/api/cs/v2/estates/{hash_id}).
    Returns {"floor", "date_updated", "district_num", "neighborhood", "seller_name"}.
    """
    result = {"floor": "", "date_updated": "", "district_num": "", "neighborhood": "", "seller_name": ""}

    # District: top-level locality_district_id (e.g. 5002 -> "2")
    raw_district_id = data.get("locality_district_id")
    if raw_district_id:
        result["district_num"] = str(raw_district_id - DISTRICT_OFFSET)

    # Neighborhood: locality.value is e.g. "Dittrichova, Praha 2 - Nové Město"
    loc_value = (data.get("locality") or {}).get("value", "")
    m = _LOKALITA_RE.search(loc_value)
    if m:
        if not result["district_num"]:
            result["district_num"] = m.group(1)
        result["neighborhood"] = m.group(2).strip()

    # Seller: _embedded.seller.user_name
    result["seller_name"] = (
        (data.get("_embedded") or {}).get("seller") or {}
    ).get("user_name", "")

    # Floor and date: items array with Czech labels
    for item in data.get("items", []):
        name = item.get("name", "")
        value = str(item.get("value", "")).strip()
        if name == "Podlaží" and not result["floor"]:
            result["floor"] = parse_floor(value)
        elif name == "Aktualizace" and not result["date_updated"]:
            result["date_updated"] = value

    return result


def process_estate(estate, session, args):
    """
    Transforms one raw estate dict into a CSV row dict.
    Returns None if the estate should be filtered out (e.g. exceeds max_size).
    """
    # Size: prefer structured field, fall back to parsing name
    raw_size = estate.get("usable_area")
    if raw_size:
        size_sqm = str(raw_size)
    else:
        m = _SIZE_RE.search(estate.get("name", ""))
        size_sqm = m.group(1) if m else ""

    # Client-side max_size filter
    if args.max_size is not None and size_sqm:
        try:
            if float(size_sqm) > args.max_size:
                return None
        except ValueError:
            pass

    hash_id = estate.get("hash_id", "")
    price = estate.get("price", "")
    room_type = parse_room_type(estate.get("name", ""))
    link = build_listing_url(estate, room_type)

    floor = ""
    date_updated = ""
    seller_name = ""

    # Fallback sources from the listing API response (no detail fetch needed)
    seo = estate.get("seo", {})
    neighborhood = neighborhood_from_slug(seo)
    raw_dist = estate.get("locality_district_id")
    district_num = str(raw_dist - DISTRICT_OFFSET) if raw_dist else district_from_slug(seo)

    detail_data = {}
    if not args.no_detail and hash_id:
        time.sleep(DETAIL_SLEEP)
        detail_data = fetch_json_detail(session, hash_id)
        fields = extract_detail_fields(detail_data)
        floor = fields["floor"]
        date_updated = fields["date_updated"]
        seller_name = fields["seller_name"]
        if fields["district_num"]:
            district_num = fields["district_num"]
        if fields["neighborhood"]:
            neighborhood = fields["neighborhood"]

    buy_or_sell = args.listing_type if args.listing_type else ""

    return {
        "link": link,
        "id": hash_id,
        "price_czk": price,
        "size_sqm": size_sqm,
        "floor": floor,
        "rooms": room_type,
        "district_num": district_num,
        "neighborhood": neighborhood,
        "date_updated": date_updated,
        "Buy Or Sell": buy_or_sell,
        "seller_name": seller_name,
    }


_JSONLD_RE = re.compile(
    r'<script[^>]+type=["\']application/ld\+json["\'][^>]*>(.*?)</script>',
    re.DOTALL | re.IGNORECASE,
)
_NEXT_DATA_RE = re.compile(
    r'<script[^>]+id=["\']__NEXT_DATA__["\'][^>]*>(.*?)</script>',
    re.DOTALL | re.IGNORECASE,
)


def parse_region_from_html(html):
    """
    Scans all JSON-LD blocks in an HTML page for a BreadcrumbList.
    Returns a list of dicts {"district": str, "name": str, "region_id": str}
    for every breadcrumb item whose URL contains a region-id query parameter.
    Returns an empty list if nothing is found.
    """
    results = []
    for block in _JSONLD_RE.findall(html):
        try:
            obj = json.loads(block.strip())
        except ValueError:
            continue
        if obj.get("@type") != "BreadcrumbList":
            continue
        items = obj.get("itemListElement", [])
        for i, item in enumerate(items):
            url = item.get("item", "")
            qs = parse_qs(urlparse(url).query)
            if "region-id" not in qs:
                continue
            region_id = qs["region-id"][0]
            region_name = item.get("name", "")
            district_name = items[i - 1].get("name", "") if i > 0 else ""
            results.append({"district": district_name, "name": region_name, "region_id": region_id})
    return results


def parse_next_data(html):
    """
    Extracts the __NEXT_DATA__ JSON block from an HTML page and returns
    the estate data dict, or {} if not found or on parse error.
    Handles both the legacy props.pageProps.data layout and the current
    dehydratedState.queries layout where queryKey[0] == 'estate'.
    """
    m = _NEXT_DATA_RE.search(html)
    if not m:
        return {}
    try:
        obj = json.loads(m.group(1).strip())
        pp = obj["props"]["pageProps"]
        # Legacy layout
        if "data" in pp:
            return pp["data"]
        # Current layout: estate data lives in dehydratedState queries
        for q in pp.get("dehydratedState", {}).get("queries", []):
            qk = q.get("queryKey", [])
            if isinstance(qk, list) and qk and qk[0] == "estate":
                return q["state"]["data"]
        return {}
    except (ValueError, KeyError, TypeError):
        return {}


def fetch_region_info(session, url):
    """Fetches a sreality.cz HTML page and extracts region info from its breadcrumb."""
    try:
        resp = session.get(url, headers={"Accept": "text/html"}, timeout=15)
        resp.raise_for_status()
        return parse_region_from_html(resp.text)
    except Exception as exc:
        print(f"[warn] Could not fetch region info from {url}: {exc}", file=sys.stderr)
        return []


def main():
    parser = build_parser()
    args = parser.parse_args()

    session = requests.Session()
    session.headers.update(HEADERS)

    if args.region_url:
        infos = fetch_region_info(session, args.region_url)
        if not infos:
            print("No region-id found in page breadcrumb.", file=sys.stderr)
            sys.exit(1)
        for info in infos:
            print(f"District : {info['district']}")
            print(f"Name     : {info['name']}")
            print(f"Region ID: {info['region_id']}")
        sys.exit(0)

    if not args.districts:
        parser.error("--districts is required when not using --region-url")

    base_params = build_params(args)

    results = []
    page = 1
    total_available = None

    print(f"Scraping sreality.cz | type={args.listing_type} | districts={args.districts}")
    if args.min_price or args.max_price:
        print(f"  Price: {args.min_price or '-'} - {args.max_price or '-'} CZK")
    if args.min_size or args.max_size:
        print(f"  Size: {args.min_size or '-'} - {args.max_size or '-'} m²")
    print(f"  Max records: {args.max_records} | Detail pages: {'no' if args.no_detail else 'yes'}")
    print()

    while len(results) < args.max_records:
        print(f"  Fetching page {page}...", end=" ", flush=True)
        try:
            data = fetch_page(session, base_params, page)
        except Exception as exc:
            print(f"\n[error] Page {page} failed: {exc}", file=sys.stderr)
            break

        if total_available is None:
            total_available = data.get("result_size", 0)
            print(f"(server total: {total_available})")

        estates = data.get("_embedded", {}).get("estates", [])
        if not estates:
            print("No more results.")
            break

        print(f"{len(estates)} listings on page.")

        for estate in estates:
            if len(results) >= args.max_records:
                break
            row = process_estate(estate, session, args)
            if row is not None:
                results.append(row)

        # Stop if we've seen all available server-side results
        if page * DEFAULT_PER_PAGE >= (total_available or 0):
            break

        page += 1

    output_path = resolve_output_path(args.file)
    print(f"\nWriting {len(results)} records to {output_path}...")
    with open(output_path, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=CSV_COLUMNS)
        writer.writeheader()
        writer.writerows(results)

    print("Done.")


if __name__ == "__main__":
    main()
