package com.example.alias.screen.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alias.data.Team
import com.example.alias.data.findActive

class TransferStrokeViewModel(private val newTeams: Array<Team>) : ViewModel() {
    private val _teams = MutableLiveData<List<Team>>(emptyList())
    val teams: LiveData<List<Team>> = _teams

    private val _nextTeam = MutableLiveData<String>()
    val nextTeam: LiveData<String>
        get() = _nextTeam

    init{
        _teams.value = newTeams.toList()
        _nextTeam.value = newTeams[findActive(newTeams)].name
    }
}