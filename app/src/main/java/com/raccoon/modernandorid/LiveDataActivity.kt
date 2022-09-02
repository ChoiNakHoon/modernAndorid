package com.raccoon.modernandorid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.raccoon.modernandorid.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {
    private val binding: ActivityLiveDataBinding by lazy {
        ActivityLiveDataBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_live_data)

        setContentView(binding.root)

        // 현재 액티비티와 초기값을 팩토리에 전달
        val factory = LiveViewModelFactory(100)

        val liveViewModel by viewModels<LiveViewModel> { factory }

//        binding.textView2.text = liveViewModel.counter.toString()

        binding.button2.setOnClickListener {
//            liveViewModel.counter += 1
//            binding.textView2.text = liveViewModel.counter.toString()
            liveViewModel.liveCounter.value = liveViewModel.liveCounter.value?.plus(1)
        }

        // LiveData 옵저빙
        liveViewModel.liveCounter.observe(this) { counter ->
            binding.textView2.text = counter.toString()
        }
    }
}