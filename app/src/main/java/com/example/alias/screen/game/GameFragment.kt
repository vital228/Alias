package com.example.alias.screen.game

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
import com.example.alias.data.WordDatabase
import com.example.alias.data.findActive
import com.example.alias.databinding.FragmentGameBinding

class GameFragment : Fragment(R.layout.fragment_game) {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameViewModel
    private lateinit var viewModelFactory: GameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        val gameFragmentArgs by navArgs<GameFragmentArgs>()
        val active = findActive(gameFragmentArgs.teams)

        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application).getWordDatabaseDao()

        viewModelFactory = GameViewModelFactory(gameFragmentArgs.teams[active], dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GameViewModel::class.java)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)



        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }



        /** Setting up LiveData observation relationship **/
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })

        viewModel.team.observe(viewLifecycleOwner, Observer{ newTeam ->
            binding.teamName.text = newTeam
        })

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = getText(R.string.score).toString()+ newScore.toString()
        })

        viewModel.currentTime.observe(viewLifecycleOwner, Observer{newTime ->
            binding.timerText.text = newTime.toString()
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                val currentScore = viewModel.score.value ?: 0
                findNavController().navigate(GameFragmentDirections
                    .actionGameFragmentToScoreFragment(
                        currentScore,
                        gameFragmentArgs.teams,
                        viewModel.playedWordList.toTypedArray()
                    )
                )
                viewModel.onGameFinishComplete()
            }
        })
        return binding.root
    }

}