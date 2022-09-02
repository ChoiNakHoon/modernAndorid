package com.raccoon.modernandorid.study.code

import androidx.lifecycle.LiveData

interface Repository {
    // getter interface
    fun getCounter(): LiveData<Int>

    // setter interface
    fun increaseCounter()
}