package com.raccoon.modernandorid

import androidx.lifecycle.ViewModel

// 팩토리에서 보낸 인자값을 받음
class MyViewModel(_counter: Int) : ViewModel() {
    var counter = _counter
}