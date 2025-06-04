package com.example.hospitalcompose.ui.pantallas.grabadora

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalcompose.data.remote.NetworkResult
import com.example.hospitalcompose.domain.usecases.grabaciones.InsertarGrabacionUseCase
import com.example.hospitalcompose.domain.usecases.grabaciones.ObtenerGrabacionesUseCase
import com.example.hospitalcompose.ui.utils.common.UiEvent
import com.example.hospitalcompose.ui.utils.common.getRecordingsDir
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class GrabadoraViewModel @Inject constructor(
    @ApplicationContext private val ctx: Context
): ViewModel() {
    private val _ui = MutableStateFlow(GrabadoraContract.State())
    val ui: StateFlow<GrabadoraContract.State> = _ui

    private var recorder: MediaRecorder? = null
    private var player:   MediaPlayer?   = null
    private var timerJob: Job? = null
    private var currentFile: File? = null

    fun handle(event: GrabadoraContract.Event) {
        when(event) {
            GrabadoraContract.Event.StartRecording -> start()
            GrabadoraContract.Event.StopRecording  -> stop()
            is GrabadoraContract.Event.Play       -> play(event.file)
            else -> {}
        }
    }

    private fun start() {
        // crea fichero único
        val f = File(getRecordingsDir(ctx),
            "grab_${System.currentTimeMillis()}.mp4")
        currentFile = f

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(f.absolutePath)
            prepare()
            start()
        }
        _ui.update { it.copy(isRecording = true, timerSec = 0) }
        timerJob = viewModelScope.launch {
            while(true) {
                delay(1000)
                _ui.update { st -> st.copy(timerSec = st.timerSec + 1) }
            }
        }
    }

    private fun stop() {
        recorder?.run {
            stop(); release()
        }
        recorder = null
        timerJob?.cancel()

        // refresca lista de ficheros
        val files = getRecordingsDir(ctx)
            .listFiles()
            .orEmpty()
            .sortedByDescending { it.lastModified() }

        _ui.update {
            it.copy(
                isRecording = false,
                timerSec = 0,
                recordings = files
            )
        }
    }

    private fun play(file: File) {
        player?.release()
        player = MediaPlayer().apply {
            setDataSource(file.absolutePath)
            prepare()
            start()
        }
    }
}

