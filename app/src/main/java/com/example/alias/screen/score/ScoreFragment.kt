package com.example.alias.screen.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alias.R
import com.example.alias.data.Team
import com.example.alias.data.findActive
import com.example.alias.databinding.FragmentScoreBinding
import com.example.alias.screen.transfer.ScoreTeamAdapter

class ScoreFragment : Fragment() {
    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.playedWords, scoreFragmentArgs.score)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ScoreViewModel::class.java)

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        viewModel.words.observe(viewLifecycleOwner) { words ->
            binding.wordRecyclerView.adapter = WordAdapter(words)
        }

        binding.goNextButton.setOnClickListener {
            updateDataTeams(scoreFragmentArgs.teams, scoreFragmentArgs.score)
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToTransferStrokeFragment(scoreFragmentArgs.teams))
        }
        return binding.root
    }

    private fun updateDataTeams(teams: Array<Team>, score: Int){
        val active = findActive(teams)
        teams[active].score += score
        teams[active].active = false
        teams[(active+1)%teams.size].active = true
    }

}