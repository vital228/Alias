package com.example.alias.screen.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alias.data.Team
import com.example.alias.data.WordDatabaseDao

class GameViewModelFactory(private val team: Team, private val dao: WordDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(team, dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}