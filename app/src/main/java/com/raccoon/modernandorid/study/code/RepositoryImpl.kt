package com.raccoon.modernandorid.study.code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// 받을 값을 생성자로 지정해주고 인터페이스 가져오고
class RepositoryImpl(counter: Int): Repository {

    // 데이터는 라이브 데이터로 저장한다.
    private val liveCounter = MutableLiveData<Int>(counter)

    //getter
    override fun getCounter(): LiveData<Int> {
        return liveCounter
    }

    //setter
    override fun increaseCounter() {
        liveCounter.value = liveCounter.value?.plus(1)
    }
}