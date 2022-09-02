package com.raccoon.modernandorid.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raccoon.modernandorid.R
import com.raccoon.modernandorid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupBottomNavigationView()

        // 앱이 처음 생성 됐을때만 메뉴가 생성
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.fragment_serach
        }
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_serach -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SearchFragment())
                        .commit()
                    true
                }
                R.id.fragment_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavoriteFragment())
                        .commit()
                    true
                }
                R.id.fragment_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SettingsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

}