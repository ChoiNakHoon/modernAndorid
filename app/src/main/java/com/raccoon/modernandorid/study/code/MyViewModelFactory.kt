package com.raccoon.modernandorid.study.code

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
//class MyViewModelFactory(private val counter: Int) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
//            return MyViewModel(counter) as T
//        }
//        throw IllegalArgumentException("Viewmodel calss not found")
//    }
//}

/*
* savestate factory
*
* AbstractSavedStateViewModelFactory 상속하고 생성자를 변경해주고 뷰 모델을 반환할때 handle을 같이 반환해주면 된다
* */

class MyViewModelFactory(
    private val counter: Int,
    owner: SavedStateRegistryOwner,
    defaultArgs : Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(counter, handle) as T
        }

        throw IllegalArgumentException("viewmodel class not found")
    }
}