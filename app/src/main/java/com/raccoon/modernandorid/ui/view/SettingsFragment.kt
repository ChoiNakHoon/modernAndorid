package com.raccoon.modernandorid.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.raccoon.modernandorid.R
import com.raccoon.modernandorid.databinding.FragmentSettingsBinding
import com.raccoon.modernandorid.ui.viewmodel.BookSearchViewModel
import com.raccoon.modernandorid.util.Sort
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookSearchViewModel: BookSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //메인 액티비티에서 뷰 모델을 전달 받고
        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        //save,loadSettings를 받는다
        saveSettings()
        loadSettings()
        showWorkStatus()
    }

    private fun saveSettings() {
        binding.rgSort.setOnCheckedChangeListener { _, checkedId ->
            val value = when (checkedId) {
                R.id.rb_accuracy -> Sort.ACCURACY.value
                R.id.rb_latest -> Sort.LATEST.value
                else -> return@setOnCheckedChangeListener
            }
            // Sort값 저장
            bookSearchViewModel.saveSortMode(value)
        }

        // WorkManager
        binding.swCacheDelete.setOnCheckedChangeListener { _, isChecked ->
            bookSearchViewModel.saveCacheDeleteMode(isChecked)
            if (isChecked) {
                bookSearchViewModel.setWork()
            } else {
                bookSearchViewModel.deleteWork()
            }
        }
    }

    private fun loadSettings() {
        lifecycleScope.launch {
            val buttonId = when (bookSearchViewModel.getSortMode()) {
                Sort.ACCURACY.value -> R.id.rb_accuracy
                Sort.LATEST.value -> R.id.rb_latest
                else -> return@launch
            }
            // Sort값을 반영
            binding.rgSort.check(buttonId)
        }

        // WorkManager
        lifecycleScope.launch {
            val mode = bookSearchViewModel.getCacheDelteMode()
            binding.swCacheDelete.isChecked = mode
        }

    }

    private fun showWorkStatus() {
        bookSearchViewModel.getWorkStatus().observe(viewLifecycleOwner) { workInfo ->
            Log.d("WorkManager", workInfo.toString())
            if (workInfo.isEmpty()) {
                binding.tvWorkStatus.text = "No works"
            } else {
                binding.tvWorkStatus.text = workInfo[0].state.toString()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}