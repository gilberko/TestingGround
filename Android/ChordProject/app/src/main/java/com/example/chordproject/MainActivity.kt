package com.example.chordproject

import android.Manifest
import android.media.AudioFormat
import android.media.AudioRecord
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Box
import androidx.compose.animation.core.Animatable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import com.example.chordproject.ui.theme.ChordProjectTheme
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.abs
import kotlin.math.sqrt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class AppState { IDLE, RECORDING, ANALYZING, ANALYZED, PLAYING }

private const val HISTORY_SIZE  = 80
private const val WAVEFORM_SIZE = 512
private const val SAMPLE_RATE   = 44100

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChordProjectTheme {
                var showSplash by remember { mutableStateOf(true) }
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(2000)
                    showSplash = false
                }
                if (showSplash) {
                    SplashScreen()
                } else {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        AudioRecorderScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

private fun writeWavFile(file: File, samples: ShortArray, sampleRate: Int) {
    val numChannels    = 1
    val bitsPerSample  = 16
    val byteRate       = sampleRate * numChannels * bitsPerSample / 8
    val blockAlign     = numChannels * bitsPerSample / 8
    val dataSize       = samples.size * 2          // bytes
    val riffChunkSize  = 36 + dataSize              // total file size minus 8

    val buf = ByteBuffer.allocate(44 + dataSize).order(ByteOrder.LITTLE_ENDIAN)
    buf.put("RIFF".toByteArray())
    buf.putInt(riffChunkSize)
    buf.put("WAVE".toByteArray())
    buf.put("fmt ".toByteArray())
    buf.putInt(16)                          // fmt chunk size
    buf.putShort(1)                         // PCM format
    buf.putShort(numChannels.toShort())
    buf.putInt(sampleRate)
    buf.putInt(byteRate)
    buf.putShort(blockAlign.toShort())
    buf.putShort(bitsPerSample.toShort())
    buf.put("data".toByteArray())
    buf.putInt(dataSize)
    for (s in samples) buf.putShort(s)

    FileOutputStream(file).use { it.write(buf.array()) }
}

@Composable
fun SplashScreen() {
    val alpha = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = androidx.compose.animation.core.tween(600))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))
            .alpha(alpha.value),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Guitar icon",
                modifier = Modifier
                    .background(Color(0xFF1A1A1A), shape = androidx.compose.foundation.shape.CircleShape)
                    .padding(16.dp)
                    .height(160.dp)
                    .fillMaxWidth(0.45f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "CHORD",
                fontSize = 52.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 8.sp,
                color = Color(0xFFE53935)
            )
            Text(
                "ANALYZER",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 12.sp,
                color = Color(0xFFFFFFFF)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "♩ ♪ ♫ ♬",
                fontSize = 22.sp,
                color = Color(0xFF888888)
            )
        }
    }
}

@Composable
fun AudioRecorderScreen(modifier: Modifier = Modifier) {
    val context    = LocalContext.current
    val outputFile = remember { File(context.cacheDir, "recording.wav") }
    val scope      = rememberCoroutineScope()

    var appState        by remember { mutableStateOf(AppState.IDLE) }
    var permissionDenied by remember { mutableStateOf(false) }
    var audioRecord     by remember { mutableStateOf<AudioRecord?>(null) }
    var player          by remember { mutableStateOf<MediaPlayer?>(null) }
    var visualizer      by remember { mutableStateOf<Visualizer?>(null) }
    var amplitudeHistory by remember { mutableStateOf(FloatArray(HISTORY_SIZE)) }
    var waveformSamples  by remember { mutableStateOf(FloatArray(WAVEFORM_SIZE)) }
    var aggregatedResults by remember { mutableStateOf<List<AggregatedFrameResult>>(emptyList()) }
    val recordedChunks  = remember { mutableListOf<ShortArray>() }
    var recordingJob      by remember { mutableStateOf<Job?>(null) }
    var currentFrameIndex by remember { mutableStateOf(-1) }

    DisposableEffect(Unit) {
        onDispose {
            recordingJob?.cancel()
            audioRecord?.apply { stop(); release() }
            visualizer?.apply { enabled = false; release() }
            player?.apply { stop(); release() }
        }
    }

    // Amplitude visualizer during playback (taps MediaPlayer's audio session)
    LaunchedEffect(appState) {
        if (appState == AppState.PLAYING) {
            val vis = visualizer ?: return@LaunchedEffect
            val waveform = ByteArray(vis.captureSize)
            while (true) {
                kotlinx.coroutines.delay(80)
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
                val posMs = player?.currentPosition ?: 0
                val posSec = posMs / 1000f
                currentFrameIndex = aggregatedResults.indexOfLast { it.startTimeSeconds <= posSec }
            }
        } else {
            currentFrameIndex = -1
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(RequestPermission()) { granted ->
        if (granted) {
            permissionDenied = false
            val minBufSize = AudioRecord.getMinBufferSize(
                SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT
            )
            val bufSize = minBufSize.coerceAtLeast(16384)
            val ar = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufSize
            )
            ar.startRecording()
            audioRecord = ar
            recordedChunks.clear()
            aggregatedResults = emptyList()
            amplitudeHistory = FloatArray(HISTORY_SIZE)
            waveformSamples  = FloatArray(WAVEFORM_SIZE)
            appState = AppState.RECORDING

            val readSize = bufSize / 2  // shorts per read
            val job = scope.launch(Dispatchers.IO) {
                val buf = ShortArray(readSize)
                while (isActive) {
                    val read = ar.read(buf, 0, readSize)
                    if (read <= 0) break
                    val chunk = buf.copyOf(read)
                    recordedChunks.add(chunk)
                    // Compute RMS for amplitude bar graph
                    var sumSq = 0.0
                    for (s in chunk) { val v = s.toFloat() / 32768f; sumSq += v * v }
                    val rms = sqrt(sumSq / read).toFloat()
                    // Downsample chunk to WAVEFORM_SIZE points for oscilloscope display
                    val stride = maxOf(1, read / WAVEFORM_SIZE)
                    val wf = FloatArray(WAVEFORM_SIZE) { i ->
                        buf[minOf(i * stride, read - 1)].toFloat() / 32768f
                    }
                    withContext(Dispatchers.Main) {
                        val prev = amplitudeHistory
                        amplitudeHistory = FloatArray(HISTORY_SIZE) { i ->
                            if (i < HISTORY_SIZE - 1) prev[i + 1] else rms.coerceIn(0f, 1f)
                        }
                        waveformSamples = wf
                    }
                }
            }
            recordingJob = job
        } else {
            permissionDenied = true
        }
    }

    val statusText = when (appState) {
        AppState.IDLE      -> "No recording yet."
        AppState.RECORDING -> "Recording..."
        AppState.ANALYZING -> "Analyzing..."
        AppState.ANALYZED  -> "Analysis complete."
        AppState.PLAYING   -> "Playing..."
    }

    val barColor = when (appState) {
        AppState.RECORDING -> Color(0xFFE53935)  // red
        AppState.PLAYING   -> Color(0xFF1E88E5)  // blue
        AppState.ANALYZING -> Color(0xFFFFA726)  // amber
        else               -> Color(0xFF757575)  // gray
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(statusText, style = MaterialTheme.typography.bodyLarge)

        if (appState == AppState.ANALYZED || appState == AppState.PLAYING) {
            Text(
                "File size: ${outputFile.length()} bytes",
                style = MaterialTheme.typography.bodySmall,
                color = if (outputFile.length() > 0) Color.Gray else Color.Red
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1A1A1A))
        ) {
            if (appState == AppState.RECORDING) {
                // Oscilloscope waveform
                val midY = size.height / 2f
                drawLine(Color(0xFF616161), Offset(0f, midY), Offset(size.width, midY), strokeWidth = 1f)
                val path = Path()
                val step = size.width / waveformSamples.size
                waveformSamples.forEachIndexed { i, s ->
                    val x = i * step
                    val y = midY - s * midY * 0.9f
                    if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                drawPath(path, color = barColor, style = Stroke(width = 2f))
            } else {
                // Amplitude bar graph
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                enabled = appState != AppState.PLAYING && appState != AppState.ANALYZING,
                onClick = {
                    if (appState == AppState.RECORDING) {
                        // Stop recording: halt AudioRecord then join the coroutine
                        val ar  = audioRecord
                        val job = recordingJob
                        audioRecord  = null
                        recordingJob = null
                        scope.launch {
                            ar?.stop()
                            job?.cancel()
                            job?.join()
                            ar?.release()

                            // Concatenate all PCM chunks
                            val total   = recordedChunks.sumOf { it.size }
                            val samples = ShortArray(total)
                            var pos     = 0
                            for (chunk in recordedChunks) {
                                chunk.copyInto(samples, pos)
                                pos += chunk.size
                            }
                            writeWavFile(outputFile, samples, SAMPLE_RATE)

                            appState = AppState.ANALYZING
                            val rawResults = withContext(Dispatchers.Default) {
                                AudioAnalyzer.analyze(samples, SAMPLE_RATE)
                            }
                            aggregatedResults = aggregateFrames(rawResults, AudioAnalyzer.HOP_SIZE.toFloat() / SAMPLE_RATE)
                            appState = AppState.ANALYZED
                        }
                    } else {
                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
                }
            ) {
                Text(if (appState == AppState.RECORDING) "Stop Recording" else "Record")
            }

            Button(
                enabled = appState == AppState.ANALYZED || appState == AppState.PLAYING,
                onClick = {
                    if (appState == AppState.PLAYING) {
                        visualizer?.apply { enabled = false; release() }
                        visualizer = null
                        player?.apply { stop(); release() }
                        player = null
                        currentFrameIndex = -1
                        appState = AppState.ANALYZED
                    } else {
                        val mp = MediaPlayer().apply {
                            setDataSource(outputFile.absolutePath)
                            prepare()
                            setOnCompletionListener {
                                it.release()
                                player = null
                                visualizer?.apply { enabled = false; release() }
                                visualizer = null
                                currentFrameIndex = -1
                                appState = AppState.ANALYZED
                            }
                            start()
                        }
                        val vis = try {
                            Visualizer(mp.audioSessionId).apply {
                                captureSize = Visualizer.getCaptureSizeRange()[0]
                                enabled = true
                            }
                        } catch (e: Exception) { null }
                        visualizer = vis
                        player = mp
                        appState = AppState.PLAYING
                    }
                }
            ) {
                Text(if (appState == AppState.PLAYING) "Stop Playing" else "Play")
            }
        }

        if (permissionDenied) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Microphone permission denied.",
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // ── Timeline ──────────────────────────────────────────────────────────
        if (appState == AppState.ANALYZED || appState == AppState.PLAYING) {
            Spacer(modifier = Modifier.height(16.dp))

            // Now Playing card — visible only during playback with a valid frame
            if (appState == AppState.PLAYING && currentFrameIndex >= 0) {
                val currentSeg = aggregatedResults[currentFrameIndex]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(Color(0xFF0D47A1), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            "NOW PLAYING",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF90CAF9)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row {
                            Text("Chord:  ", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                            Text(
                                currentSeg.chord,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF66BB6A)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text("Notes: ", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                            Text(
                                currentSeg.notes.joinToString(" "),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF90CAF9)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Header row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF424242))
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text("Time",  color = Color.White, style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(1f))
                Text("Chord", color = Color.White, style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(2f))
                Text("Notes", color = Color.White, style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(2f))
            }

            val listState = rememberLazyListState()
            LaunchedEffect(currentFrameIndex) {
                if (currentFrameIndex >= 0)
                    listState.animateScrollToItem(currentFrameIndex)
            }

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(aggregatedResults.size) { index ->
                    val seg      = aggregatedResults[index]
                    val hasChord = seg.chord != "(no chord)"
                    val isCurrent = index == currentFrameIndex
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                when {
                                    isCurrent -> Color(0xFF0D47A1).copy(alpha = 0.4f)
                                    hasChord  -> Color(0xFF1B5E20).copy(alpha = 0.35f)
                                    else      -> Color(0xFF424242).copy(alpha = 0.10f)
                                }
                            )
                            .padding(horizontal = 16.dp, vertical = 3.dp)
                    ) {
                        Text(
                            "%.2fs".format(seg.startTimeSeconds),
                            style    = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            seg.chord,
                            style    = MaterialTheme.typography.bodySmall,
                            color    = if (hasChord) Color(0xFF66BB6A) else Color.Gray,
                            modifier = Modifier.weight(2f)
                        )
                        Text(
                            seg.notes.joinToString(" "),
                            style    = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.weight(2f)
                        )
                    }
                    HorizontalDivider(thickness = 0.5.dp, color = Color(0xFF424242))
                }
            }
        }
    }
}
