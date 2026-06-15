import argparse
import csv
import re
import sys
import time
from pathlib import Path

import requests
from bs4 import BeautifulSoup

BASE_URL = "https://www.imovirtual.com"
SEARCH_URL = BASE_URL + "/pt/resultados/{action}/apartamento/{city}/"
DETAIL_SLEEP = 0.3
LISTING_SLEEP = 0.5
MAX_RECORDS = 100
PER_PAGE = 36
DEFAULT_OUTPUT = "imovirtual_results.csv"

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/124.0.0.0 Safari/537.36"
    ),
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language": "pt-PT,pt;q=0.9,en;q=0.8",
    "Referer": BASE_URL + "/",
}

CSV_COLUMNS = ["link", "price_eur", "region", "sqm", "floor", "elevator", "rooms", "summary"]

_FLOOR_NUM_RE = re.compile(r"(\d+)")
_PRICE_DIGITS_RE = re.compile(r"[\d\s]+")
_EURO_RE = re.compile(r"([\d\s]+)\s*€")


def resolve_output_path(filename):
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
        description="Scrape apartment listings from imovirtual.com and export to CSV."
    )
    parser.add_argument(
        "--action", choices=["buy", "rent"], default="buy",
        help="buy (comprar) or rent (arrendar). Default: buy",
    )
    parser.add_argument(
        "--city", choices=["porto", "lisbon"], default="porto",
        help="City to search: porto or lisbon. Default: porto",
    )
    parser.add_argument("--min-sqm", type=float, default=None, metavar="SQM",
                        help="Minimum area in m²")
    parser.add_argument("--max-sqm", type=float, default=None, metavar="SQM",
                        help="Maximum area in m²")
    parser.add_argument("--min-price", type=int, default=None, metavar="EUR",
                        help="Minimum price in euros")
    parser.add_argument("--max-price", type=int, default=None, metavar="EUR",
                        help="Maximum price in euros")
    parser.add_argument("--file", dest="file", default=DEFAULT_OUTPUT, metavar="FILE",
                        help=f"Output CSV filename (default: {DEFAULT_OUTPUT}). Auto-increments if file exists.")
    return parser


def build_search_url(args, page):
    action = "comprar" if args.action == "buy" else "arrendar"
    city = "lisboa" if args.city == "lisbon" else "porto"
    url = SEARCH_URL.format(action=action, city=city)
    params = {"page": page}
    if args.min_price is not None:
        params["priceMin"] = args.min_price
    if args.max_price is not None:
        params["priceMax"] = args.max_price
    if args.min_sqm is not None:
        params["areaMin"] = int(args.min_sqm)
    if args.max_sqm is not None:
        params["areaMax"] = int(args.max_sqm)
    req = requests.Request("GET", url, params=params)
    return req.prepare().url


def collect_links(session, args):
    links = []
    seen = set()
    page = 1
    while len(links) < MAX_RECORDS:
        url = build_search_url(args, page)
        print(f"  Fetching listing page {page}: {url}", flush=True)
        try:
            resp = session.get(url, timeout=20)
            resp.raise_for_status()
        except Exception as exc:
            print(f"  [warn] Listing page {page} failed: {exc}", file=sys.stderr)
            break
        soup = BeautifulSoup(resp.text, "html.parser")
        found = 0
        for tag in soup.find_all("a", href=True):
            href = tag["href"]
            if "/pt/anuncio/" in href:
                full = BASE_URL + href if href.startswith("/") else href
                # Strip query strings from listing links
                full = full.split("?")[0]
                if full not in seen:
                    seen.add(full)
                    links.append(full)
                    found += 1
                    if len(links) >= MAX_RECORDS:
                        break
        print(f"    Found {found} new links (total: {len(links)})")
        if found == 0:
            print("  No new links found, stopping.")
            break
        page += 1
        time.sleep(LISTING_SLEEP)
    return links[:MAX_RECORDS]


def parse_price(text):
    m = _EURO_RE.search(text or "")
    if not m:
        return ""
    digits = re.sub(r"\s", "", m.group(1))
    return digits if digits.isdigit() else ""


def parse_floor(text):
    if not text:
        return ""
    lower = text.strip().lower()
    if "sem informação" in lower or "sem info" in lower:
        return ""
    if "rés do chão" in lower or "res do chao" in lower:
        return "0"
    if "cave" in lower or "sub" in lower:
        return "-1"
    m = _FLOOR_NUM_RE.search(text)
    return m.group(1) if m else text.strip()


def find_feature_value(soup, label_text):
    """
    Searches for a label containing label_text and returns the associated value text.
    Handles multiple common HTML patterns used by imovirtual.
    """
    label_lower = label_text.lower()

    # Pattern 1: <dt> / <dd> definition list pairs
    for dt in soup.find_all("dt"):
        if label_lower in (dt.get_text() or "").lower():
            dd = dt.find_next_sibling("dd")
            if dd:
                return dd.get_text(strip=True)

    # Pattern 2: <li> or <div> containing a <span>/<p> label + sibling value
    for elem in soup.find_all(["li", "div", "p", "span"]):
        text = elem.get_text(separator=" ", strip=True)
        if label_lower + ":" in text.lower() or text.lower().startswith(label_lower):
            # Try to extract value after the colon
            parts = re.split(r":\s*", text, maxsplit=1)
            if len(parts) == 2 and parts[0].strip().lower() == label_lower:
                val = parts[1].strip()
                if val:
                    return val
            # Try child elements: first child is label, second is value
            children = [c for c in elem.children if hasattr(c, "get_text")]
            if len(children) >= 2:
                first_text = children[0].get_text(strip=True).lower()
                if label_lower in first_text:
                    return children[1].get_text(strip=True)

    return ""


def scrape_detail(session, url):
    try:
        resp = session.get(url, timeout=20)
        resp.raise_for_status()
    except Exception as exc:
        print(f"  [warn] Detail fetch failed for {url}: {exc}", file=sys.stderr)
        return None

    soup = BeautifulSoup(resp.text, "html.parser")

    # Price: look for strongest euro value on the page
    price_eur = ""
    for strong in soup.find_all("strong"):
        t = strong.get_text(strip=True)
        if "€" in t:
            price_eur = parse_price(t)
            if price_eur:
                break
    if not price_eur:
        # Fallback: any element with a large euro amount
        for elem in soup.find_all(string=_EURO_RE):
            price_eur = parse_price(str(elem))
            if price_eur and len(price_eur) >= 4:
                break

    # Feature fields
    sqm_raw = find_feature_value(soup, "Área")
    if sqm_raw:
        # Handle Portuguese decimal comma: "41,4 m²" → "41"
        m_sqm = re.match(r"(\d+)", sqm_raw.strip().replace(",", "."))
        sqm = m_sqm.group(1) if m_sqm else ""
    else:
        sqm = ""

    floor_raw = find_feature_value(soup, "Andar")
    floor = parse_floor(floor_raw)

    elevator_raw = find_feature_value(soup, "Elevador")
    elevator = elevator_raw.lower() if elevator_raw else ""
    if elevator in ("sim", "não", "nao", "yes", "no"):
        pass  # keep as-is
    elif "sim" in elevator or "yes" in elevator:
        elevator = "sim"
    elif "não" in elevator or "nao" in elevator or "no" in elevator:
        elevator = "não"

    rooms_raw = find_feature_value(soup, "Tipologia")
    rooms = rooms_raw.upper() if rooms_raw else ""

    # Region: the address link anchors to the map section
    region = ""
    map_link = soup.find("a", href="#map")
    if map_link:
        region = map_link.get_text(separator=", ", strip=True)
    if not region:
        for sel in ["address", "[data-testid*='address']", "[class*='address']", "[class*='location']"]:
            elem = soup.select_one(sel)
            if elem:
                text = elem.get_text(separator=", ", strip=True)
                if text and len(text) > 5:
                    region = text
                    break

    # Summary: description is JS-loaded; use h1 title as informative fallback
    _LOGIN_PHRASES = ("inicie sessão", "crie uma conta", "iniciar sessão", "fazer login")
    summary = ""
    for sel in ["[data-testid*='description']", "[class*='description']", "[class*='descricao']",
                "article p", "section p"]:
        elems = soup.select(sel)
        for elem in elems:
            text = elem.get_text(separator=" ", strip=True)
            if len(text) > 80 and not any(p in text.lower() for p in _LOGIN_PHRASES):
                summary = text[:500]
                break
        if summary:
            break
    if not summary:
        h1 = soup.find("h1")
        if h1:
            summary = h1.get_text(strip=True)[:500]

    return {
        "link": url,
        "price_eur": price_eur,
        "region": region,
        "sqm": sqm,
        "floor": floor,
        "elevator": elevator,
        "rooms": rooms,
        "summary": summary,
    }


def main():
    parser = build_parser()
    args = parser.parse_args()

    session = requests.Session()
    session.headers.update(HEADERS)

    print(f"imovirtual.com scraper | action={args.action} | city={args.city}")
    filters = []
    if args.min_price or args.max_price:
        filters.append(f"price: {args.min_price or '-'} – {args.max_price or '-'} €")
    if args.min_sqm or args.max_sqm:
        filters.append(f"area: {args.min_sqm or '-'} – {args.max_sqm or '-'} m²")
    if filters:
        print(f"  Filters: {' | '.join(filters)}")
    print(f"  Max records: {MAX_RECORDS}")
    print()

    print("Phase 1: collecting listing links...")
    links = collect_links(session, args)
    print(f"  Collected {len(links)} links.\n")

    if not links:
        print("No listings found. Exiting.", file=sys.stderr)
        sys.exit(1)

    print("Phase 2: scraping detail pages...")
    results = []
    for i, url in enumerate(links, 1):
        print(f"  [{i}/{len(links)}] {url}", flush=True)
        time.sleep(DETAIL_SLEEP)
        row = scrape_detail(session, url)
        if row is not None:
            results.append(row)

    output_path = resolve_output_path(args.file)
    print(f"\nWriting {len(results)} records to {output_path}...")
    with open(output_path, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=CSV_COLUMNS)
        writer.writeheader()
        writer.writerows(results)

    print("Done.")


if __name__ == "__main__":
    main()
