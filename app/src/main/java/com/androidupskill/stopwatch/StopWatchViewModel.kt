package com.androidupskill.stopwatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StopWatchViewModel : ViewModel() {

    private val _timer = MutableStateFlow(0)
    val timer: StateFlow<Int> = _timer

    var isRunning = false
    var startTime = 0L

    fun startStopwatch() {
        if (isRunning) return

        isRunning = true
        viewModelScope.launch {
            startTime = System.currentTimeMillis() - _timer.value * 1000
            while (isRunning) {
                val elapsedTime = ((System.currentTimeMillis() - startTime) / 1000).toInt()
                _timer.value = elapsedTime
                delay(1000)
            }
        }

    }

    fun stopStopwatch() {
        isRunning = false
    }

    fun resetStopWatch() {
        isRunning = false
        // startTime = 0L
        _timer.value = 0
    }
    fun getFormattedTime(): String {
        val totalSeconds = _timer.value
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        // Formatting the time to HH:MM:SS format
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}