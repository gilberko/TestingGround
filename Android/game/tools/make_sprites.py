"""Generates all pixel-art PNGs for Netta The Super Agent and writes assets/game.atlas.

Run from anywhere:  python tools/make_sprites.py
Everything is drawn at a small base resolution and upscaled with nearest-neighbour.
Player layers are grayscale so the game can colour them with the sprite tint.
"""
import os
from PIL import Image, ImageDraw

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(ROOT, "assets", "images")
ATLAS = os.path.join(ROOT, "assets", "game.atlas")
os.makedirs(OUT, exist_ok=True)

IMAGES = []        # image ids (file names without .png) in atlas
ANIMS = []         # (id, [image ids], fps, playback)


def save(img, name, scale):
    if scale != 1:
        img = img.resize((img.width * scale, img.height * scale), Image.NEAREST)
    img.save(os.path.join(OUT, name + ".png"))
    IMAGES.append(name)


def rect(d, r, c):
    x0, y0, x1, y1 = r
    d.rectangle([x0, y0, x1, y1], fill=c)


# ---------------------------------------------------------------- people ---
W_, S_, D_ = (255, 255, 255, 255), (196, 196, 196, 255), (140, 140, 140, 255)
POSES = ["stand", "walk1", "walk2", "jump", "climb1", "climb2", "duck", "shoot"]


def geo(pose):
    g = {"head": (8, 3), "torso": (7, 12, 16, 22),
         "arms": [(5, 12, 6, 20), (17, 12, 18, 20)],
         "hands": [(5, 21, 6, 22), (17, 21, 18, 22)],
         "legs": [(8, 23, 11, 32), (12, 23, 15, 32)],
         "shoes": [(8, 33, 12, 35), (12, 33, 16, 35)], "gun": None}
    if pose == "walk1":
        g["arms"] = [(4, 12, 6, 19), (17, 12, 19, 19)]
        g["hands"] = [(4, 20, 5, 21), (18, 20, 19, 21)]
        g["legs"] = [(8, 23, 11, 27), (6, 28, 9, 32), (12, 23, 15, 27), (14, 28, 17, 32)]
        g["shoes"] = [(5, 33, 10, 35), (14, 33, 19, 35)]
    elif pose == "walk2":
        g["arms"] = [(6, 12, 7, 20), (16, 12, 17, 20)]
        g["hands"] = [(6, 21, 7, 22), (16, 21, 17, 22)]
        g["legs"] = [(9, 23, 12, 32), (11, 23, 14, 32)]
        g["shoes"] = [(9, 33, 13, 35), (11, 33, 15, 35)]
    elif pose == "jump":
        g["head"] = (8, 2)
        g["torso"] = (7, 11, 16, 21)
        g["arms"] = [(4, 5, 6, 12), (17, 5, 19, 12)]
        g["hands"] = [(4, 3, 6, 4), (17, 3, 19, 4)]
        g["legs"] = [(8, 22, 11, 27), (12, 22, 15, 27), (7, 28, 10, 31), (13, 28, 16, 31)]
        g["shoes"] = [(6, 32, 10, 34), (13, 32, 17, 34)]
    elif pose == "climb1":
        g["arms"] = [(5, 4, 6, 12), (17, 8, 18, 12)]
        g["hands"] = [(5, 2, 6, 3), (17, 6, 18, 7)]
        g["legs"] = [(8, 23, 11, 29), (12, 23, 15, 32)]
        g["shoes"] = [(8, 30, 11, 32), (12, 33, 15, 35)]
    elif pose == "climb2":
        g["arms"] = [(5, 8, 6, 12), (17, 4, 18, 12)]
        g["hands"] = [(5, 6, 6, 7), (17, 2, 18, 3)]
        g["legs"] = [(8, 23, 11, 32), (12, 23, 15, 29)]
        g["shoes"] = [(8, 33, 11, 35), (12, 30, 15, 32)]
    elif pose == "duck":
        g["head"] = (9, 13)
        g["torso"] = (7, 22, 16, 29)
        g["arms"] = [(13, 23, 18, 25)]
        g["hands"] = [(19, 23, 20, 25)]
        g["legs"] = [(8, 30, 17, 32)]
        g["shoes"] = [(5, 33, 10, 35), (14, 33, 19, 35)]
    elif pose == "shoot":
        g["arms"] = [(5, 12, 6, 20), (16, 14, 20, 16)]
        g["hands"] = [(5, 21, 6, 22), (21, 14, 21, 16)]
        g["gun"] = (20, 12, 23, 14)
    return g


def draw_person(pose, p, layer=None, w=24, h=36):
    """p: dict of colours/options. layer: None = everything baked, else one of
    skin/suit/det/hair/acc rendered alone (tinted layers in grayscale)."""
    img = Image.new("RGBA", (w, h), (0, 0, 0, 0))
    d = ImageDraw.Draw(img)
    g = geo(pose)
    hx, hy = g["head"]
    female = p.get("body") == "f"

    def on(l):
        return layer is None or layer == l

    def col(role, shade=False):
        if layer in ("skin", "suit", "hair"):
            return S_ if shade else W_
        c = p[role]
        if shade:
            c = tuple(max(0, int(v * 0.78)) for v in c[:3]) + (255,)
        return c

    # --- skin: head, neck, hands
    if on("skin"):
        rect(d, (hx, hy, hx + 7, hy + 7), col("skin"))
        rect(d, (hx + 3, hy + 8, hx + 5, hy + 8), col("skin", True))
        rect(d, (hx + 7, hy + 4, hx + 7, hy + 5), col("skin", True))  # nose shade
        rect(d, (hx + 1, hy + 4, hx + 1, hy + 5), col("skin", True))  # ear
        for r in g["hands"]:
            rect(d, r, col("skin"))
    # --- suit: jacket/shirt body, sleeves, trousers
    if on("suit"):
        t = g["torso"]
        jacket = "jacket" if p.get("jacket") else "shirt"
        if layer == "suit" or p.get("jacket") or layer is None:
            c = col(jacket)
            if female:
                rect(d, (t[0], t[1], t[2], t[1] + 2), c)
                rect(d, (t[0] + 1, t[1] + 3, t[2] - 1, t[3] - 3), c)
                rect(d, (t[0], t[3] - 2, t[2], t[3]), c)
            else:
                rect(d, t, c)
            rect(d, (t[0], t[3], t[2], t[3]), col(jacket, True))
            for r in g["arms"]:
                rect(d, r, c)
        for r in g["legs"]:
            rect(d, r, col("pants") if layer != "suit" else S_)
    # --- details: shirt V, tie, eyes, mouth, shoes, gun
    if on("det"):
        t = g["torso"]
        cx = (t[0] + t[2]) // 2
        if p.get("jacket"):
            rect(d, (cx - 1, t[1], cx + 2, t[1] + 1), p["shirt"])
            rect(d, (cx, t[1] + 2, cx + 1, t[1] + 3), p["shirt"])
            if p.get("tie"):
                rect(d, (cx, t[1] + 1, cx + 1, t[1] + 6), p["tie"])
        rect(d, (hx + 4, hy + 3, hx + 4, hy + 3), p["eyes"])
        rect(d, (hx + 6, hy + 3, hx + 6, hy + 3), p["eyes"])
        if female:
            rect(d, (hx + 4, hy + 2, hx + 4, hy + 2), p["eyes"])
            rect(d, (hx + 6, hy + 2, hx + 7, hy + 2), p["eyes"])
            rect(d, (hx + 5, hy + 6, hx + 6, hy + 6), (200, 40, 70, 255))
        else:
            rect(d, (hx + 5, hy + 6, hx + 6, hy + 6), (120, 60, 50, 255))
        for r in g["shoes"]:
            rect(d, r, p["shoes"])
        if g["gun"] and p.get("gun"):
            x0, y0, x1, y1 = g["gun"]
            rect(d, (x0, y0, x1, y0 + 1), (40, 40, 48, 255))
            rect(d, (x0, y0, x0 + 1, y1 + 1), (40, 40, 48, 255))
    # --- hair
    if on("hair") and p.get("hair_style"):
        hc, hs = col("hair"), col("hair", True)
        st = p["hair_style"]
        if st in ("short", "long", "pony"):
            rect(d, (hx - 1, hy - 1, hx + 7, hy + 1), hc)
            rect(d, (hx - 1, hy + 2, hx + 1, hy + 4), hc)
            rect(d, (hx, hy + 1, hx + 7, hy + 1), hs)
        if st == "long":
            rect(d, (hx - 1, hy + 2, hx + 2, hy + 11), hc)
            rect(d, (hx - 1, hy + 11, hx + 2, hy + 11), hs)
            rect(d, (hx + 6, hy + 2, hx + 7, hy + 2), hc)
        if st == "pony":
            rect(d, (hx - 3, hy + 1, hx - 2, hy + 8), hc)
            rect(d, (hx - 3, hy + 8, hx - 2, hy + 8), hs)
            rect(d, (hx - 2, hy, hx - 1, hy + 1), hs)
        if st == "spiky":
            rect(d, (hx - 1, hy, hx + 7, hy + 1), hc)
            rect(d, (hx - 1, hy + 2, hx + 1, hy + 3), hc)
            for x in (hx - 1, hx + 1, hx + 3, hx + 5, hx + 7):
                rect(d, (x, hy - 1, x, hy - 1), hc)
            for x in (hx, hx + 4):
                rect(d, (x, hy - 2, x, hy - 2), hs)
        if st == "buzz":
            rect(d, (hx, hy, hx + 7, hy), hs)
            rect(d, (hx, hy + 1, hx + 1, hy + 2), hs)
    # --- accessories (baked colours)
    if on("acc") and p.get("acc"):
        a = p["acc"]
        if a == "shades":
            rect(d, (hx + 3, hy + 3, hx + 7, hy + 3), (15, 15, 20, 255))
            rect(d, (hx + 4, hy + 4, hx + 4, hy + 4), (15, 15, 20, 255))
            rect(d, (hx + 6, hy + 4, hx + 7, hy + 4), (15, 15, 20, 255))
            rect(d, (hx + 2, hy + 3, hx + 2, hy + 3), (15, 15, 20, 255))
        elif a == "ear":
            rect(d, (hx + 1, hy + 4, hx + 2, hy + 5), (225, 225, 235, 255))
            rect(d, (hx + 1, hy + 6, hx + 1, hy + 9), (190, 190, 200, 255))
        elif a == "beret":
            rect(d, (hx - 1, hy - 2, hx + 7, hy), (140, 20, 35, 255))
            rect(d, (hx + 5, hy - 3, hx + 8, hy - 1), (140, 20, 35, 255))
            rect(d, (hx + 2, hy - 3, hx + 2, hy - 3), (60, 10, 15, 255))
            rect(d, (hx - 1, hy, hx + 7, hy), (100, 14, 25, 255))
    return img


def build_people():
    base = {"shirt": (245, 245, 250, 255), "tie": (40, 40, 50, 255), "eyes": (25, 25, 35, 255),
            "shoes": (25, 25, 30, 255), "gun": False, "jacket": True}
    # ----- player layers (grayscale tinted at runtime)
    layers = []
    for body in ("m", "f"):
        p = dict(base, body=body)
        layers.append(("skin_" + body, p, "skin"))
        layers.append(("suit_" + body, p, "suit"))
        layers.append(("det_" + body, p, "det"))
    for st in ("short", "long", "pony", "spiky", "buzz"):
        layers.append(("hair_" + st, dict(base, hair_style=st), "hair"))
    for a in ("shades", "ear", "beret"):
        layers.append(("acc_" + a, dict(base, acc=a), "acc"))
    for name, p, layer in layers:
        for pose in POSES[:-1]:
            save(draw_person(pose, p, layer), "%s_%s" % (name, pose), 2)
        ANIMS.append((name + "_walk", [name + "_walk1", name + "_walk2"], 8, "PLAYBACK_LOOP_FORWARD"))
        ANIMS.append((name + "_climb", [name + "_climb1", name + "_climb2"], 6, "PLAYBACK_LOOP_FORWARD"))

    # ----- villain (black suit, shades)
    villain = dict(base, body="m", skin=(225, 180, 145, 255), hair=(20, 20, 20, 255), hair_style="buzz",
                   jacket=(28, 28, 34, 255), pants=(28, 28, 34, 255), tie=(150, 20, 30, 255),
                   acc="shades", gun=True)
    # ----- bystanders (office workers)
    bys = [
        dict(base, body="m", skin=(240, 200, 170, 255), hair=(110, 70, 40, 255), hair_style="short",
             jacket=None, shirt=(90, 150, 220, 255), pants=(190, 170, 120, 255), shoes=(90, 60, 40, 255), gun=True),
        dict(base, body="f", skin=(200, 150, 110, 255), hair=(30, 25, 25, 255), hair_style="long",
             jacket=None, shirt=(230, 120, 170, 255), pants=(80, 80, 95, 255), gun=True),
        dict(base, body="m", skin=(120, 80, 55, 255), hair=(30, 25, 20, 255), hair_style="buzz",
             jacket=None, shirt=(110, 190, 110, 255), pants=(60, 70, 110, 255), gun=True),
        dict(base, body="f", skin=(245, 210, 185, 255), hair=(200, 90, 40, 255), hair_style="pony",
             jacket=None, shirt=(240, 210, 80, 255), pants=(120, 90, 70, 255), gun=True),
        dict(base, body="m", skin=(235, 195, 160, 255), hair=(225, 200, 120, 255), hair_style="spiky",
             jacket=(120, 120, 130, 255), pants=(120, 120, 130, 255), tie=(60, 90, 160, 255), gun=True),
    ]
    npcs = [("villain", villain)] + [("by%d" % (i + 1), b) for i, b in enumerate(bys)]
    for name, p in npcs:
        for pose in ("stand", "walk1", "walk2", "shoot"):
            save(draw_person(pose, p), "%s_%s" % (name, pose), 2)
        ANIMS.append((name + "_walk", [name + "_walk1", name + "_walk2"], 6, "PLAYBACK_LOOP_FORWARD"))
    # ----- boss (bigger)
    boss = dict(base, body="m", skin=(215, 170, 140, 255), hair=(200, 200, 205, 255), hair_style="buzz",
                jacket=(110, 30, 130, 255), pants=(60, 20, 75, 255), tie=(230, 180, 40, 255),
                acc="shades", gun=True)
    for pose in ("stand", "shoot"):
        save(draw_person(pose, boss), "boss_" + pose, 3)


# ----------------------------------------------------------------- tiles ---
def build_tiles():
    # floor slab
    img = Image.new("RGBA", (24, 24)); d = ImageDraw.Draw(img)
    rect(d, (0, 0, 23, 23), (96, 98, 110, 255))
    rect(d, (0, 0, 23, 2), (70, 52, 46, 255))
    rect(d, (0, 3, 23, 3), (50, 38, 34, 255))
    for x, y in ((3, 8), (15, 11), (8, 17), (20, 19), (11, 6), (2, 20)):
        rect(d, (x, y, x + 1, y), (80, 82, 94, 255))
    rect(d, (0, 23, 23, 23), (70, 72, 84, 255))
    save(img, "floor", 2)
    # roof slab
    img = Image.new("RGBA", (24, 24)); d = ImageDraw.Draw(img)
    rect(d, (0, 0, 23, 23), (60, 62, 74, 255))
    rect(d, (0, 0, 23, 3), (40, 40, 50, 255))
    save(img, "roof", 2)
    # wall (blocking)
    img = Image.new("RGBA", (24, 24)); d = ImageDraw.Draw(img)
    rect(d, (0, 0, 23, 23), (70, 90, 120, 255))
    rect(d, (1, 1, 22, 22), (88, 110, 142, 255))
    rect(d, (0, 11, 23, 12), (60, 76, 100, 255))
    for x, y in ((3, 3), (20, 3), (3, 20), (20, 20)):
        rect(d, (x, y, x, y), (150, 170, 200, 255))
    save(img, "wall", 2)
    # crate
    img = Image.new("RGBA", (24, 24)); d = ImageDraw.Draw(img)
    rect(d, (0, 0, 23, 23), (120, 80, 40, 255))
    rect(d, (2, 2, 21, 21), (170, 120, 60, 255))
    for i in range(20):
        rect(d, (2 + i, 2 + i, 3 + i, 2 + i), (120, 80, 40, 255))
    rect(d, (0, 0, 23, 1), (200, 150, 80, 255))
    save(img, "crate", 2)
    # background wallpaper (grayscale-ish so it can be tinted per floor)
    img = Image.new("RGBA", (24, 24)); d = ImageDraw.Draw(img)
    rect(d, (0, 0, 23, 23), (235, 235, 235, 255))
    for x in (5, 17):
        rect(d, (x, 0, x, 23), (220, 220, 220, 255))
    rect(d, (0, 20, 23, 23), (205, 205, 205, 255))
    rect(d, (0, 20, 23, 20), (180, 180, 180, 255))
    save(img, "bg", 2)
    # background with window
    img2 = img.copy(); d = ImageDraw.Draw(img2)
    rect(d, (4, 3, 19, 16), (90, 90, 100, 255))
    rect(d, (5, 4, 18, 15), (140, 200, 240, 255))
    rect(d, (5, 4, 18, 6), (170, 220, 250, 255))
    rect(d, (11, 4, 12, 15), (90, 90, 100, 255))
    rect(d, (6, 12, 9, 15), (110, 160, 120, 255))
    save(img2, "bg_window", 2)
    # background with picture
    img3 = img.copy(); d = ImageDraw.Draw(img3)
    rect(d, (7, 5, 16, 13), (120, 85, 45, 255))
    rect(d, (8, 6, 15, 12), (240, 230, 200, 255))
    rect(d, (9, 9, 14, 12), (90, 140, 90, 255))
    rect(d, (12, 7, 13, 8), (230, 180, 50, 255))
    save(img3, "bg_picture", 2)
    # background with secret-agency logo
    img4 = img.copy(); d = ImageDraw.Draw(img4)
    d.ellipse([6, 4, 17, 15], fill=(60, 60, 80, 255))
    d.ellipse([8, 6, 15, 13], fill=(220, 190, 60, 255))
    rect(d, (11, 6, 12, 13), (60, 60, 80, 255))
    save(img4, "bg_logo", 2)
    # ladder (transparent)
    img = Image.new("RGBA", (24, 24), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    rect(d, (3, 0, 5, 23), (200, 140, 30, 255))
    rect(d, (18, 0, 20, 23), (200, 140, 30, 255))
    rect(d, (5, 0, 5, 23), (150, 100, 20, 255))
    rect(d, (20, 0, 20, 23), (150, 100, 20, 255))
    for y in (2, 8, 14, 20):
        rect(d, (6, y, 17, y + 1), (230, 170, 50, 255))
        rect(d, (6, y + 1, 17, y + 1), (170, 120, 30, 255))
    save(img, "ladder", 2)
    # desk with computer: 48x26 base -> 96x52
    img = Image.new("RGBA", (48, 26), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    rect(d, (16, 0, 31, 9), (40, 40, 48, 255))     # monitor
    rect(d, (17, 1, 30, 8), (80, 170, 220, 255))
    rect(d, (18, 2, 26, 2), (200, 240, 255, 255))
    rect(d, (22, 10, 25, 11), (40, 40, 48, 255))   # stand
    rect(d, (34, 9, 41, 11), (230, 230, 230, 255))  # papers
    rect(d, (6, 8, 8, 11), (240, 240, 240, 255))   # mug
    rect(d, (1, 12, 46, 14), (140, 95, 55, 255))   # desk top
    rect(d, (1, 12, 46, 12), (175, 125, 75, 255))
    rect(d, (2, 15, 45, 25), (115, 75, 40, 255))   # front panel
    rect(d, (4, 17, 43, 23), (130, 88, 48, 255))
    rect(d, (21, 19, 26, 19), (90, 60, 30, 255))
    save(img, "desk", 2)
    # exit door 24x48 -> 48x96
    img = Image.new("RGBA", (24, 48), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    rect(d, (2, 0, 21, 7), (20, 120, 50, 255))
    rect(d, (3, 1, 20, 6), (40, 200, 90, 255))
    # 'EXIT' pixel text
    font = {"E": ["111", "100", "110", "100", "111"], "X": ["101", "101", "010", "101", "101"],
            "I": ["111", "010", "010", "010", "111"], "T": ["111", "010", "010", "010", "010"]}
    x = 4
    for ch in "EXIT":
        for yy, row in enumerate(font[ch]):
            for xx, c in enumerate(row):
                if c == "1":
                    rect(d, (x + xx, 1 + yy, x + xx, 1 + yy), (255, 255, 255, 255))
        x += 4
    rect(d, (1, 9, 22, 47), (60, 60, 70, 255))
    rect(d, (3, 11, 20, 47), (170, 60, 50, 255))
    rect(d, (5, 13, 18, 27), (190, 75, 60, 255))
    rect(d, (5, 30, 18, 45), (190, 75, 60, 255))
    rect(d, (16, 29, 18, 31), (240, 210, 80, 255))
    save(img, "door", 2)
    # boss buttons 16x12 -> 48x36
    for name, cap, glow in (("button_off", (120, 60, 60, 255), None),
                            ("button_on", (255, 50, 50, 255), (255, 240, 120, 255)),
                            ("button_done", (60, 210, 90, 255), None)):
        img = Image.new("RGBA", (16, 12), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
        if glow:
            d.ellipse([0, 0, 15, 8], fill=glow)
        rect(d, (2, 8, 13, 11), (90, 90, 100, 255))
        rect(d, (2, 8, 13, 8), (130, 130, 140, 255))
        d.ellipse([4, 3, 11, 9], fill=cap)
        rect(d, (6, 4, 7, 4), (255, 255, 255, 180))
        save(img, name, 3)
    # teleport pad (boss)
    img = Image.new("RGBA", (24, 6), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    d.ellipse([0, 0, 23, 5], fill=(80, 40, 120, 255))
    d.ellipse([3, 1, 20, 4], fill=(170, 90, 240, 255))
    save(img, "pad", 2)
    # plant
    img = Image.new("RGBA", (12, 24), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    rect(d, (3, 17, 8, 23), (170, 90, 50, 255))
    rect(d, (3, 17, 8, 17), (200, 120, 70, 255))
    for (x0, y0, x1, y1) in ((5, 4, 6, 16), (2, 8, 4, 14), (7, 6, 9, 13), (0, 11, 2, 15), (9, 10, 11, 15), (4, 1, 7, 5)):
        d.ellipse([x0, y0, x1, y1], fill=(60, 150, 70, 255))
    save(img, "plant", 2)
    # water cooler
    img = Image.new("RGBA", (12, 24), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    rect(d, (3, 0, 8, 7), (150, 200, 240, 255))
    rect(d, (4, 1, 5, 6), (200, 235, 255, 255))
    rect(d, (2, 8, 9, 23), (230, 230, 235, 255))
    rect(d, (4, 12, 7, 13), (90, 90, 110, 255))
    save(img, "cooler", 2)


def build_fx_ui():
    # bullet
    img = Image.new("RGBA", (8, 3), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    rect(d, (0, 0, 7, 2), (255, 140, 20, 255))
    rect(d, (2, 1, 7, 1), (255, 250, 180, 255))
    save(img, "bullet", 2)
    # muzzle flash
    img = Image.new("RGBA", (6, 6), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    d.polygon([(0, 3), (5, 0), (4, 3), (5, 5)], fill=(255, 220, 80, 255))
    save(img, "flash", 2)
    # exclamation bubble
    img = Image.new("RGBA", (10, 14), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    d.ellipse([0, 0, 9, 11], fill=(255, 255, 255, 255), outline=(30, 30, 30, 255))
    d.polygon([(3, 10), (6, 10), (4, 13)], fill=(255, 255, 255, 255))
    rect(d, (4, 2, 5, 6), (220, 30, 30, 255))
    rect(d, (4, 8, 5, 9), (220, 30, 30, 255))
    save(img, "exclaim", 2)
    # hearts
    heart = ["01100110", "11111111", "11111111", "11111111", "01111110", "00111100", "00011000"]
    for name, c, o in (("heart", (230, 40, 60, 255), (120, 10, 20, 255)),
                       ("heart_empty", (70, 70, 80, 255), (30, 30, 35, 255))):
        img = Image.new("RGBA", (10, 9), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
        for y, row in enumerate(heart):
            for x, ch in enumerate(row):
                if ch == "1":
                    for dx, dy in ((0, 1), (1, 0), (2, 1), (1, 2)):
                        rect(d, (x + dx, y + dy, x + dx, y + dy), o)
        for y, row in enumerate(heart):
            for x, ch in enumerate(row):
                if ch == "1":
                    rect(d, (x + 1, y + 1, x + 1, y + 1), c)
        rect(d, (2, 2, 3, 2), (255, 255, 255, 200)) if name == "heart" else None
        save(img, name, 3)
    # debris pieces
    for i, c in enumerate(((255, 170, 40, 255), (255, 80, 30, 255), (110, 30, 130, 255), (255, 240, 150, 255))):
        img = Image.new("RGBA", (4, 4), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
        rect(d, (0, 0, 3, 3), c)
        save(img, "debris%d" % (i + 1), 2)
    # big blast
    img = Image.new("RGBA", (32, 32), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    d.ellipse([0, 0, 31, 31], fill=(255, 120, 30, 200))
    d.ellipse([5, 5, 26, 26], fill=(255, 200, 60, 230))
    d.ellipse([10, 10, 21, 21], fill=(255, 255, 220, 255))
    save(img, "blast", 3)
    # UI: round button + icons (white, tinted by GUI)
    img = Image.new("RGBA", (32, 32), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    d.ellipse([0, 0, 31, 31], fill=(255, 255, 255, 255))
    save(img, "ui_circle", 3)
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    d.polygon([(3, 8), (11, 2), (11, 14)], fill=(255, 255, 255, 255))
    save(img, "ui_left", 3)
    save(img.transpose(Image.FLIP_LEFT_RIGHT), "ui_right", 3)
    save(img.transpose(Image.ROTATE_270), "ui_up", 3)
    save(img.transpose(Image.ROTATE_90), "ui_down", 3)
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    d.polygon([(2, 9), (8, 3), (14, 9), (11, 9), (8, 6), (5, 9)], fill=(255, 255, 255, 255))
    d.polygon([(2, 14), (8, 8), (14, 14), (11, 14), (8, 11), (5, 14)], fill=(255, 255, 255, 255))
    save(img, "ui_jump", 3)
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0)); d = ImageDraw.Draw(img)
    rect(d, (4, 3, 6, 12), (255, 255, 255, 255))
    rect(d, (9, 3, 11, 12), (255, 255, 255, 255))
    save(img, "ui_pause", 3)
    img = Image.new("RGBA", (8, 8), (255, 255, 255, 255))
    save(img, "ui_white", 1)


def write_atlas():
    lines = []
    in_anim = set(i for _, imgs, _, _ in ANIMS for i in imgs)
    for name in IMAGES:
        lines.append('images {\n  image: "/assets/images/%s.png"\n}' % name)
    for aid, imgs, fps, pb in ANIMS:
        body = "".join('  images {\n    image: "/assets/images/%s.png"\n  }\n' % i for i in imgs)
        lines.append('animations {\n  id: "%s"\n%s  playback: %s\n  fps: %d\n}' % (aid, body, pb, fps))
    lines.append("margin: 0\nextrude_borders: 2\ninner_padding: 0")
    with open(ATLAS, "w", newline="\n") as f:
        f.write("\n".join(lines) + "\n")
    _ = in_anim


def build_icons():
    """Android launcher icons: Netta's head (long hair, shades) on a dark badge."""
    base = {"shirt": (245, 245, 250, 255), "tie": (40, 40, 50, 255), "eyes": (25, 25, 35, 255),
            "shoes": (25, 25, 30, 255), "jacket": (50, 50, 60, 255), "pants": (50, 50, 60, 255),
            "skin": (241, 196, 158, 255), "hair": (40, 34, 34, 255), "hair_style": "long", "acc": "shades",
            "body": "f"}
    fig = draw_person("stand", base)
    head = fig.crop((4, 0, 20, 16))           # 16x16 head + shoulders
    out_dir = os.path.join(ROOT, "assets", "icons")
    os.makedirs(out_dir, exist_ok=True)
    for size in (36, 48, 72, 96, 144, 192):
        img = Image.new("RGBA", (size, size), (0, 0, 0, 0))
        d = ImageDraw.Draw(img)
        d.rounded_rectangle([0, 0, size - 1, size - 1], radius=size // 5, fill=(28, 34, 70, 255))
        d.rounded_rectangle([size // 16, size // 16, size - 1 - size // 16, size - 1 - size // 16],
                            radius=size // 6, outline=(240, 200, 60, 255), width=max(1, size // 24))
        s = max(1, (size * 3 // 4) // 16)
        h = head.resize((16 * s, 16 * s), Image.NEAREST)
        img.alpha_composite(h, ((size - h.width) // 2, (size - h.height) // 2 + size // 16))
        img.save(os.path.join(out_dir, "icon_%d.png" % size))


if __name__ == "__main__":
    for f in os.listdir(OUT):
        if f.endswith(".png"):
            os.remove(os.path.join(OUT, f))
    build_people()
    build_tiles()
    build_fx_ui()
    write_atlas()
    build_icons()
    print("images:", len(IMAGES), "animations:", len(ANIMS))
