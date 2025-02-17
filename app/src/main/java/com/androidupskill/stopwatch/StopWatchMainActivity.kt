package com.androidupskill.stopwatch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.androidupskill.R
import com.androidupskill.databinding.ActivityStopWatchMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class StopWatchMainActivity : AppCompatActivity() {
    lateinit var viewModel: StopWatchViewModel
    lateinit var binding: ActivityStopWatchMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopWatchMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[StopWatchViewModel::class.java]


        lifecycleScope.launch {
            viewModel.timer.collect() {
                binding.tvTime.text = viewModel.getFormattedTime()
            }
        }
        binding.btnStart.setOnClickListener() {
            viewModel.startStopwatch()
        }

        binding.btnStop.setOnClickListener() {
            viewModel.stopStopwatch()
        }

        binding.btnReset.setOnClickListener() {
            viewModel.resetStopWatch()
        }

    }
}