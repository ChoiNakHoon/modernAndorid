package com.raccoon.modernandorid.study.code

import android.content.Context


/*
* 싱글톤
* - 클래스의 인스턴스를 단 하나만 만들어야 할 때 사용
* */

// java
//public class Singleton {
//    private static Singleton instance
//
//    private Singleton()
//    {}
//
//    public static Singleton getInstance()
//    {
//        if (instance == null) {
//            return instance = new getInstance ()
//        }
//        return instance;
//    }
//}


// Kotlin
object Singleton {}

val singleton = Singleton

class Sigleton


/**
 * 아무튼 하나의 쓰레드는 하나의 싱글톤을(단 하나만의 인스턴스)가져야 되는데
 * 설계 에러로 두개의 쓰레드가 어떤 하나의 인스턴스를 같이 바라보는 경우가 있다.
 * 이런 경우를 미연의 방지하기 위한 기법
 * DoubleChecked Locking
 * */

class DBHandler private constructor(context: Context) {
    companion object {
        @Volatile
        private var instance: DBHandler? = null

        fun getInstance(context: Context) =
            // 복수계의 쓰레드가 접근하지 못하도록 막음
            instance ?: synchronized(DBHandler::class.java) {
                instance ?: DBHandler(context).also {
                    instance = it
                }
            }
    }
}