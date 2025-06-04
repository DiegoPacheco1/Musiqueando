import java.io.File

interface GrabadoraContract {
    sealed class Event {
        object StartRecording : Event()
        object StopRecording  : Event()
        data class Play(val file: File) : Event()
    }

    data class State(
        val isRecording: Boolean = false,
        val timerSec: Long    = 0,
        val recordings: List<File> = emptyList()
    )
}
