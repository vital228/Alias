package com.example.alias.screen.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alias.data.Word

class ScoreViewModelFactory(private val words: Array<Word>, private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(words, finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}