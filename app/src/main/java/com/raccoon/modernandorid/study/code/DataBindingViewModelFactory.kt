package com.raccoon.modernandorid.study.code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DataBindingViewModelFactory(private val counter : Int ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataBindingViewModel::class.java)) {
            return DataBindingViewModel(counter) as T
        }

        throw IllegalArgumentException("Viewmodel class not found")
    }
}