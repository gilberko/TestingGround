package com.example.developmentapp.screens.java

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JavaEnumsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Enums",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Java Enums vs C Enums") {
                    BodyText(
                        "In C, an enum is just a named integer constant — a compile-time alias with no " +
                        "type safety and no methods. You can freely assign an int where an enum is expected:"
                    )
                    CodeBlock(
                        "// C\n" +
                        "enum Day { MON = 0, TUE = 1, WED = 2 };\n" +
                        "int d = MON;   // fine — MON is just 0\n" +
                        "d = 42;        // also fine — no type checking"
                    )
                    BodyText(
                        "In Java, an enum is a full class. Each constant is a singleton object — an " +
                        "instance of the enum type. The enum type implicitly extends java.lang.Enum<E>. " +
                        "It is type-safe: you cannot assign an int where a Day is expected, and the " +
                        "compiler enforces this at compile time. Enums can have fields, constructors, " +
                        "and methods — none of which are possible in C."
                    )
                }
            }

            item {
                SectionCard(title = "Basic Declaration") {
                    BodyText(
                        "Declare an enum with the enum keyword. Each listed identifier becomes a " +
                        "public static final constant of that type:"
                    )
                    CodeBlock(
                        "enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }\n" +
                        "\n" +
                        "Day today = Day.WED;\n" +
                        "System.out.println(today);            // WED\n" +
                        "System.out.println(today.ordinal());  // 2  (0-based)\n" +
                        "System.out.println(today.name());     // WED"
                    )
                    BodyText(
                        "ordinal() returns the declaration position (0-based). name() returns the " +
                        "constant's identifier as a String. toString() defaults to name() but can be " +
                        "overridden."
                    )
                }
            }

            item {
                SectionCard(title = "Enum Is an Object") {
                    BodyText(
                        "Every enum constant is a heap-allocated singleton. instanceof works as expected:"
                    )
                    CodeBlock(
                        "Day.MON instanceof Day   // true\n" +
                        "Day.MON instanceof Enum  // true  (all enums extend Enum)\n" +
                        "\n" +
                        "// Reference equality works because constants are singletons\n" +
                        "Day.MON == Day.MON       // true\n" +
                        "Day.MON == Day.TUE       // false"
                    )
                    BodyText(
                        "The compiler auto-generates two static methods on every enum type:"
                    )
                    CodeBlock(
                        "// values() — returns all constants in declaration order\n" +
                        "for (Day d : Day.values())\n" +
                        "    System.out.println(d.ordinal() + \": \" + d);\n" +
                        "// 0: MON  1: TUE  2: WED ...\n" +
                        "\n" +
                        "// valueOf(String) — parses by name, throws IllegalArgumentException if unknown\n" +
                        "Day parsed = Day.valueOf(\"FRI\");  // Day.FRI"
                    )
                }
            }

            item {
                SectionCard(title = "Fields and Constructor") {
                    BodyText(
                        "Enums can have fields. Each constant passes arguments to a private constructor " +
                        "that runs exactly once at class-load time:"
                    )
                    CodeBlock(
                        "enum Planet {\n" +
                        "    MERCURY(3.303e+23, 2.4397e6),\n" +
                        "    VENUS  (4.869e+24, 6.0518e6),\n" +
                        "    EARTH  (5.976e+24, 6.37814e6);\n" +
                        "\n" +
                        "    private final double mass;    // kg\n" +
                        "    private final double radius;  // metres\n" +
                        "\n" +
                        "    Planet(double mass, double radius) {\n" +
                        "        this.mass   = mass;\n" +
                        "        this.radius = radius;\n" +
                        "    }\n" +
                        "    public double getMass()   { return mass; }\n" +
                        "    public double getRadius() { return radius; }\n" +
                        "}\n" +
                        "System.out.println(Planet.EARTH.getMass()); // 5.976E24"
                    )
                    BodyText(
                        "The constructor must be private or package-private — you cannot create enum " +
                        "instances manually with new. This enforces the singleton guarantee."
                    )
                }
            }

            item {
                SectionCard(title = "Methods on Enums") {
                    BodyText(
                        "Any instance or static method can be added to an enum. Here Planet gains " +
                        "physics calculations using its stored fields:"
                    )
                    CodeBlock(
                        "enum Planet {\n" +
                        "    MERCURY(3.303e+23, 2.4397e6),\n" +
                        "    EARTH  (5.976e+24, 6.37814e6);\n" +
                        "\n" +
                        "    static final double G = 6.67300E-11;\n" +
                        "    private final double mass, radius;\n" +
                        "    Planet(double m, double r) { mass = m; radius = r; }\n" +
                        "\n" +
                        "    public double surfaceGravity() {\n" +
                        "        return G * mass / (radius * radius);\n" +
                        "    }\n" +
                        "    public double surfaceWeight(double otherMass) {\n" +
                        "        return otherMass * surfaceGravity();\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "double earthWeight = 75.0;\n" +
                        "double mass = earthWeight / Planet.EARTH.surfaceGravity();\n" +
                        "for (Planet p : Planet.values())\n" +
                        "    System.out.printf(\"Weight on %s = %.2f%n\", p, p.surfaceWeight(mass));"
                    )
                }
            }

            item {
                SectionCard(title = "Abstract Methods (Per-Constant Behaviour)") {
                    BodyText(
                        "An enum can declare an abstract method. Each constant must then provide its " +
                        "own implementation — similar to a sealed class with polymorphism, but more " +
                        "compact:"
                    )
                    CodeBlock(
                        "enum Operation {\n" +
                        "    PLUS(\"+\")  { public double apply(double x, double y) { return x + y; } },\n" +
                        "    MINUS(\"-\") { public double apply(double x, double y) { return x - y; } },\n" +
                        "    TIMES(\"*\") { public double apply(double x, double y) { return x * y; } },\n" +
                        "    DIVIDE(\"/\") { public double apply(double x, double y) { return x / y; } };\n" +
                        "\n" +
                        "    private final String symbol;\n" +
                        "    Operation(String symbol) { this.symbol = symbol; }\n" +
                        "    public abstract double apply(double x, double y);\n" +
                        "\n" +
                        "    @Override public String toString() { return symbol; }\n" +
                        "}\n" +
                        "\n" +
                        "System.out.println(Operation.PLUS.apply(3, 4));   // 7.0\n" +
                        "System.out.println(Operation.TIMES);              // *"
                    )
                    BodyText(
                        "Each constant becomes an anonymous subclass of Operation that implements apply(). " +
                        "This pattern replaces a switch/if-else dispatch entirely."
                    )
                }
            }

            item {
                SectionCard(title = "Enums in switch") {
                    BodyText(
                        "Old-style switch uses just the constant name (no qualifier needed). " +
                        "Java 14+ enhanced switch expressions are cleaner:"
                    )
                    CodeBlock(
                        "// Classic switch — fall-through unless break is used\n" +
                        "switch (today) {\n" +
                        "    case SAT:\n" +
                        "    case SUN: System.out.println(\"Weekend\"); break;\n" +
                        "    default:  System.out.println(\"Weekday\");\n" +
                        "}\n" +
                        "\n" +
                        "// Java 14+ switch expression — no fall-through, returns a value\n" +
                        "int numLetters = switch (today) {\n" +
                        "    case MON, FRI, SUN -> 6;\n" +
                        "    case TUE           -> 7;\n" +
                        "    case THU, SAT      -> 8;\n" +
                        "    case WED           -> 9;\n" +
                        "};"
                    )
                    BodyText(
                        "The compiler checks exhaustiveness for enum switches: if all constants are " +
                        "covered, no default branch is needed (and the compiler warns if a new constant " +
                        "is added later without updating the switch)."
                    )
                }
            }

            item {
                SectionCard(title = "EnumSet and EnumMap") {
                    BodyText(
                        "The java.util package provides two highly optimised collections specifically " +
                        "for enum keys. EnumSet<E> is backed by a single long bit vector (for enums " +
                        "with ≤64 constants), making set operations extremely fast. EnumMap<K,V> is " +
                        "backed by an array indexed by ordinal(), much faster than HashMap for enum keys:"
                    )
                    CodeBlock(
                        "// EnumSet — compact bit-vector implementation\n" +
                        "EnumSet<Day> weekend  = EnumSet.of(Day.SAT, Day.SUN);\n" +
                        "EnumSet<Day> weekdays = EnumSet.complementOf(weekend);\n" +
                        "EnumSet<Day> allDays  = EnumSet.allOf(Day.class);\n" +
                        "EnumSet<Day> workWeek = EnumSet.range(Day.MON, Day.FRI);\n" +
                        "\n" +
                        "// EnumMap — array-backed, ordinal-indexed\n" +
                        "EnumMap<Day, String> schedule = new EnumMap<>(Day.class);\n" +
                        "schedule.put(Day.MON, \"Stand-up at 9am\");\n" +
                        "schedule.put(Day.FRI, \"Retrospective\");\n" +
                        "System.out.println(schedule.get(Day.MON)); // Stand-up at 9am"
                    )
                    BodyText(
                        "Prefer EnumSet over HashSet<MyEnum> and EnumMap over HashMap<MyEnum,V> whenever " +
                        "your key type is an enum — both are faster and use less memory."
                    )
                }
            }

            item {
                SectionCard(title = "Interface Implementation") {
                    BodyText(
                        "Enums can implement interfaces. They cannot extend a class (they already " +
                        "implicitly extend java.lang.Enum<E>), but implementing interfaces is fully " +
                        "supported:"
                    )
                    CodeBlock(
                        "interface Describable {\n" +
                        "    String describe();\n" +
                        "}\n" +
                        "\n" +
                        "enum Planet implements Describable {\n" +
                        "    MERCURY(3.303e+23, 2.4397e6),\n" +
                        "    EARTH  (5.976e+24, 6.37814e6);\n" +
                        "\n" +
                        "    private final double mass, radius;\n" +
                        "    Planet(double m, double r) { mass = m; radius = r; }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public String describe() {\n" +
                        "        return name() + \" (mass=\" + mass + \" kg)\";\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "Describable d = Planet.EARTH;        // polymorphic reference\n" +
                        "System.out.println(d.describe());    // EARTH (mass=5.976E24 kg)"
                    )
                    BodyText(
                        "This allows enums to be passed wherever an interface type is expected, " +
                        "enabling enum constants to participate fully in polymorphic designs."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
