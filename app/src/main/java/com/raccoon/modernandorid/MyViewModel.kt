package com.raccoon.modernandorid

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// 팩토리에서 보낸 인자값을 받음
class MyViewModel(
    _counter: Int,
    private val savedStateHandle : SavedStateHandle
) : ViewModel() {
//    var counter = _counter

    /*
    * SaveState방법 근데 느리지만 앱이 소실되어도 데이터 유실을 방지 할 수 있음
    * */

    var counter = savedStateHandle.get<Int>(SAVE_STATE_KEY) ?: _counter

    fun saveState() {
        // key , valye
        savedStateHandle.set(SAVE_STATE_KEY, counter)
    }


    // savestate는 key, value로 받기때문에 키 값을 상수처리
    companion object {
        private const val SAVE_STATE_KEY = "counter"
    }
}