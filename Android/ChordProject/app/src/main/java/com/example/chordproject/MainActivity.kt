package com.example.chordproject

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.media.audiofx.Visualizer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.chordproject.ui.theme.ChordProjectTheme
import java.io.File
import kotlin.math.abs
import kotlinx.coroutines.delay

enum class AppState { IDLE, RECORDING, RECORDED, PLAYING }

private const val HISTORY_SIZE = 80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChordProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AudioRecorderScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AudioRecorderScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val outputFile = remember { File(context.cacheDir, "recording.3gp") }

    var appState by remember { mutableStateOf(AppState.IDLE) }
    var permissionDenied by remember { mutableStateOf(false) }
    var recorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var player by remember { mutableStateOf<MediaPlayer?>(null) }
    var visualizer by remember { mutableStateOf<Visualizer?>(null) }
    var amplitudeHistory by remember { mutableStateOf(FloatArray(HISTORY_SIZE)) }

    DisposableEffect(Unit) {
        onDispose {
            recorder?.apply { stop(); release() }
            recorder = null
            visualizer?.apply { enabled = false; release() }
            visualizer = null
            player?.apply { stop(); release() }
            player = null
        }
    }

    // Poll amplitude on every state change that involves audio activity.
    LaunchedEffect(appState) {
        when (appState) {
            AppState.RECORDING -> {
                while (true) {
                    delay(80)
                    val amp = (recorder?.maxAmplitude?.toFloat() ?: 0f) / 32767f
                    val prev = amplitudeHistory
                    amplitudeHistory = FloatArray(HISTORY_SIZE) { i ->
                        if (i < HISTORY_SIZE - 1) prev[i + 1] else amp.coerceIn(0f, 1f)
                    }
                }
            }
            AppState.PLAYING -> {
                val vis = visualizer ?: return@LaunchedEffect
                val waveform = ByteArray(vis.captureSize)
                while (true) {
                    delay(80)
                    val amp = try {
                        vis.getWaveForm(waveform)
                        waveform.map { abs((it.toInt() and 0xFF) - 128) }.average().toFloat() / 128f
                    } catch (e: Exception) {
                        break
                    }
                    val prev = amplitudeHistory
                    amplitudeHistory = FloatArray(HISTORY_SIZE) { i ->
                        if (i < HISTORY_SIZE - 1) prev[i + 1] else amp.coerceIn(0f, 1f)
                    }
                }
            }
            else -> {}
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(RequestPermission()) { granted ->
        if (granted) {
            permissionDenied = false
            val mr = MediaRecorder(context).apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(outputFile.absolutePath)
                prepare()
                start()
            }
            recorder = mr
            appState = AppState.RECORDING
        } else {
            permissionDenied = true
        }
    }

    val statusText = when (appState) {
        AppState.IDLE -> "No recording yet."
        AppState.RECORDING -> "Recording..."
        AppState.RECORDED -> "Recording ready."
        AppState.PLAYING -> "Playing..."
    }

    val barColor = when (appState) {
        AppState.RECORDING -> Color(0xFFE53935) // red
        AppState.PLAYING   -> Color(0xFF1E88E5) // blue
        else               -> Color(0xFF757575) // gray
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Audio Recorder", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(statusText, style = MaterialTheme.typography.bodyLarge)
        if (appState == AppState.RECORDED || appState == AppState.PLAYING) {
            Text(
                "File size: ${outputFile.length()} bytes",
                style = MaterialTheme.typography.bodySmall,
                color = if (outputFile.length() > 0) Color.Gray else Color.Red
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1A1A1A))
        ) {
            val barWidth = size.width / HISTORY_SIZE
            amplitudeHistory.forEachIndexed { i, amp ->
                val barHeight = (size.height * amp).coerceAtLeast(1f)
                drawRect(
                    color = barColor,
                    topLeft = Offset(i * barWidth, size.height - barHeight),
                    size = Size((barWidth - 1f).coerceAtLeast(1f), barHeight)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            enabled = appState != AppState.PLAYING,
            onClick = {
                if (appState == AppState.RECORDING) {
                    recorder?.apply { stop(); release() }
                    recorder = null
                    appState = AppState.RECORDED
                } else {
                    permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            }
        ) {
            Text(if (appState == AppState.RECORDING) "Stop Recording" else "Record")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            enabled = appState == AppState.RECORDED || appState == AppState.PLAYING,
            onClick = {
                if (appState == AppState.PLAYING) {
                    visualizer?.apply { enabled = false; release() }
                    visualizer = null
                    player?.apply { stop(); release() }
                    player = null
                    appState = AppState.RECORDED
                } else {
                    val mp = MediaPlayer().apply {
                        setDataSource(outputFile.absolutePath)
                        prepare()
                        setOnCompletionListener {
                            it.release()
                            player = null
                            visualizer?.apply { enabled = false; release() }
                            visualizer = null
                            appState = AppState.RECORDED
                        }
                        start()
                    }
                    // Visualizer taps into the MediaPlayer's audio session to read
                    // the waveform of whatever is being rendered to the speaker.
                    val vis = try {
                        Visualizer(mp.audioSessionId).apply {
                            captureSize = Visualizer.getCaptureSizeRange()[0]
                            enabled = true
                        }
                    } catch (e: Exception) {
                        null
                    }
                    visualizer = vis
                    player = mp
                    appState = AppState.PLAYING
                }
            }
        ) {
            Text(if (appState == AppState.PLAYING) "Stop Playing" else "Play")
        }

        if (permissionDenied) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Microphone permission denied.",
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
