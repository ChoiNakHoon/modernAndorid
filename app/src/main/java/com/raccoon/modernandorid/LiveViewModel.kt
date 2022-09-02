package com.raccoon.modernandorid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveViewModel(_counter: Int) : ViewModel() {

//    var counter = _counter
    // LiveData는 변경이 불가능하기떄문에 Mutable
    var liveCounter : MutableLiveData<Int> = MutableLiveData(_counter)

}