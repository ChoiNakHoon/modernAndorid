package com.raccoon.modernandorid.study.code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

// 팩토리에 Impl 생성자 받아주고
@Suppress("UNCHECKED_CAST")
class RepositoryViewModelFactory(
    private val repositoryImpl: RepositoryImpl
) : ViewModelProvider.Factory {
    // 싱글톤 구현
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoriViewModel::class.java)) {
            // 뷰 모델로 반환
            return RepositoriViewModel(repositoryImpl) as T
        }

        throw IllegalArgumentException("Viewmodel class not found")
    }
}