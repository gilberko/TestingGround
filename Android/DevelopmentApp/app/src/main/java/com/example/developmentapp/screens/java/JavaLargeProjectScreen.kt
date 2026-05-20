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
fun JavaLargeProjectScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Java — Large Project",
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
                SectionCard(title = "Organizing with Packages") {
                    BodyText(
                        "In a real project you will have dozens or hundreds of classes. Packages " +
                        "are how Java organizes them:\n\n" +
                        "• One public class per .java file; the file name must exactly match the " +
                        "class name.\n" +
                        "• The package declaration goes on the very first line of the file.\n" +
                        "• Use import to bring other packages' classes into scope, or write the " +
                        "fully qualified name inline.\n" +
                        "• Build tools (Gradle, Maven) compile every .java file under the source " +
                        "root automatically — you don't list files manually.\n\n" +
                        "A class with no package declaration is in the unnamed (default) package — " +
                        "fine for quick experiments, never for real projects."
                    )
                    CodeBlock(
                        "// File: src/main/java/com/example/app/UserService.java\n" +
                        "package com.example.app;\n\n" +
                        "import com.example.model.User;   // from another package\n" +
                        "import java.util.List;\n\n" +
                        "public class UserService {\n" +
                        "    public List<User> getAllUsers() { ... }\n" +
                        "    public void save(User u)       { ... }\n" +
                        "}\n\n" +
                        "// File: src/main/java/com/example/model/User.java\n" +
                        "package com.example.model;\n\n" +
                        "public class User {\n" +
                        "    private String name;\n" +
                        "    public User(String name) { this.name = name; }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Package Names and Directory Structure") {
                    BodyText(
                        "Package names must match the directory path exactly. The compiler " +
                        "and JVM both enforce this — the package declaration and the file's " +
                        "location must agree or the class will not compile / load.\n\n" +
                        "Convention: use the reverse of your domain name as the root, then " +
                        "subdivide by layer or feature. This guarantees uniqueness across all " +
                        "Java code in the world.\n\n" +
                        "Build tools set the source root for you (src/main/java/ in Gradle / " +
                        "Maven). Everything underneath is resolved relative to that root."
                    )
                    CodeBlock(
                        "// Gradle project layout (standard)\n" +
                        "src/\n" +
                        "  main/\n" +
                        "    java/                      ← source root\n" +
                        "      com/\n" +
                        "        example/\n" +
                        "          app/\n" +
                        "            UserService.java   ← package com.example.app;\n" +
                        "            OrderService.java  ← package com.example.app;\n" +
                        "          model/\n" +
                        "            User.java          ← package com.example.model;\n" +
                        "            Order.java         ← package com.example.model;\n" +
                        "          util/\n" +
                        "            DateHelper.java    ← package com.example.util;\n\n" +
                        "// Plain javac — compile all sources manually:\n" +
                        "javac -d out src/com/example/**/*.java\n" +
                        "java  -cp out com.example.app.Main"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Loading Third-Party Code") {
                    BodyText(
                        "Third-party libraries are distributed as JAR files (Java ARchive) — " +
                        "ZIP archives containing .class files and resources. Two ways to use " +
                        "them:\n\n" +
                        "Manual: download the .jar, place it in a lib/ folder, and add it to " +
                        "the classpath with -cp / -classpath when compiling and running. Tedious " +
                        "for anything beyond a handful of files.\n\n" +
                        "Build tool: declare the dependency by its Maven coordinates " +
                        "(group:artifact:version). Gradle or Maven download the JAR automatically " +
                        "from a repository and put it on the classpath. Transitive dependencies " +
                        "(the library's own dependencies) are handled automatically too."
                    )
                    CodeBlock(
                        "// --- Manual (plain javac) ---\n" +
                        "// Download gson-2.10.1.jar, put it in lib/\n" +
                        "javac -cp lib/gson-2.10.1.jar src/com/example/app/Main.java -d out\n" +
                        "java  -cp out:lib/gson-2.10.1.jar com.example.app.Main\n\n" +
                        "// --- Gradle (build.gradle.kts) ---\n" +
                        "repositories { mavenCentral() }\n\n" +
                        "dependencies {\n" +
                        "    // Gradle downloads this JAR (and its transitive deps) for you\n" +
                        "    implementation(\"com.google.code.gson:gson:2.10.1\")\n" +
                        "}\n\n" +
                        "// --- Maven (pom.xml) ---\n" +
                        "<dependency>\n" +
                        "  <groupId>com.google.code.gson</groupId>\n" +
                        "  <artifactId>gson</artifactId>\n" +
                        "  <version>2.10.1</version>\n" +
                        "</dependency>"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Repositories") {
                    BodyText(
                        "Repositories are servers that host JAR files indexed by Maven " +
                        "coordinates (group:artifact:version).\n\n" +
                        "Maven Central — the primary public repository, hosted at " +
                        "central.sonatype.com. Almost every major open-source Java library " +
                        "publishes here. Free to use.\n\n" +
                        "JitPack — builds and serves any public GitHub / GitLab project on " +
                        "demand, useful for libraries not yet on Maven Central.\n\n" +
                        "Google's Maven repo — required for Android libraries (androidx.*, " +
                        "com.google.android.*).\n\n" +
                        "To find a library's coordinates, search mvnrepository.com or the " +
                        "library's own README."
                    )
                    CodeBlock(
                        "// build.gradle.kts — typical Android/Java project setup\n" +
                        "repositories {\n" +
                        "    mavenCentral()          // most open-source libraries\n" +
                        "    google()                // Android / Google libraries\n" +
                        "    maven(\"https://jitpack.io\")  // GitHub projects\n" +
                        "}\n\n" +
                        "dependencies {\n" +
                        "    // JSON\n" +
                        "    implementation(\"com.google.code.gson:gson:2.10.1\")\n" +
                        "    // HTTP\n" +
                        "    implementation(\"com.squareup.okhttp3:okhttp:4.12.0\")\n" +
                        "    // Logging\n" +
                        "    implementation(\"org.slf4j:slf4j-simple:2.0.9\")\n" +
                        "    // Tests only (not shipped)\n" +
                        "    testImplementation(\"org.junit.jupiter:junit-jupiter:5.10.0\")\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Distributing Your Project") {
                    BodyText(
                        "You distribute a Java application as a JAR (Java ARchive) — a ZIP file " +
                        "containing all your compiled .class files plus resources (images, config " +
                        "files, etc.).\n\n" +
                        "Executable JAR: specify the entry-point class in META-INF/MANIFEST.MF " +
                        "using Main-Class. Users then run it with java -jar MyApp.jar.\n\n" +
                        "Fat JAR / Uber JAR: bundle your code AND all dependency JARs into one " +
                        "file so there are no external classpath dependencies. The Gradle Shadow " +
                        "plugin (or Maven Shade) produces this.\n\n" +
                        "Native image: GraalVM's native-image compiles to a standalone binary " +
                        "with no JVM required (fast startup, smaller memory footprint)."
                    )
                    CodeBlock(
                        "// build.gradle.kts — create an executable JAR\n" +
                        "tasks.jar {\n" +
                        "    manifest {\n" +
                        "        attributes[\"Main-Class\"] = \"com.example.app.Main\"\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// Build:\n" +
                        "// ./gradlew jar\n" +
                        "// Produces: build/libs/MyApp.jar\n\n" +
                        "// Run:\n" +
                        "// java -jar build/libs/MyApp.jar\n\n" +
                        "// MANIFEST.MF (generated inside the JAR):\n" +
                        "// Manifest-Version: 1.0\n" +
                        "// Main-Class: com.example.app.Main\n" +
                        "// Class-Path: lib/gson-2.10.1.jar lib/okhttp-4.12.0.jar\n\n" +
                        "// View what's inside a JAR:\n" +
                        "// jar tf MyApp.jar"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Metadata, Modules, and Signing") {
                    BodyText(
                        "MANIFEST.MF — a plain-text file embedded in every JAR under " +
                        "META-INF/. Records version, main class, classpath, and any custom " +
                        "attributes. Build tools write this for you.\n\n" +
                        "Java 9+ Module System — a module is a JAR with a module-info.java " +
                        "descriptor that declares what it exports (public API) and what it " +
                        "requires (dependencies). Gives strong encapsulation: internal packages " +
                        "are invisible even to reflection by default.\n\n" +
                        "Signing — jarsigner signs a JAR with a private key; users can verify " +
                        "the signature and check that the JAR has not been tampered with. " +
                        "Required for applets (legacy) and certain enterprise/OS deployments."
                    )
                    CodeBlock(
                        "// module-info.java — placed at src/main/java/module-info.java\n" +
                        "module com.example.myapp {\n" +
                        "    requires java.net.http;       // use HttpClient\n" +
                        "    requires com.google.gson;     // use Gson\n\n" +
                        "    // Only this package is accessible to outsiders:\n" +
                        "    exports com.example.app.api;\n\n" +
                        "    // Open to reflection (e.g. for Jackson / Spring):\n" +
                        "    opens com.example.model to com.fasterxml.jackson.databind;\n" +
                        "}\n\n" +
                        "// Sign a JAR\n" +
                        "jarsigner -keystore mykeystore.jks \\\n" +
                        "          -storepass secret       \\\n" +
                        "          MyApp.jar myalias\n\n" +
                        "// Verify\n" +
                        "jarsigner -verify -verbose MyApp.jar"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
