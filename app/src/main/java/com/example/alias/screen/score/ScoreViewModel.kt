package com.example.alias.screen.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alias.data.Team
import com.example.alias.data.Word

class ScoreViewModel(private val newWords: Array<Word>, private val finalScore: Int) : ViewModel() {
    private val _words = MutableLiveData<List<Word>>(emptyList())
    val words: LiveData<List<Word>> = _words

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _score.value = finalScore
        _words.value = newWords.toList()
    }
}