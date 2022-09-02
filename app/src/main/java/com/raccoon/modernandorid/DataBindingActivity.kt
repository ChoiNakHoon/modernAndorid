package com.raccoon.modernandorid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.raccoon.modernandorid.databinding.ActivityDataBindingBinding as ActivityDataBinding

class DataBindingActivity : AppCompatActivity() {

//    private val binding : ActivityDataBinding by lazy {
//        ActivityDataBinding.inflate(layoutInflater)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data_binding)

//        setContentView(binding.root)

        // databidingUtil를 통해서 setContentView를 실행시킴
        val binding = DataBindingUtil.setContentView<ActivityDataBinding>(this@DataBindingActivity, R.layout.activity_data_binding)


        // 현재 액티비티에 초기값을 팩토리에 전달
        val factory = DataBindingViewModelFactory(10)

        val dataBindingViewModel by viewModels<DataBindingViewModel> { factory }

        // 라이브 사이클을 관측
        binding.lifecycleOwner = this
        binding.viewmodel = dataBindingViewModel


        binding.button3.setOnClickListener {

            dataBindingViewModel.liveCounter.value = dataBindingViewModel.liveCounter.value?.plus(1)
        }

        dataBindingViewModel.modifiedCounter.observe(this) { counter ->
            binding.textView3.text = counter.toString()
        }

    }
}