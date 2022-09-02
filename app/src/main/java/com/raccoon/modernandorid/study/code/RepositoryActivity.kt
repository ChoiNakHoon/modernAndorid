package com.raccoon.modernandorid.study.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.raccoon.modernandorid.R
import com.raccoon.modernandorid.databinding.ActivityRepositoryBinding

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_repository)


        val binding = DataBindingUtil.setContentView<ActivityRepositoryBinding>(this@RepositoryActivity,
            R.layout.activity_repository
        )


        // Impl를 인스턴스하고 초기값 넣어준다음
        val repositoryImpl : RepositoryImpl = RepositoryImpl(10)

        // 현재 액티비티에 팩토리에 impl 설정
        val factory = RepositoryViewModelFactory(repositoryImpl)

        // 팩토리를 뷰모델 넣어준다.
        val viewModel by viewModels<RepositoriViewModel> { factory }


        // 라이플 사이클을 관측
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.button.setOnClickListener {
            viewModel.increaseCounter()
        }

//        viewModel.modifiedCounter.observe(this) { counter ->
//            viewModel.counterFromRepository.toString()
//        }

    }
}