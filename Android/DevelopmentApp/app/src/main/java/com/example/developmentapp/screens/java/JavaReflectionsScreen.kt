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
fun JavaReflectionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Reflections and More",
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
                SectionCard(title = "Object and Class<T>") {
                    BodyText(
                        "Two special types sit at the root of Java's runtime type system:\n\n" +
                        "java.lang.Object — every class implicitly extends Object. It provides " +
                        "equals(), hashCode(), toString(), getClass(), wait(), notify(), and " +
                        "clone(). Any variable declared as Object can hold a reference to any " +
                        "object whatsoever.\n\n" +
                        "java.lang.Class<T> — a live object that describes a class or interface. " +
                        "The JVM creates exactly one Class instance per loaded type and stores it " +
                        "permanently. Through it you can inspect everything the compiler knew about " +
                        "a type: its name, superclass, interfaces, fields, methods, and constructors."
                    )
                    CodeBlock(
                        "Object obj = new Object();        // base of all classes\n" +
                        "Class<?> c = obj.getClass();      // Class<Object>\n" +
                        "System.out.println(c.getName());  // \"java.lang.Object\"\n\n" +
                        "String s = \"hello\";\n" +
                        "System.out.println(s.getClass().getSimpleName()); // \"String\"\n\n" +
                        "// Class is also a type parameter on itself:\n" +
                        "Class<String> strClass = String.class;  // typed, compile-time safe"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Getting a Class Object") {
                    BodyText(
                        "Three ways to obtain a Class<?> object:\n\n" +
                        "1. MyClass.class — compile-time class literal; no instance needed, " +
                        "no exception thrown. Type-safe: String.class is Class<String>.\n\n" +
                        "2. obj.getClass() — called on an existing instance; returns the runtime " +
                        "type (which may be a subclass of the declared type).\n\n" +
                        "3. Class.forName(\"fully.qualified.Name\") — loads a class by name at " +
                        "runtime; useful when the class name comes from config or user input. " +
                        "Throws ClassNotFoundException if not found on the classpath. The JVM " +
                        "caches loaded classes per class loader, so repeated calls return the " +
                        "same object."
                    )
                    CodeBlock(
                        "// 1. Class literal — type-safe, no exception\n" +
                        "Class<String> c1 = String.class;\n\n" +
                        "// 2. From an instance\n" +
                        "Class<?> c2 = \"hello\".getClass();\n\n" +
                        "// 3. By name — throws ClassNotFoundException\n" +
                        "Class<?> c3 = Class.forName(\"java.lang.String\");\n\n" +
                        "// All three point to the same cached Class object:\n" +
                        "System.out.println(c1 == c2);  // true\n" +
                        "System.out.println(c2 == c3);  // true\n\n" +
                        "// Useful metadata\n" +
                        "System.out.println(c1.getName());        // \"java.lang.String\"\n" +
                        "System.out.println(c1.getSimpleName());  // \"String\"\n" +
                        "System.out.println(c1.getSuperclass().getSimpleName()); // \"Object\""
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Constructors") {
                    BodyText(
                        "From a Class<?> you can discover and invoke any constructor:\n\n" +
                        "• getConstructors() — returns only public constructors\n" +
                        "• getDeclaredConstructors() — returns ALL constructors (public, " +
                        "protected, package-private, and private)\n\n" +
                        "Each Constructor object has getParameterTypes() and newInstance(args...) " +
                        "which calls that constructor and returns the new object. If the constructor " +
                        "is not public, call setAccessible(true) first."
                    )
                    CodeBlock(
                        "import java.lang.reflect.*;\n\n" +
                        "Class<?> clazz = Class.forName(\"com.example.Person\");\n\n" +
                        "// List all constructors\n" +
                        "for (Constructor<?> ctor : clazz.getDeclaredConstructors()) {\n" +
                        "    System.out.println(ctor);  // prints signature\n" +
                        "}\n\n" +
                        "// Get a specific constructor: Person(String, int)\n" +
                        "Constructor<?> ctor = clazz.getDeclaredConstructor(\n" +
                        "    String.class, int.class);\n\n" +
                        "ctor.setAccessible(true);            // needed if private\n" +
                        "Object person = ctor.newInstance(\"Alice\", 30);  // creates instance"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Storing Unknown Types") {
                    BodyText(
                        "When you create an object via reflection you don't know its type at " +
                        "compile time. Use Object to hold any reference:\n\n" +
                        "• Object — can hold anything; use instanceof before casting\n" +
                        "• Java 16+ pattern matching: instanceof Person p — combines the check " +
                        "and cast in one step\n" +
                        "• Generic type token Class<T> — when you do know the type at the call " +
                        "site, pass Class<T> as a parameter and cast safely inside\n\n" +
                        "Avoid raw Class without generics in new code — you lose compile-time " +
                        "type safety and will need unchecked casts."
                    )
                    CodeBlock(
                        "// Object holds anything — cast before use\n" +
                        "Object unknown = ctor.newInstance(\"Alice\", 30);\n\n" +
                        "if (unknown instanceof Person p) {    // Java 16+ pattern matching\n" +
                        "    System.out.println(p.getName());  // no explicit cast needed\n" +
                        "}\n\n" +
                        "// Generic type token — type-safe helper\n" +
                        "public static <T> T create(Class<T> clazz, Object... args)\n" +
                        "        throws Exception {\n" +
                        "    Constructor<T> ctor = clazz.getDeclaredConstructor(\n" +
                        "        /* param types */);\n" +
                        "    ctor.setAccessible(true);\n" +
                        "    return ctor.newInstance(args);\n" +
                        "}\n\n" +
                        "// Caller stays type-safe:\n" +
                        "Person p = create(Person.class);"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enumerating Fields") {
                    BodyText(
                        "A Field object represents one field of a class:\n\n" +
                        "• getFields() — public fields declared in this class AND inherited from " +
                        "superclasses / interfaces\n" +
                        "• getDeclaredFields() — ALL fields declared directly in this class " +
                        "(public, protected, package, private) but NOT inherited ones\n\n" +
                        "Each Field gives you: getName(), getType(), getModifiers() (use " +
                        "Modifier.isStatic() / isFinal() etc. to decode the int). To read or " +
                        "write the field value call field.get(obj) / field.set(obj, value) after " +
                        "setAccessible(true) if needed."
                    )
                    CodeBlock(
                        "Class<?> clazz = Class.forName(\"com.example.Person\");\n\n" +
                        "// All declared fields (incl. private)\n" +
                        "for (Field f : clazz.getDeclaredFields()) {\n" +
                        "    int mod = f.getModifiers();\n" +
                        "    System.out.printf(\"%s %s %s%n\",\n" +
                        "        Modifier.toString(mod),\n" +
                        "        f.getType().getSimpleName(),\n" +
                        "        f.getName());\n" +
                        "}\n" +
                        "// Output for class Person { private String name; private int age; }:\n" +
                        "// private String name\n" +
                        "// private int    age"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Enumerating Methods") {
                    BodyText(
                        "A Method object represents one method of a class:\n\n" +
                        "• getMethods() — all public methods declared in this class AND inherited " +
                        "from superclasses and interfaces (includes Object's methods)\n" +
                        "• getDeclaredMethods() — all methods declared directly in this class, " +
                        "all access levels, but NOT inherited\n\n" +
                        "Each Method gives: getName(), getReturnType(), getParameterTypes(), " +
                        "getExceptionTypes(), getModifiers(). Call method.invoke(obj, args...) " +
                        "to execute it — returns Object (or null for void)."
                    )
                    CodeBlock(
                        "Class<?> clazz = Person.class;\n\n" +
                        "// Print all declared methods\n" +
                        "for (Method m : clazz.getDeclaredMethods()) {\n" +
                        "    System.out.printf(\"%s %s(%s)%n\",\n" +
                        "        m.getReturnType().getSimpleName(),\n" +
                        "        m.getName(),\n" +
                        "        Arrays.stream(m.getParameterTypes())\n" +
                        "              .map(Class::getSimpleName)\n" +
                        "              .collect(Collectors.joining(\", \")));\n" +
                        "}\n" +
                        "// Example output:\n" +
                        "// void   setName(String)\n" +
                        "// String getName()\n" +
                        "// void   greet()"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Invoking Methods and Modifying Fields") {
                    BodyText(
                        "Once you have a Field or Method object you can read/write/invoke it " +
                        "on any instance:\n\n" +
                        "• field.get(obj) — returns the field's value as Object\n" +
                        "• field.set(obj, value) — sets the field's value\n" +
                        "• method.invoke(obj, args...) — calls the method; returns Object " +
                        "(null for void); varargs for the arguments\n\n" +
                        "setAccessible(true) bypasses the access check — private, protected, and " +
                        "package-private members all become reachable. On Java 9+ modules may " +
                        "block this; use --add-opens on the command line or open the package in " +
                        "module-info.java."
                    )
                    CodeBlock(
                        "Object person = new Person(\"Alice\", 30);\n" +
                        "Class<?> clazz = person.getClass();\n\n" +
                        "// --- Modify a private field ---\n" +
                        "Field ageField = clazz.getDeclaredField(\"age\");\n" +
                        "ageField.setAccessible(true);      // bypass private\n" +
                        "System.out.println(ageField.get(person)); // 30\n" +
                        "ageField.set(person, 99);\n" +
                        "System.out.println(ageField.get(person)); // 99\n\n" +
                        "// --- Invoke a private method ---\n" +
                        "Method greet = clazz.getDeclaredMethod(\"greet\");\n" +
                        "greet.setAccessible(true);\n" +
                        "greet.invoke(person);              // calls person.greet()\n\n" +
                        "// --- Method with parameters ---\n" +
                        "Method setName = clazz.getDeclaredMethod(\"setName\", String.class);\n" +
                        "setName.setAccessible(true);\n" +
                        "setName.invoke(person, \"Bob\");"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Your Own Classes") {
                    BodyText(
                        "Every class you write is fully available to the reflection API the moment " +
                        "it is compiled. javac embeds complete metadata into the .class file: field " +
                        "names, types, and modifiers; method names, parameter types, return types, " +
                        "and checked exceptions; constructor signatures; access modifiers; " +
                        "annotations. No extra annotation or configuration is needed — it's always " +
                        "there.\n\n" +
                        "Reflection is heavily used by frameworks: Spring reads annotations to wire " +
                        "beans, Jackson reads field names to serialize JSON, JUnit finds methods " +
                        "annotated @Test. When you write a plain Java class your code is " +
                        "automatically discoverable by all of these."
                    )
                    CodeBlock(
                        "// Your class — no extra annotation needed\n" +
                        "package com.example;\n" +
                        "public class Dog {\n" +
                        "    private String name;\n" +
                        "    public Dog(String n) { this.name = n; }\n" +
                        "    private void bark() { System.out.println(name + \": Woof!\"); }\n" +
                        "}\n\n" +
                        "// Reflected from anywhere on the classpath:\n" +
                        "Class<?> c = Class.forName(\"com.example.Dog\");\n" +
                        "Constructor<?> ctor = c.getDeclaredConstructor(String.class);\n" +
                        "Object dog = ctor.newInstance(\"Rex\");\n\n" +
                        "Field name = c.getDeclaredField(\"name\");\n" +
                        "name.setAccessible(true);\n" +
                        "System.out.println(name.get(dog));  // \"Rex\"\n\n" +
                        "Method bark = c.getDeclaredMethod(\"bark\");\n" +
                        "bark.setAccessible(true);\n" +
                        "bark.invoke(dog);                   // \"Rex: Woof!\""
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
