package com.raccoon.modernandorid

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

//progressScaled와 android:max가 실행된때 setProgress가 실행됩니다.
@BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
fun setProgress(progressBar: ProgressBar, counter: Int, max: Int) {
    progressBar.progress = (counter * 2).coerceAtMost(max)
}