package com.example.hospitalcompose.ui.pantallas.afinador

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.log2
import kotlin.math.roundToInt

@HiltViewModel
class AfinadorViewModel @Inject constructor() : ViewModel() {

    private var dispatcher: Thread? = null

    private val _uiState = MutableStateFlow(AfinadorContract.State())
    val uiState: StateFlow<AfinadorContract.State> = _uiState.asStateFlow()

    fun onEvent(event: AfinadorContract.Event) {
        when (event) {
            AfinadorContract.Event.Start -> startTuner()
            AfinadorContract.Event.Stop  -> stopTuner()
        }
    }

    private fun startTuner() {
        if (_uiState.value.isTuning) return

        _uiState.update { it.copy(isTuning = true) }

       /* val audioDispatcher = AudioDispatcherFactory.fromDefaultMicrophone(
            22050, 1024, 0
        )

        audioDispatcher.addAudioProcessor(
            PitchProcessor(
                PitchProcessor.PitchEstimationAlgorithm.YIN,
                22050f,
                1024
            ) { result, _ ->
                if (result.pitch > 0) {
                    val (note, cents) = frequencyToNote(result.pitch)
                    _uiState.update {
                        it.copy(
                            pitchHz = result.pitch,
                            note = note,
                            cents = cents
                        )
                    }
                }
            }
        )*/

      /*  dispatcher = Thread { audioDispatcher.run() }.also { it.start() }*/
    }

    private fun stopTuner() {
        dispatcher?.interrupt()
        dispatcher = null
        _uiState.update { it.copy(isTuning = false, pitchHz = 0f) }
    }

    private fun frequencyToNote(freq: Float): Pair<String, Float> {
        val midi = 69 + 12 * log2(freq / 440f)
        val midiRound = midi.roundToInt()
        val noteNames = listOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")
        val note = noteNames[midiRound % 12] + (midiRound / 12 - 1)
        val cents = (midi - midiRound) * 100
        return note to cents
    }
}
