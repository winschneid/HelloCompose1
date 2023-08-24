package com.example.hellocompose1.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleViewModel: ViewModel() {
    val count = MutableLiveData(0)

    fun countUp() {
        val c = count.value ?: 0
        count.value = c + 1
    }
}