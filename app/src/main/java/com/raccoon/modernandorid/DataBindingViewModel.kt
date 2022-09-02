package com.raccoon.modernandorid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DataBindingViewModel(
    _counter: Int
) : ViewModel() {

    var liveCounter: MutableLiveData<Int> = MutableLiveData(_counter)

    val modifiedCounter: LiveData<String> = Transformations.map(liveCounter) { counter ->
        "${counter} 입니다."
    }

    val hasChecked: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
}