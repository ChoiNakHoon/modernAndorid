package com.raccoon.modernandorid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raccoon.modernandorid.databinding.ActivityApplyViewModelBinding

class ApplyViewModelActivity : AppCompatActivity() {
    private val binding : ActivityApplyViewModelBinding by lazy {
        ActivityApplyViewModelBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

//        var count: Int = 100
//        binding.textView.text = count.toString()
//
//        binding.button.setOnClickListener {
//            count += 1
//            binding.textView.text = count.toString()
//        }

        /*
        * 정상 작동하지만 가로 모드로 했을 때 액티비티가 새로 그려지기때문에 counter text가 초기화 됨됨
        * 그래서 기존에는 이 문제를 해결하기 위해서 onSaveInstanceState를 이용
        * 그러나 이거는 bundle로 설계되었기때문에 방대한 데이터를 담는 포맷이 아임!!
        * 그래서 뷰 모델을 제안
        * 여기서부터 뷰모델
       * */

        // 뷰모델프로바이더로 뷰모델을 싱글톤으로 생성함
        // 이렇게 했을때 화면 전환하면 문제 없지만 액티비티가 파괴되었을때는 여전한 문제가 발생!
        // 이럴때 Factory를 만듬..
//        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
//        myViewModel.counter = 100
//        binding.textView.text = myViewModel.counter.toString()
//
//        binding.button.setOnClickListener {
//            myViewModel.counter += 1
//            binding.textView.text = myViewModel.counter.toString()
//        }

        /*
        * 그래서
        * */
        // 전달할 초기값을 팩토리에 넘김
        // 팩토리를 받아서 싱글톤
        val factory = MyViewModelFactory(100)
        val myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
        binding.textView.text = myViewModel.counter.toString()

        binding.button.setOnClickListener {
            myViewModel.counter += 1
            binding.textView.text = myViewModel.counter.toString()
        }
    }
}