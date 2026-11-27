"""Writes the boilerplate Defold resource files (.go, .gui shells, main.collection).
Run: python tools/make_defold_files.py   (safe to re-run; scripts are not touched)"""
import os

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
BS = "\\"          # one backslash
NL = BS + "n"      # the two characters \n inside a protobuf text string
Q = BS + '"'       # an escaped quote inside a protobuf text string


def w(rel, text):
    with open(os.path.join(ROOT, rel), "w", newline="\n") as f:
        f.write(text)


def data_block(lines):
    return "\n  ".join('"%s%s"' % (l.replace('"', Q), NL) for l in lines)


def sprite_data(anim):
    return data_block(['default_animation: "%s"' % anim,
                       'material: "/builtins/materials/sprite.material"',
                       'blend_mode: BLEND_MODE_ALPHA',
                       'textures {',
                       '  sampler: "texture_sampler"',
                       '  texture: "/assets/game.atlas"',
                       '}'])


def emb(id_, type_, data, pos=None):
    s = 'embedded_components {\n  id: "%s"\n  type: "%s"\n  data: %s\n' % (id_, type_, data)
    if pos:
        s += '  position {\n    x: %s\n    y: %s\n    z: %s\n  }\n' % pos
    return s + '}\n'


w("game/sprite.go", emb("sprite", "sprite", sprite_data("floor")))

p = ""
for i, (cid, anim) in enumerate((("skin", "skin_f_stand"), ("suit", "suit_f_stand"), ("det", "det_f_stand"),
                                 ("hair", "hair_long_stand"), ("acc", "acc_ear_stand"))):
    p += emb(cid, "sprite", sprite_data(anim), ("0.0", "36.0", "%.3f" % (i * 0.002)))
w("game/player.go", p)

w("game/camera.go", emb("camera", "camera", data_block([
    "aspect_ratio: 1.0", "fov: 0.7854", "near_z: 0.1", "far_z: 20.0", "auto_aspect_ratio: 1",
    "orthographic_projection: 1", "orthographic_zoom: 1.0", "orthographic_mode: ORTHO_MODE_AUTO_FIT"])))

w("game/game.go", 'components {\n  id: "game"\n  component: "/game/game.script"\n}\n'
  + emb("sprite_factory", "factory", data_block(['prototype: "/game/sprite.go"'])))

GUIS = ("hud", "overlay", "designer", "menu")
w("ui/ui.go", "".join('components {\n  id: "%s"\n  component: "/ui/%s.gui"\n}\n' % (n, n) for n in GUIS))
for n in GUIS:
    w("ui/%s.gui" % n,
      'script: "/ui/%s.gui_script"\n'
      'fonts {\n  name: "main"\n  font: "/assets/fonts/main.font"\n}\n'
      'textures {\n  name: "game"\n  texture: "/assets/game.atlas"\n}\n'
      'background_color {\n  x: 0.0\n  y: 0.0\n  z: 0.0\n  w: 0.0\n}\n'
      'adjust_reference: ADJUST_REFERENCE_PARENT\nmax_nodes: 512\n' % n)


def inst(id_, proto, pos):
    return ('instances {\n  id: "%s"\n  prototype: "%s"\n  position {\n    x: %s\n    y: %s\n    z: %s\n  }\n}\n'
            % ((id_, proto) + pos))


w("main/main.collection", 'name: "main"\n'
  + inst("game", "/game/game.go", ("0.0", "0.0", "0.0"))
  + inst("player", "/game/player.go", ("0.0", "0.0", "0.2"))
  + inst("camera", "/game/camera.go", ("640.0", "312.0", "10.0"))
  + inst("ui", "/ui/ui.go", ("0.0", "0.0", "0.0"))
  + "scale_along_z: 0\n")
print("ok")
