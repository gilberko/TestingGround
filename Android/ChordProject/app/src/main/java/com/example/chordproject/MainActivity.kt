package com.example.chordproject

import android.Manifest
import android.content.Intent
import androidx.core.content.FileProvider
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.animation.core.Animatable
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontStyle
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

enum class AppState { IDLE, RECORDING, ANALYZING, ANALYZED, PLAYING, PLAYING_MIDI, PLAYING_SIMPLIFIED_MIDI }

private const val HISTORY_SIZE  = 80
private const val WAVEFORM_SIZE = 512
private const val SAMPLE_RATE   = 44100

private fun formatNotes(notes: List<String>): String {
    val regex = Regex("""^([A-G]#?)\((\d+)\)$""")
    val grouped = LinkedHashMap<String, MutableList<String>>()
    for (note in notes) {
        val m = regex.matchEntire(note)
        if (m != null) {
            grouped.getOrPut(m.groupValues[1]) { mutableListOf() }.add(m.groupValues[2])
        } else {
            grouped.getOrPut(note) { mutableListOf() }
        }
    }
    return grouped.entries.joinToString(" ") { (pc, octaves) ->
        if (octaves.isEmpty()) pc else "$pc(${octaves.joinToString(",")})"
    }
}

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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "v1.0",
                fontSize = 13.sp,
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
    var analysisMethod by remember { mutableStateOf(AnalysisMethod.FFT) }
    var frequencyRange by remember { mutableStateOf(FrequencyRange.BOTH) }
    var showSettings   by remember { mutableStateOf(false) }
    var simplifiedResults by remember { mutableStateOf<List<AggregatedFrameResult>?>(null) }
    var editedResults by remember { mutableStateOf<List<AggregatedFrameResult>>(emptyList()) }
    var editingIndex by remember { mutableStateOf(-1) }
    var fingeringIndex by remember { mutableStateOf(-1) }
    var midiJob by remember { mutableStateOf<Job?>(null) }
    var showVerboseInfo by remember { mutableStateOf(false) }
    val isAnyMidiPlaying = appState == AppState.PLAYING_MIDI || appState == AppState.PLAYING_SIMPLIFIED_MIDI
    var detectTempo by remember { mutableStateOf(false) }
    var tempoResult by remember { mutableStateOf<TempoResult?>(null) }

    DisposableEffect(Unit) {
        onDispose {
            recordingJob?.cancel()
            audioRecord?.apply { stop(); release() }
            visualizer?.apply { enabled = false; release() }
            player?.apply { stop(); release() }
            midiJob?.cancel()
        }
    }

    // Amplitude visualizer during WAV playback (taps MediaPlayer's audio session)
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
            simplifiedResults = null
            editedResults = emptyList()
            tempoResult = null
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
        AppState.IDLE         -> "No recording yet."
        AppState.RECORDING    -> "Recording..."
        AppState.ANALYZING    -> "Analyzing..."
        AppState.ANALYZED     -> "Analysis complete."
        AppState.PLAYING      -> "Playing..."
        AppState.PLAYING_MIDI             -> "Playing MIDI..."
        AppState.PLAYING_SIMPLIFIED_MIDI  -> "Playing Simplified MIDI..."
    }

    val barColor = when (appState) {
        AppState.RECORDING    -> Color(0xFFE53935)  // red
        AppState.PLAYING      -> Color(0xFF1E88E5)  // blue
        AppState.ANALYZING    -> Color(0xFFFFA726)  // amber
        AppState.PLAYING_MIDI            -> Color(0xFF7B1FA2)  // purple
        AppState.PLAYING_SIMPLIFIED_MIDI -> Color(0xFF00897B)  // teal
        else                  -> Color(0xFF757575)  // gray
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                "v1.0",
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 4.dp),
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF888888)
            )
            IconButton(
                onClick = { showSettings = true },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = Color(0xFF888888))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(statusText, style = MaterialTheme.typography.bodyLarge)

        if (appState == AppState.ANALYZED || appState == AppState.PLAYING || isAnyMidiPlaying) {
            Text(
                "File size: ${outputFile.length()} bytes",
                style = MaterialTheme.typography.bodySmall,
                color = if (outputFile.length() > 0) Color.Gray else Color.Red
            )
            tempoResult?.let { tr ->
                val highConfidence = tr.confidence >= 0.25f
                Text(
                    if (highConfidence) "Tempo: %.1f BPM".format(tr.bpm)
                    else "Tempo: ~%.0f BPM (low confidence)".format(tr.bpm),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (highConfidence) Color(0xFFFFB300) else Color(0xFF888888),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "Detect Tempo",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Checkbox(
                checked = detectTempo,
                onCheckedChange = { detectTempo = it },
                enabled = appState == AppState.IDLE || appState == AppState.ANALYZED || isAnyMidiPlaying
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

        // ── Row 1: Record | Play | Simplify Melody ──────────────────────────────
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                enabled = appState != AppState.PLAYING && appState != AppState.ANALYZING && !isAnyMidiPlaying,
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
                                AudioAnalyzer.analyze(samples, SAMPLE_RATE, analysisMethod, frequencyRange)
                            }
                            if (detectTempo) {
                                tempoResult = withContext(Dispatchers.Default) {
                                    AudioAnalyzer.detectTempo(samples, SAMPLE_RATE)
                                }
                            } else {
                                tempoResult = null
                            }
                            aggregatedResults = aggregateFrames(rawResults, AudioAnalyzer.hopSizeFor(analysisMethod).toFloat() / SAMPLE_RATE)
                            editedResults = aggregatedResults.toList()
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
                enabled = (appState == AppState.ANALYZED || appState == AppState.PLAYING) && !isAnyMidiPlaying,
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

            Button(
                enabled = appState == AppState.ANALYZED || appState == AppState.PLAYING,
                onClick = {
                    simplifiedResults = if (simplifiedResults != null) null
                    else simplifyMelody(aggregatedResults)
                }
            ) { Text(if (simplifiedResults != null) "Show Full Notes" else "Simplify Melody") }
        }

        // ── Row 2: Play MIDI | Simplified MIDI ──────────────────────────────────
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                enabled = appState == AppState.ANALYZED || appState == AppState.PLAYING_MIDI,
                onClick = {
                    if (appState == AppState.PLAYING_MIDI) {
                        midiJob?.cancel()
                        midiJob = null
                        currentFrameIndex = -1
                        appState = AppState.ANALYZED
                    } else {
                        val segments = simplifiedResults ?: editedResults
                        midiJob = scope.launch(Dispatchers.IO) {
                            withContext(Dispatchers.Main) { appState = AppState.PLAYING_MIDI }
                            MidiSynthesizer.play(
                                segments = segments,
                                sampleRate = SAMPLE_RATE,
                                onFrameChanged = { idx ->
                                    scope.launch(Dispatchers.Main) { currentFrameIndex = idx }
                                },
                                isActive = { isActive }
                            )
                            withContext(Dispatchers.Main) {
                                currentFrameIndex = -1
                                if (appState == AppState.PLAYING_MIDI) appState = AppState.ANALYZED
                            }
                        }
                    }
                }
            ) { Text(if (appState == AppState.PLAYING_MIDI) "Stop MIDI" else "Play MIDI") }

            Button(
                enabled = appState == AppState.ANALYZED || appState == AppState.PLAYING_SIMPLIFIED_MIDI,
                onClick = {
                    if (appState == AppState.PLAYING_SIMPLIFIED_MIDI) {
                        midiJob?.cancel()
                        midiJob = null
                        currentFrameIndex = -1
                        appState = AppState.ANALYZED
                    } else {
                        midiJob = scope.launch(Dispatchers.IO) {
                            withContext(Dispatchers.Main) { appState = AppState.PLAYING_SIMPLIFIED_MIDI }
                            MidiSynthesizer.playSimplified(
                                segments = editedResults,
                                sampleRate = SAMPLE_RATE,
                                onFrameChanged = { idx ->
                                    scope.launch(Dispatchers.Main) { currentFrameIndex = idx }
                                },
                                isActive = { isActive }
                            )
                            withContext(Dispatchers.Main) {
                                currentFrameIndex = -1
                                if (appState == AppState.PLAYING_SIMPLIFIED_MIDI) appState = AppState.ANALYZED
                            }
                        }
                    }
                }
            ) { Text(if (appState == AppState.PLAYING_SIMPLIFIED_MIDI) "Stop Simplified" else "Simplified MIDI") }
        }

        // ── Row 3: Export PDF | Verbose (red) ───────────────────────────────────
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                enabled = (appState == AppState.ANALYZED || appState == AppState.PLAYING)
                          && aggregatedResults.isNotEmpty(),
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        val file = TablaturePdfGenerator.generate(
                            context, simplifiedResults ?: aggregatedResults
                        )
                        withContext(Dispatchers.Main) {
                            val uri = FileProvider.getUriForFile(
                                context, "${context.packageName}.fileprovider", file
                            )
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "application/pdf"
                                putExtra(Intent.EXTRA_STREAM, uri)
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }
                            context.startActivity(Intent.createChooser(intent, "Share Tablature PDF"))
                        }
                    }
                }
            ) { Text("Export PDF") }

            Button(
                enabled = (appState == AppState.ANALYZED || appState == AppState.PLAYING || isAnyMidiPlaying)
                          && aggregatedResults.isNotEmpty(),
                onClick = { showVerboseInfo = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C))
            ) { Text("Verbose Info") }
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
        if (appState == AppState.ANALYZED || appState == AppState.PLAYING || isAnyMidiPlaying) {
            Spacer(modifier = Modifier.height(16.dp))

            // Now Playing card — visible only during playback with a valid frame
            if ((appState == AppState.PLAYING || isAnyMidiPlaying) && currentFrameIndex >= 0) {
                val currentSeg = when (appState) {
                    AppState.PLAYING_MIDI            -> (simplifiedResults ?: editedResults).getOrNull(currentFrameIndex)
                                                            ?: aggregatedResults.getOrNull(currentFrameIndex)
                    AppState.PLAYING_SIMPLIFIED_MIDI -> editedResults.getOrNull(currentFrameIndex)
                    else                             -> aggregatedResults.getOrNull(currentFrameIndex)
                }
                if (currentSeg != null) {
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
                                    formatNotes(currentSeg.notes),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF90CAF9)
                                )
                                Text(
                                    "  (n) = octave",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
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

            val displayResults = simplifiedResults ?: editedResults
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(displayResults.size) { index ->
                    val seg      = displayResults[index]
                    val hasChord = seg.chord != "(no chord)" && seg.chord != "(silence)"
                    val isCurrent = index == currentFrameIndex
                    val isEdited = editedResults.getOrNull(index) != aggregatedResults.getOrNull(index)
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
                            style     = MaterialTheme.typography.bodySmall,
                            color     = when {
                                isEdited && hasChord -> Color(0xFFFFB300)
                                hasChord             -> Color(0xFF66BB6A)
                                else                 -> Color.Gray
                            },
                            fontStyle = if (isEdited) FontStyle.Italic else FontStyle.Normal,
                            modifier  = Modifier
                                .weight(2f)
                                .clickable(enabled = !isAnyMidiPlaying && hasChord) {
                                    fingeringIndex = index
                                }
                        )
                        Text(
                            formatNotes(seg.notes),
                            style    = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .weight(2f)
                                .clickable(enabled = !isAnyMidiPlaying) {
                                    editingIndex = index
                                }
                        )
                    }
                    HorizontalDivider(thickness = 0.5.dp, color = Color(0xFF424242))
                }
            }
        }

        if (showVerboseInfo) {
            VerboseInfoDialog(aggregatedResults) { showVerboseInfo = false }
        }

        if (showSettings) {
            SettingsScreen(
                analysisMethod  = analysisMethod,
                frequencyRange  = frequencyRange,
                onMethodChange  = { analysisMethod = it },
                onRangeChange   = { frequencyRange = it },
                settingsEnabled = appState == AppState.IDLE || appState == AppState.ANALYZED || isAnyMidiPlaying,
                onClose         = { showSettings = false }
            )
        }

        if (editingIndex >= 0) {
            ChordEditDialog(
                segment = editedResults.getOrNull(editingIndex),
                onConfirm = { newChord, newNotes ->
                    editedResults = editedResults.toMutableList().also {
                        it[editingIndex] = it[editingIndex].copy(
                            chord = newChord,
                            notes = newNotes.split(" ").filter { n -> n.isNotBlank() }
                        )
                    }
                    editingIndex = -1
                },
                onDismiss = { editingIndex = -1 }
            )
        }

        if (fingeringIndex >= 0) {
            val fingeringSeg = (simplifiedResults ?: editedResults).getOrNull(fingeringIndex)
            if (fingeringSeg != null) {
                ChordFingeringDialog(
                    chordName = fingeringSeg.chord,
                    onDismiss = { fingeringIndex = -1 }
                )
            } else {
                fingeringIndex = -1
            }
        }
    }
}

@Composable
private fun SettingsScreen(
    analysisMethod  : AnalysisMethod,
    frequencyRange  : FrequencyRange,
    onMethodChange  : (AnalysisMethod) -> Unit,
    onRangeChange   : (FrequencyRange) -> Unit,
    settingsEnabled : Boolean,
    onClose         : () -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1F1F1F))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Settings",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onClose) { Text("Close") }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        "Analysis Method",
                        style      = MaterialTheme.typography.titleSmall,
                        color      = Color(0xFF90CAF9),
                        fontWeight = FontWeight.SemiBold
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        AnalysisMethod.entries.forEach { method ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(enabled = settingsEnabled) { onMethodChange(method) }
                                    .padding(vertical = 4.dp)
                            ) {
                                RadioButton(
                                    selected = analysisMethod == method,
                                    onClick  = { onMethodChange(method) },
                                    enabled  = settingsEnabled
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        method.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color.White
                                    )
                                    Text(
                                        when (method) {
                                            AnalysisMethod.FFT -> "Fast Fourier Transform – low latency, good for broad detection"
                                            AnalysisMethod.CQT -> "Constant-Q Transform – better pitch resolution, especially for bass"
                                        },
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF888888)
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(color = Color(0xFF333333))

                    Text(
                        "Frequency Range",
                        style      = MaterialTheme.typography.titleSmall,
                        color      = Color(0xFF90CAF9),
                        fontWeight = FontWeight.SemiBold
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        FrequencyRange.entries.forEach { range ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(enabled = settingsEnabled) { onRangeChange(range) }
                                    .padding(vertical = 4.dp)
                            ) {
                                RadioButton(
                                    selected = frequencyRange == range,
                                    onClick  = { onRangeChange(range) },
                                    enabled  = settingsEnabled
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        range.name.lowercase().replaceFirstChar { it.uppercase() },
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color.White
                                    )
                                    Text(
                                        when (range) {
                                            FrequencyRange.BASS   -> "~40–500 Hz – bass guitar, cello, upright bass"
                                            FrequencyRange.MELODY -> "~250–2000 Hz – vocals, lead guitar, piano melody"
                                            FrequencyRange.BOTH   -> "~80–2000 Hz – full range (default)"
                                        },
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF888888)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChordEditDialog(
    segment: AggregatedFrameResult?,
    onConfirm: (String, String) -> Unit,
    onDismiss: () -> Unit
) {
    if (segment == null) { onDismiss(); return }
    var chordText by remember { mutableStateOf(segment.chord) }
    var notesText by remember { mutableStateOf(segment.notes.joinToString(" ")) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Segment @ %.2fs".format(segment.startTimeSeconds)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = chordText,
                    onValueChange = { chordText = it },
                    label = { Text("Chord name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    label = { Text("Notes (space-separated)") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(chordText.trim(), notesText.trim()) }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChordFingeringDialog(
    chordName: String,
    onDismiss: () -> Unit
) {
    val parts    = chordName.trim().split(" ", limit = 2)
    val root     = parts.getOrElse(0) { "" }
    val type     = parts.getOrElse(1) { "" }
    val voicings = remember(chordName) { getVoicings(root, type) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1F1F1F))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "$chordName — Fingering",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onDismiss) { Text("Close") }
                }

                if (voicings.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No voicings available for \"$chordName\"",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        voicings.forEach { voicing ->
                            ChordDiagram(voicing = voicing)
                        }
                    }
                    Text(
                        "Tap chord name for fingering  •  Tap notes to edit",
                        color  = Color.Gray,
                        style  = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ChordDiagram(voicing: ChordVoicing) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (voicing.baseFret > 1) {
            Text(
                "${voicing.baseFret}fr",
                color = Color(0xFFCCCCCC),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
        Canvas(
            modifier = Modifier
                .width(140.dp)
                .height(200.dp)
        ) {
            val w = size.width
            val h = size.height

            val topPad   = 30f
            val botPad   = 24f
            val leftPad  = 24f
            val rightPad = 32f

            val gridL = leftPad
            val gridR = w - rightPad
            val gridT = topPad
            val gridB = h - botPad

            val sGap = (gridR - gridL) / 5f
            val fGap = (gridB - gridT) / 5f

            val lineColor   = Color(0xFFAAAAAA)
            val dotColor    = Color(0xFF66BB6A)
            val markerColor = Color(0xFFCCCCCC)

            // Nut or top fret line
            if (voicing.baseFret == 1) {
                drawLine(lineColor, Offset(gridL, gridT), Offset(gridR, gridT), strokeWidth = 5f)
            } else {
                drawLine(lineColor, Offset(gridL, gridT), Offset(gridR, gridT), strokeWidth = 1f)
            }

            // Fret lines (rows 1–5 below nut)
            for (row in 1..5) {
                val y = gridT + row * fGap
                drawLine(lineColor, Offset(gridL, y), Offset(gridR, y), strokeWidth = 1f)
            }

            // String lines
            for (s in 0..5) {
                val x = gridL + s * sGap
                drawLine(lineColor, Offset(x, gridT), Offset(x, gridB), strokeWidth = 1f)
            }

            // Dots, X, O markers
            for (s in 0..5) {
                val f  = voicing.frets[s]
                val sx = gridL + s * sGap
                when {
                    f == -1 -> {
                        drawIntoCanvas { canvas ->
                            val p = android.graphics.Paint().apply {
                                color = markerColor.toArgb()
                                textSize = 26f
                                isAntiAlias = true
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                            canvas.nativeCanvas.drawText("x", sx, gridT - 8f, p)
                        }
                    }
                    f == 0 -> {
                        drawCircle(
                            color  = markerColor,
                            radius = sGap * 0.28f,
                            center = Offset(sx, gridT - sGap * 0.45f),
                            style  = Stroke(width = 2f)
                        )
                    }
                    else -> {
                        val row = f - voicing.baseFret + 1
                        if (row in 1..5) {
                            val dotCy = gridT + (row - 0.5f) * fGap
                            drawCircle(dotColor, radius = sGap * 0.35f, center = Offset(sx, dotCy))
                        }
                    }
                }
            }

            // String name labels below grid
            val stringNames = listOf("E", "A", "D", "G", "B", "e")
            drawIntoCanvas { canvas ->
                val p = android.graphics.Paint().apply {
                    color = markerColor.toArgb()
                    textSize = 22f
                    isAntiAlias = true
                    textAlign = android.graphics.Paint.Align.CENTER
                }
                stringNames.forEachIndexed { s, name ->
                    canvas.nativeCanvas.drawText(name, gridL + s * sGap, gridB + 18f, p)
                }
            }
        }

        Text(
            voicing.label,
            color = Color(0xFF90CAF9),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
private fun VerboseInfoDialog(
    segments: List<AggregatedFrameResult>,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1F1F1F))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Verbose Frequency Info",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onDismiss) { Text("Close") }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                ) {
                    items(segments.size) { index ->
                        val seg = segments[index]
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "%.2f–%.2fs  |  %s  |  %d frames"
                                .format(seg.startTimeSeconds, seg.endTimeSeconds, seg.chord, seg.frameCount),
                            style = MaterialTheme.typography.labelMedium,
                            color = if (seg.chord != "(no chord)" && seg.chord != "(silence)")
                                Color(0xFF66BB6A) else Color.Gray
                        )
                        if (seg.notes.isNotEmpty()) {
                            Text(
                                "Notes: ${formatNotes(seg.notes)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF90CAF9)
                            )
                        }
                        val sorted = seg.peaks.sortedByDescending { it.magnitude }
                        if (sorted.isEmpty()) {
                            Text(
                                "  (no peaks)",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF616161)
                            )
                        } else {
                            sorted.forEach { peak ->
                                Text(
                                    "  %-8s  %7.1f Hz   str: %.5f"
                                        .format(peak.noteName, peak.freqHz, peak.magnitude),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                    color = Color(0xFFCFD8DC)
                                )
                            }
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(top = 6.dp),
                            thickness = 0.5.dp,
                            color = Color(0xFF333333)
                        )
                    }
                }
            }
        }
    }
}
