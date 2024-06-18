package com.example.alias.screen.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alias.data.Team

class TransferStrokeViewModelFactory(private val teams: Array<Team>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransferStrokeViewModel::class.java)) {
            return TransferStrokeViewModel(teams) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}