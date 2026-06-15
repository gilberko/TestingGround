#!/usr/bin/env python3
import argparse
import csv
import hashlib
import re
import random
import time

from playwright.sync_api import sync_playwright, TimeoutError as PWTimeout
from playwright_stealth import Stealth

BASE_URL = "https://www.immowelt.de"
SEARCH_URL = "https://www.immowelt.de/suche/berlin/wohnungen/kaufen/"

MAX_LISTINGS = 250

# Immowelt card IDs follow the pattern "classified-card-<ID>".
CARD_SELECTOR = "[id^='classified-card-']"

BERLIN_REGIONS = [
    "Mitte", "Charlottenburg", "Wilmersdorf", "Friedrichshain", "Kreuzberg",
    "Pankow", "Prenzlauer Berg", "Neukölln", "Schöneberg", "Tempelhof",
    "Steglitz", "Zehlendorf", "Lichtenberg", "Spandau", "Wedding",
    "Moabit", "Köpenick", "Treptow", "Reinickendorf", "Marzahn",
    "Hellersdorf", "Weißensee", "Tiergarten",
]


def clean_number(text):
    if not text:
        return None
    text = text.replace(".", "").replace(",", ".")
    m = re.search(r"\d+(?:\.\d+)?", text)
    return float(m.group()) if m else None


def make_id(url):
    return hashlib.sha1(url.encode("utf-8")).hexdigest()[:12]


def parse_card(text, url):
    price = sqm = rooms = floor = None

    m = re.search(r"([\d.,]+)\s*€", text)
    if m:
        price = int(clean_number(m.group(1)) or 0)

    m = re.search(r"([\d.,]+)\s*m²", text)
    if m:
        sqm = clean_number(m.group(1))

    m = re.search(r"([\d.,]+)\s*(?:Zimmer|Zi\.)", text, re.I)
    if m:
        rooms = clean_number(m.group(1))

    m = re.search(r"(\d+)\.?\s*(?:Etage|OG|Stock)", text, re.I)
    if m:
        floor = int(m.group(1))

    region = next((r for r in BERLIN_REGIONS if r.lower() in text.lower()), None)

    return {
        "id": make_id(url),
        "url": url,
        "price_eur": price,
        "sqm": sqm,
        "rooms": rooms,
        "floor": floor,
        "region": region,
    }


def dismiss_consent(page):
    """Click the Usercentrics accept-all button (Shadow DOM) then remove the overlay."""
    try:
        page.evaluate("""() => {
            const host = document.getElementById('usercentrics-root');
            if (!host) return;
            if (host.shadowRoot) {
                const btn = host.shadowRoot.querySelector('button[data-testid="uc-accept-all-button"]');
                if (btn) btn.click();
            }
            host.remove();
        }""")
        # Also inject CSS to hide it if it reappears
        page.add_style_tag(content="#usercentrics-root { display: none !important; }")
    except Exception:
        pass
    page.wait_for_timeout(500)


def go_next_page(page):
    """Click the 'next page' arrow and wait for cards to swap out."""
    dismiss_consent(page)
    
    old_url = page.url
    # Record the first card's ID so we can detect when the set changes.
    first_card_id = page.evaluate(
        '() => document.querySelector("[id^=\'classified-card-\']")?.id || ""'
    )

    try:
        # Use JS click to bypass overlays.
        clicked = page.evaluate("""() => {
            const btn = [...document.querySelectorAll('button')].find(b => 
                b.getAttribute('aria-label')?.toLowerCase().includes('chste seite') ||
                b.innerText.includes('Nächste')
            );
            if (btn) {
                btn.scrollIntoView();
                btn.click();
                return true;
            }
            return false;
        }""")
        if not clicked:
            return False
    except Exception:
        return False

    # Wait for the URL to change or the first card to disappear.
    try:
        page.wait_for_function(
            f"(oldUrl, oldId) => window.location.href !== oldUrl || (oldId && !document.getElementById(oldId))",
            arg=[old_url, first_card_id],
            timeout=10000
        )
    except Exception:
        pass

    try:
        page.wait_for_selector(CARD_SELECTOR, timeout=10000)
        return True
    except Exception:
        return False


def scrape(headless=False):
    seen = set()

    with sync_playwright() as pw:
        browser = pw.chromium.launch(
            headless=headless,
            args=["--disable-blink-features=AutomationControlled"],
        )
        ctx = browser.new_context(
            user_agent=(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                "AppleWebKit/537.36 (KHTML, like Gecko) "
                "Chrome/124.0.0.0 Safari/537.36"
            ),
            viewport={"width": 1280, "height": 900},
            locale="de-DE",
        )
        page = ctx.new_page()

        # Mask headless-browser fingerprints so DataDome doesn't block paginated routes.
        Stealth().apply_stealth_sync(page)

        page.goto(SEARCH_URL, wait_until="domcontentloaded", timeout=30000)
        dismiss_consent(page)
        page.wait_for_timeout(2000)

        # Verify cards are present before starting the loop.
        try:
            page.wait_for_selector(CARD_SELECTOR, timeout=10000)
        except PWTimeout:
            print("ERROR: No listing cards detected on page 1.")
            print("This often happens if the bot was detected. Try running without --headless.")
            browser.close()
            return

        page_num = 0
        while len(seen) < MAX_LISTINGS:
            page_num += 1

            # Scroll down slowly to mimic human reading and ensure all lazy-loaded cards are present.
            page.evaluate("window.scrollTo(0, document.body.scrollHeight / 2)")
            page.wait_for_timeout(1000)
            page.evaluate("window.scrollTo(0, document.body.scrollHeight)")
            page.wait_for_timeout(1000)

            cards = page.locator(CARD_SELECTOR).all()
            page_new = 0

            for card in cards:
                try:
                    a = card.locator("a[href]").first
                    href = a.get_attribute("href", timeout=2000)
                    if not href:
                        continue
                    url = href if href.startswith("http") else BASE_URL + href
                    if url in seen:
                        continue

                    text = " ".join(card.inner_text().split())
                    item = parse_card(text, url)
                    seen.add(url)
                    page_new += 1
                    yield item

                    if len(seen) >= MAX_LISTINGS:
                        print(f"Reached {MAX_LISTINGS}-listing cap on page {page_num}.")
                        browser.close()
                        return
                except Exception:
                    continue

            print(f"Page {page_num}: +{page_new} new  (total {len(seen)})")

            if page_new == 0:
                print("No new listings on this page — done.")
                break

            if not go_next_page(page):
                print("Could not advance to next page — done. (Bot detection may have triggered)")
                break

        browser.close()


def matches(item, min_size=None, budget=None, keyword=None, rooms=None):
    if min_size is not None and (item["sqm"] is None or item["sqm"] < min_size):
        return False
    if budget is not None and (item["price_eur"] is None or item["price_eur"] > budget):
        return False
    if rooms is not None and (item["rooms"] is None or item["rooms"] < rooms):
        return False
    if keyword:
        if keyword.lower() not in (item["region"] or "").lower():
            return False
    return True


def main():
    p = argparse.ArgumentParser()
    p.add_argument("--min-size", type=float)
    p.add_argument("--budget", type=int)
    p.add_argument("--keyword")
    p.add_argument("--rooms", type=float)
    p.add_argument("--out", default="berlin_buy_listings.csv")
    p.add_argument("--headless", action="store_true", help="Run headless (may be blocked by anti-bot)")
    args = p.parse_args()

    rows = [
        item for item in scrape(headless=args.headless)
        if matches(item, args.min_size, args.budget, args.keyword, args.rooms)
    ]

    with open(args.out, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(
            f, fieldnames=["id", "url", "price_eur", "sqm", "rooms", "floor", "region"]
        )
        writer.writeheader()
        writer.writerows(rows)

    print(f"Saved {len(rows)} listings to {args.out}")


if __name__ == "__main__":
    main()
