package com.example.alias.screen.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alias.R
import com.example.alias.data.Team
import kotlin.random.Random

class SetupViewModel: ViewModel() {
    private val _teams = MutableLiveData<List<Team>>(emptyList())
    val teams: LiveData<List<Team>> = _teams
    private val imageIdList = listOf(
        R.drawable.ic_team_placeholder,
        R.drawable.ic_team_placeholder_1,
        R.drawable.ic_team_placeholder_2
    )

    private val colorIdList = listOf(
        R.color.green,
        R.color.red,
        R.color.blue
    )
    fun addTeam(name: String) {
        val newTeam = Team(
            name = name,
            color = generateColor(),
            picture = generatePicture()
        )
        _teams.value = _teams.value!! + newTeam
    }

    private fun generateColor(): Int {
        // Placeholder for color generation logic
        return colorIdList.random()
    }

    private fun generatePicture(): Int {
        // Placeholder for picture generation logic
        return imageIdList.random()
    }
}