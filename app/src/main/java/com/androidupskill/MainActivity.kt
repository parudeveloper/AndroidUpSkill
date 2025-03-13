package com.androidupskill

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androidupskill.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.time.DurationUnit
import kotlin.time.toDuration
// Updated Code
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myException = CoroutineExceptionHandler() { _, exception ->
            binding.tvTextView.text = "Error: ${exception.localizedMessage}"

        }

        var myContext: CoroutineContext = Dispatchers.Main + Job() + myException
        val myScope: CoroutineScope = CoroutineScope(myContext)

        // Scenario 1
        /*   myScope.launch {
               val numbers = flow {
                   repeat(10) {
                       kotlinx.coroutines.delay(2000)
                       emit(it)
                   }
               }

               numbers.collect() {
                   Log.e("MainActivity", "$it")
               }
           }

           myScope.launch {
               val flowOfValues = flowOf(1,2,3,4,5,6,7,8,9,10)

               flowOfValues.collect(){
                   Log.e("MainActivity", it.toString())

               }
           }*/

        // Scenario 2
        /*  myScope.launch {
              getNumberSequence()
                  .map { it * 2 }
                  .flowOn(Dispatchers.IO)
                  .collect() {
                      binding.tvTextView.text = it.toString()

                  }
          }*/

        // Scenario 3
        /* myScope.launch {
             try {
                 getNetworkDataWithTimeOut().collect() { response ->
                     binding.tvTextView.text = response
                 }
             } catch (e: TimeoutCancellationException) {
                 binding.tvTextView.text = "Request Time Out Exception ${e.message} "
             }
         }*/

        viewModel = ViewModelProvider(this).get(FlowViewModel::class.java)

        /*myScope.launch {
            viewModel.getRandomNumbers().collect() {
                binding.tvTextView.text = it.toString()
            }
        }*/

        /*myScope.launch {
            viewModel.getLatestNumbers.collect() {
                binding.tvTextView.text = it.toString()
            }
        }*/

        myScope.launch {
            viewModel.paging.collect() {
                binding.tvTextView.text = it.toString()
            }
        }

    }

    private fun getNumberSequence(): Flow<Int> = flow {
        for (i in 1..10) {
            delay(1000)
            emit(i)
        }

    }

    private fun getNetworkDataWithTimeOut(): Flow<String> = flow {
        delay(5000)
        emit("Data Fetched Successfully")
    }.timeout(2.toDuration(DurationUnit.SECONDS))

}