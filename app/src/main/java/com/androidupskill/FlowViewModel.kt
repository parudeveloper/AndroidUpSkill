package com.androidupskill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowViewModel() : ViewModel() {
    var currentPage = 1
    // Normal Flow Example 1
    fun getRandomNumbers(): Flow<Int> = flow {
        for (i in 1..100) {
            delay(1000)
            emit(i)
        }
    }


    init {
        getRandomVValuesWithStateFlow()
        loadPageData()
    }

    // Example 1
    private val _getLatestNumbers = MutableStateFlow(0)
    val getLatestNumbers: StateFlow<Int> get() = _getLatestNumbers

    fun getRandomVValuesWithStateFlow() {
        viewModelScope.launch {
            for (i in 1..100) {
                delay(1000)
                _getLatestNumbers.value = i
            }
        }
    }


    // Example 2
    private val _paging = MutableStateFlow<List<String>>(emptyList())
    val paging: StateFlow<List<String>> get() = _paging

    fun loadPageData() {
        viewModelScope.launch {
            val listData = listOf("Item : ${currentPage * 1}", "Item : ${currentPage * 2}")
            delay(1000)
            _paging.value = listData
            currentPage++
        }
    }
}