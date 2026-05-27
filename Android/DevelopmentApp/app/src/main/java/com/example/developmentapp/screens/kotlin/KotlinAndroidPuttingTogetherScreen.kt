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
fun KotlinAndroidPuttingTogetherScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Android — Putting It All Together",
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
                SectionCard(title = "The Scenario") {
                    BodyText(
                        "We have an app with:\n\n" +
                        "• A UI (Activity + Jetpack Compose) with two buttons:\n" +
                        "  - \"Read File\" — reads a local text file\n" +
                        "  - \"Fetch Data\" — makes an HTTP GET request\n\n" +
                        "• A status Text that updates with the result\n\n" +
                        "• A notification that fires when the work completes\n\n" +
                        "Walking through how this is built shows every major Android concept " +
                        "working together: Activity, ViewModel, Coroutines, Dispatchers, " +
                        "StateFlow, and NotificationManager."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Architecture — Layers Overview") {
                    BodyText(
                        "Modern Android apps use a layered architecture:\n\n" +
                        "UI Layer  →  Activity / Composable\n" +
                        "  ↕  observes StateFlow, calls ViewModel methods\n" +
                        "ViewModel →  holds state, launches coroutines\n" +
                        "  ↕  calls suspend functions\n" +
                        "Repository →  abstracts data sources\n" +
                        "  ↕\n" +
                        "Data sources: local files, network, database\n\n" +
                        "Data flows UP as StateFlow<UiState>. User events flow DOWN as " +
                        "function calls into the ViewModel. The UI never talks to the " +
                        "repository directly."
                    )
                    CodeBlock(
                        "// UiState — single source of truth for the screen\n" +
                        "data class MainUiState(\n" +
                        "    val status: String = \"Ready\",\n" +
                        "    val isLoading: Boolean = false\n" +
                        ")"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The Activity") {
                    BodyText(
                        "MainActivity is the entry point declared in AndroidManifest.xml with " +
                        "MAIN + LAUNCHER intent filters. It hosts the Compose UI via setContent{}.\n\n" +
                        "The Activity creates (or retrieves) the ViewModel and observes its " +
                        "StateFlow. When the ViewModel emits a new state, Compose re-composes " +
                        "only the affected parts of the UI.\n\n" +
                        "The Activity also holds the application Context, needed to create " +
                        "notification channels and access system services."
                    )
                    CodeBlock(
                        "class MainActivity : ComponentActivity() {\n\n" +
                        "    private val viewModel: MainViewModel by viewModels()\n\n" +
                        "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                        "        super.onCreate(savedInstanceState)\n" +
                        "        createNotificationChannel()  // must exist before posting\n" +
                        "        setContent {\n" +
                        "            val state by viewModel.uiState.collectAsState()\n" +
                        "            MainScreen(\n" +
                        "                state    = state,\n" +
                        "                onReadFile  = { viewModel.readFile(this) },\n" +
                        "                onFetchData = { viewModel.fetchData() }\n" +
                        "            )\n" +
                        "        }\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "The ViewModel") {
                    BodyText(
                        "The ViewModel survives configuration changes (screen rotation) because " +
                        "it is scoped to the Activity's lifecycle, not the Activity instance itself.\n\n" +
                        "It holds a MutableStateFlow<MainUiState> (private) and exposes it as " +
                        "StateFlow<MainUiState> (public, read-only). The UI collects this flow.\n\n" +
                        "viewModelScope is a coroutine scope that is automatically cancelled when " +
                        "the ViewModel is cleared — no manual cleanup needed."
                    )
                    CodeBlock(
                        "class MainViewModel : ViewModel() {\n\n" +
                        "    private val _uiState = MutableStateFlow(MainUiState())\n" +
                        "    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()\n\n" +
                        "    fun readFile(context: Context) {\n" +
                        "        viewModelScope.launch {\n" +
                        "            _uiState.update { it.copy(isLoading = true) }\n" +
                        "            val text = withContext(Dispatchers.IO) {\n" +
                        "                readLocalFile(context)\n" +
                        "            }\n" +
                        "            _uiState.update { it.copy(status = text, isLoading = false) }\n" +
                        "            postNotification(context, \"File read complete\")\n" +
                        "        }\n" +
                        "    }\n\n" +
                        "    fun fetchData() {\n" +
                        "        viewModelScope.launch {\n" +
                        "            _uiState.update { it.copy(isLoading = true) }\n" +
                        "            val result = withContext(Dispatchers.IO) {\n" +
                        "                makeHttpRequest(\"https://example.com/api/data\")\n" +
                        "            }\n" +
                        "            _uiState.update { it.copy(status = result, isLoading = false) }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Coroutines and Dispatchers") {
                    BodyText(
                        "viewModelScope.launch {} starts a coroutine on Dispatchers.Main (the UI " +
                        "thread) by default — safe for updating StateFlow which Compose observes.\n\n" +
                        "withContext(Dispatchers.IO) switches to the IO thread pool for the " +
                        "blocking operation (file read, network call). The coroutine suspends " +
                        "at withContext, does the work on the IO pool, then resumes back on Main " +
                        "automatically — no callbacks, no explicit thread management.\n\n" +
                        "Dispatchers.Default — CPU-intensive work (sorting, parsing large JSON)\n" +
                        "Dispatchers.IO — file I/O, network, database queries (up to 64 threads)\n" +
                        "Dispatchers.Main — update UI, collect StateFlow"
                    )
                    CodeBlock(
                        "// The coroutine starts on Main\n" +
                        "viewModelScope.launch {           // Main thread\n" +
                        "    updateUi(isLoading = true)\n\n" +
                        "    val result = withContext(Dispatchers.IO) {  // IO thread\n" +
                        "        heavyWork()  // runs here, suspends Main\n" +
                        "    }                             // back to Main\n\n" +
                        "    updateUi(result = result)     // Main thread\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Reading a File") {
                    BodyText(
                        "For private app files (written with openFileOutput), use openFileInput() " +
                        "from the Context. For shared storage, use MediaStore or SAF (Storage " +
                        "Access Framework). Both are blocking and must run on Dispatchers.IO.\n\n" +
                        "bufferedReader().use{} automatically closes the stream when done — " +
                        "the use{} extension is the Kotlin equivalent of try-with-resources."
                    )
                    CodeBlock(
                        "private suspend fun readLocalFile(context: Context): String {\n" +
                        "    return try {\n" +
                        "        context.openFileInput(\"data.txt\").bufferedReader().use { reader ->\n" +
                        "            reader.readText()\n" +
                        "        }\n" +
                        "    } catch (e: FileNotFoundException) {\n" +
                        "        \"File not found: \" + e.message\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Making a Network Request") {
                    BodyText(
                        "The standard Android approach is to use the OkHttp library or Retrofit " +
                        "(which wraps OkHttp). For a simple example, HttpURLConnection from the " +
                        "standard library works without additional dependencies.\n\n" +
                        "The network call blocks the thread while waiting for a response — that " +
                        "is why it must run inside withContext(Dispatchers.IO).\n\n" +
                        "Remember to declare INTERNET permission in AndroidManifest.xml."
                    )
                    CodeBlock(
                        "// AndroidManifest.xml:\n" +
                        "// <uses-permission android:name=\"android.permission.INTERNET\"/>\n\n" +
                        "private suspend fun makeHttpRequest(url: String): String {\n" +
                        "    return try {\n" +
                        "        val connection = URL(url).openConnection() as HttpURLConnection\n" +
                        "        connection.connectTimeout = 5_000\n" +
                        "        connection.readTimeout    = 5_000\n" +
                        "        val body = connection.inputStream.bufferedReader().readText()\n" +
                        "        connection.disconnect()\n" +
                        "        body\n" +
                        "    } catch (e: Exception) {\n" +
                        "        \"Error: \" + e.message\n" +
                        "    }\n" +
                        "}\n\n" +
                        "// With OkHttp (add dependency: com.squareup.okhttp3:okhttp)\n" +
                        "val client = OkHttpClient()\n" +
                        "val request = Request.Builder().url(url).build()\n" +
                        "val response = client.newCall(request).execute()  // blocking\n" +
                        "val body = response.body?.string() ?: \"\""
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Showing a Notification") {
                    BodyText(
                        "On Android 8.0+ (API 26+) you must create a NotificationChannel before " +
                        "posting. Do this once at startup (onCreate). Then use NotificationCompat " +
                        "to build the notification and NotificationManager to post it.\n\n" +
                        "On Android 13+ (API 33+) you also need the POST_NOTIFICATIONS runtime " +
                        "permission — request it with ActivityCompat.requestPermissions.\n\n" +
                        "Notifications tie all the pieces together: the coroutine does the work " +
                        "on an IO thread, returns to Main, updates the ViewModel state (which " +
                        "updates the UI), and then posts a notification to inform the user even " +
                        "if they have switched to another app."
                    )
                    CodeBlock(
                        "private fun createNotificationChannel() {\n" +
                        "    val channel = NotificationChannel(\n" +
                        "        \"MAIN_CHANNEL\",\n" +
                        "        \"Main Notifications\",\n" +
                        "        NotificationManager.IMPORTANCE_DEFAULT\n" +
                        "    )\n" +
                        "    getSystemService(NotificationManager::class.java)\n" +
                        "        .createNotificationChannel(channel)\n" +
                        "}\n\n" +
                        "private fun postNotification(context: Context, message: String) {\n" +
                        "    val notification = NotificationCompat.Builder(context, \"MAIN_CHANNEL\")\n" +
                        "        .setSmallIcon(R.drawable.ic_notification)\n" +
                        "        .setContentTitle(\"Work Complete\")\n" +
                        "        .setContentText(message)\n" +
                        "        .setAutoCancel(true)\n" +
                        "        .build()\n\n" +
                        "    NotificationManagerCompat.from(context)\n" +
                        "        .notify(1, notification)\n" +
                        "}\n\n" +
                        "// Flow of one button press:\n" +
                        "// Button click → ViewModel.fetchData()\n" +
                        "//   → viewModelScope.launch (Main)\n" +
                        "//   → withContext(IO) { makeHttpRequest() }  ← suspends\n" +
                        "//   → resumes on Main with result\n" +
                        "//   → _uiState.update { ... }  ← Compose re-renders\n" +
                        "//   → postNotification()  ← user sees notification"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
