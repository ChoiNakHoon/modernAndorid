package com.raccoon.modernandorid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class LiveViewModelFactory(private val counter : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LiveViewModel::class.java)) {
            return LiveViewModel(counter) as T
        }

        throw IllegalArgumentException("viewmodel class not found")
    }
}