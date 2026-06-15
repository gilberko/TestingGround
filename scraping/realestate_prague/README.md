# sreality.cz Prague Apartment Scraper

A Python script that searches for apartments in Prague on [sreality.cz](https://www.sreality.cz/) using their public JSON API and exports results to a CSV file.

## Requirements

```bash
pip install requests
```

## Usage

```bash
python scraper.py --districts <N> [N ...] [options]
```

`--districts` is the only required argument.

## Arguments

| Argument | Type | Default | Description |
|---|---|---|---|
| `--districts N [N ...]` | int(s) | *(required)* | Prague district numbers (e.g. `2 3 5` for Praha 2, Praha 3, Praha 5) |
| `--type buy\|rent` | string | `buy` | Listing type: `buy` (prodej) or `rent` (pronájem) |
| `--min-size SQM` | float | — | Minimum usable area in m² |
| `--max-size SQM` | float | — | Maximum usable area in m² |
| `--min-price CZK` | int | — | Minimum price in CZK |
| `--max-price CZK` | int | — | Maximum price in CZK |
| `--max-records N` | int | `100` | Maximum number of listings to collect |
| `--output FILE` | string | `sreality_results.csv` | Output CSV filename |
| `--no-detail` | flag | off | Skip fetching individual listing detail pages. Much faster, but `floor` and `date_updated` columns will be empty |

## CSV Output

The output CSV has the following columns:

| Column | Description |
|---|---|
| `link` | Direct URL to the listing on sreality.cz |
| `id` | Unique listing ID (hash_id) |
| `price_czk` | Price in CZK (total sale price or monthly rent) |
| `size_sqm` | Usable area in square metres |
| `floor` | Floor number (`0` = ground floor, `-1` = basement) |
| `rooms` | Room designation, e.g. `3+kk`, `2+1`, `4+1` |
| `date_updated` | Date the listing was last updated (as shown on site, may be in Czech e.g. `Dnes` = Today) |

## Examples

**Buy apartments in Prague 2 and 3, 50–90 m², up to 10 million CZK:**
```bash
python scraper.py --districts 2 3 --min-size 50 --max-size 90 --max-price 10000000
```

**Rent apartments in Prague 5, any size, up to 25,000 CZK/month, 50 results:**
```bash
python scraper.py --districts 5 --type rent --max-price 25000 --max-records 50
```

**Multiple districts, price range, custom output file:**
```bash
python scraper.py --districts 2 3 4 5 --min-price 4000000 --max-price 12000000 --output prague_central.csv
```

**Fast scan without detail pages (no floor/date):**
```bash
python scraper.py --districts 1 --type rent --no-detail --max-records 200 --output quick.csv
```

## Notes

- Without `--no-detail`, the script fetches a detail page for every listing to retrieve the floor number and update date. This adds ~0.3 seconds per listing to be polite to the server.
- `--max-size` is applied client-side (the API only supports a minimum size filter). Other filters are applied server-side.
- Districts map to Praha 1–22+. Common ones: Praha 1=1, Praha 2=2, Praha 3=3, Praha 4=4, Praha 5=5, Praha 6=6, etc.
- The `date_updated` field reflects when the listing was last updated on the site, not necessarily when it was first posted.
