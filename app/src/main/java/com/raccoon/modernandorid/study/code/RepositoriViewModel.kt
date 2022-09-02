package com.raccoon.modernandorid.study.code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

// 뷰 모델에서 Impl 가져오고
class RepositoriViewModel(
//    _counter: Int,
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

//    var liveCounter : MutableLiveData<Int> = MutableLiveData(_counter)

    // 뷰 get할 것과 액티비티에서 셋할 것을 만들어줍니다.


    // repository에 정의된 인터페이스 내용을 Impl에서 구현하고
    // 구현된 Impl은 viewmodel에서 생성자로 사용하며
    // viewmodel에서는 함수를 구현해서 Impl에 구현된것을 가져와서 사용함
    // get
    val counterFromRepository : LiveData<Int> = repositoryImpl.getCounter()

    // set
    fun increaseCounter() {
        repositoryImpl.increaseCounter()
    }

    val modifiedCounter : LiveData<String> = Transformations.map(counterFromRepository) { counter ->
        "${counter} 입니다."
    }

    val hasChecked: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
}