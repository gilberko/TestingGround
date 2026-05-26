package com.example.developmentapp.screens.kotlin

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
fun KotlinAndroid101Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Android 101",
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("What Is Android") {
                    BodyText(
                        "Android is an operating system built on the Linux kernel, designed primarily " +
                        "for touchscreen devices. Every Android app runs inside its own sandboxed " +
                        "process on the Android Runtime (ART), which compiles Kotlin/Java bytecode to " +
                        "native machine code ahead-of-time (AOT).\n\n" +
                        "An app is packaged as an APK (Android Package) or the newer AAB (Android App " +
                        "Bundle). The Play Store distributes AABs and generates device-specific APKs " +
                        "on the fly, reducing download size.\n\n" +
                        "Android versions are identified by API level numbers (e.g. Android 14 = API 34). " +
                        "You set minSdk (minimum device requirement) and targetSdk (what you tested against) " +
                        "in build.gradle."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Activity") {
                    BodyText(
                        "An Activity is the primary building block of Android UI. Each screen the user " +
                        "sees is typically one Activity. The system manages a back stack of Activities; " +
                        "pressing Back pops the top one.\n\n" +
                        "Every Activity has a lifecycle. The system calls these methods as the user " +
                        "navigates in and out of your app:"
                    )
                    CodeBlock(
                        """
onCreate()   // first creation — set up UI, initialize data
onStart()    // becoming visible
onResume()   // in the foreground, receiving input
  ↕ (user interaction)
onPause()    // losing focus (another Activity coming in front)
onStop()     // no longer visible
onDestroy()  // being finished or destroyed by system
                        """.trimIndent()
                    )
                    BodyText(
                        "The most important method is onCreate(). In the classic View system you call " +
                        "setContentView(R.layout.my_layout) here. In Jetpack Compose you call setContent { } " +
                        "instead.\n\n" +
                        "One Activity can host many Fragments (reusable UI pieces), or with Compose you " +
                        "typically have a single Activity hosting all composable screens."
                    )
                    CodeBlock(
                        """
// Classic View
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

// Jetpack Compose
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme { MyNavGraph() }
        }
    }
}
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Intent") {
                    BodyText(
                        "An Intent is a messaging object used to request an action from another component. " +
                        "It is how you start Activities, Services, and send broadcasts.\n\n" +
                        "Explicit Intent — you name the exact target class:"
                    )
                    CodeBlock(
                        """
val intent = Intent(this, DetailActivity::class.java)
intent.putExtra("item_id", 42)
startActivity(intent)

// In DetailActivity:
val id = intent.getIntExtra("item_id", 0)
                        """.trimIndent()
                    )
                    BodyText("Implicit Intent — you describe the action; the system picks a handler:")
                    CodeBlock(
                        """
// Open a URL in the browser
val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://example.com"))
startActivity(intent)

// Share text
val share = Intent(Intent.ACTION_SEND)
share.type = "text/plain"
share.putExtra(Intent.EXTRA_TEXT, "Hello!")
startActivity(Intent.createChooser(share, "Share via"))
                        """.trimIndent()
                    )
                    BodyText(
                        "PendingIntent wraps an Intent for future or remote delivery — used for " +
                        "notifications (tapping a notification fires the PendingIntent) and alarms."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Fragment") {
                    BodyText(
                        "A Fragment is a reusable UI sub-component that lives inside an Activity. It has " +
                        "its own layout and lifecycle, which mirrors the Activity lifecycle but also has " +
                        "onCreateView() (inflate the Fragment's layout) and onDestroyView().\n\n" +
                        "Fragments are managed by the FragmentManager. You add, replace, or remove them " +
                        "via a FragmentTransaction:"
                    )
                    CodeBlock(
                        """
supportFragmentManager.beginTransaction()
    .replace(R.id.container, DetailFragment.newInstance(id))
    .addToBackStack(null)  // pressing Back will pop this
    .commit()
                        """.trimIndent()
                    )
                    BodyText(
                        "In modern Android development with Jetpack Compose, Fragments are largely " +
                        "replaced by composable functions navigated with the Navigation Compose library. " +
                        "However, Fragments are still widely used in View-based apps and you will " +
                        "encounter them frequently in existing codebases."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Context") {
                    BodyText(
                        "Context is arguably the most important class in Android. It provides access to " +
                        "application resources, the file system, system services, and the ability to " +
                        "start Activities and Services.\n\n" +
                        "There are two main kinds:"
                    )
                    CodeBlock(
                        """
// Application context — lives for the entire app lifetime
val appContext = applicationContext
// Use for: database, file I/O, system services in singletons

// Activity context — tied to the Activity lifecycle
val activityContext = this  // inside an Activity
// Use for: inflating layouts, showing dialogs, starting Activities
                        """.trimIndent()
                    )
                    BodyText(
                        "Leaking an Activity context is a classic Android bug. If you store a reference " +
                        "to an Activity context in a long-lived object (singleton, static field), the " +
                        "Activity cannot be garbage collected even after it is destroyed. Always use " +
                        "applicationContext in singletons and long-lived objects."
                    )
                    CodeBlock(
                        """
// Common uses
val res = context.getString(R.string.app_name)
val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
val connectivity = context.getSystemService(ConnectivityManager::class.java)
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("ViewModel") {
                    BodyText(
                        "A ViewModel is a lifecycle-aware class designed to hold and manage UI-related " +
                        "data. Its key feature: it survives configuration changes such as screen rotation. " +
                        "When the Activity is recreated, the same ViewModel instance is returned.\n\n" +
                        "Without ViewModel, you would lose all your loaded data on every rotation."
                    )
                    CodeBlock(
                        """
class CounterViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    fun increment() { _count.value++ }
}

// In Activity / Composable:
val vm: CounterViewModel by viewModels()
val count by vm.count.collectAsState()
                        """.trimIndent()
                    )
                    BodyText(
                        "ViewModel is the V-M part of the MVVM (Model-View-ViewModel) architecture " +
                        "recommended by Google. The View (Activity/Composable) observes StateFlow or " +
                        "LiveData from the ViewModel and reacts to changes. The ViewModel talks to " +
                        "repositories and data sources but never holds a reference to the View."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("AndroidManifest.xml") {
                    BodyText(
                        "Every Android app has an AndroidManifest.xml at its root. This file is the " +
                        "app's declaration to the Android system. Every component (Activity, Service, " +
                        "BroadcastReceiver, ContentProvider) must be listed here or the system will not " +
                        "know it exists and it cannot be launched.\n\n" +
                        "Key things declared in the manifest:"
                    )
                    CodeBlock(
                        """
<manifest package="com.example.myapp">

    <!-- Permissions the app needs -->
    <uses-permission android:name="android.permission.INTERNET"/>

    <application
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name">

        <!-- The entry-point Activity -->
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>

        <!-- Other activities, services, receivers -->
        <activity android:name=".DetailActivity"/>
        <service android:name=".MyService"/>

    </application>
</manifest>
                        """.trimIndent()
                    )
                    BodyText(
                        "android:name must match the fully qualified class name (or use a dot shorthand " +
                        "relative to the package). Missing a component from the manifest is a very " +
                        "common mistake that causes an ActivityNotFoundException at runtime."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Service & BroadcastReceiver") {
                    BodyText(
                        "A Service runs background work without a UI. Examples: playing music, downloading " +
                        "a file, syncing data. A started Service runs until it stops itself or is stopped " +
                        "explicitly. A bound Service allows other components to bind to it and call its methods.\n\n" +
                        "A BroadcastReceiver listens for system-wide or app-wide broadcast events:"
                    )
                    CodeBlock(
                        """
// Register in Manifest for system broadcasts:
<receiver android:name=".BootReceiver">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED"/>
    </intent-filter>
</receiver>

// Or register dynamically for app broadcasts:
val filter = IntentFilter("com.example.MY_EVENT")
registerReceiver(myReceiver, filter)
                        """.trimIndent()
                    )
                    BodyText(
                        "Modern Android (Doze mode, App Standby, battery restrictions) has strict limits " +
                        "on background execution. For reliable background tasks that need to survive " +
                        "process death and device restarts, use WorkManager — it handles constraints " +
                        "(network, charging), retries, and is compatible with power-saving restrictions."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("View vs Jetpack Compose") {
                    BodyText(
                        "Android has two UI toolkits and you will encounter both:\n\n" +
                        "Classic View System — UI is defined in XML layout files. At runtime you " +
                        "inflate the layout and find widgets by ID:"
                    )
                    CodeBlock(
                        """
// res/layout/activity_main.xml
<TextView
    android:id="@+id/myText"
    android:text="Hello" />

// In Activity:
val tv = findViewById<TextView>(R.id.myText)
tv.text = "World"
                        """.trimIndent()
                    )
                    BodyText("Jetpack Compose — UI is written in Kotlin as composable functions. No XML:")
                    CodeBlock(
                        """
@Composable
fun Greeting(name: String) {
    var text by remember { mutableStateOf("Hello") }
    Column {
        Text(text = "${'$'}text, ${'$'}name")
        Button(onClick = { text = "Hi" }) { Text("Click me") }
    }
}
                        """.trimIndent()
                    )
                    BodyText(
                        "In Compose, when state changes the framework re-calls (recomposes) only the " +
                        "affected composables — no manual view.invalidate() or notifyDataSetChanged().\n\n" +
                        "Both systems can coexist in the same app. You can embed Compose inside a View " +
                        "with ComposeView, or embed a classic View inside Compose with AndroidView. " +
                        "Google now recommends Compose for all new Android UI development."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
