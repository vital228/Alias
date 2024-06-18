package com.example.alias.screen.title

import android.util.Log
import androidx.lifecycle.ViewModel

class TitleViewModel : ViewModel() {

    init {
        Log.i("TitleViewModel", "GameViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TitleViewModel", "GameViewModel destroyed")
    }
}