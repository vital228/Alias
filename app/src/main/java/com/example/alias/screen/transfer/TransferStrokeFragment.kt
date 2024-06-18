package com.example.alias.screen.transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alias.databinding.FragmentTransferStrokeBinding


class TransferStrokeFragment : Fragment() {
    private var _binding: FragmentTransferStrokeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TransferStrokeViewModel
    private lateinit var viewModelFactory: TransferStrokeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferStrokeBinding.inflate(inflater, container, false)

        val transferStrokeFragmentArgs by navArgs<TransferStrokeFragmentArgs>()

        viewModelFactory = TransferStrokeViewModelFactory(transferStrokeFragmentArgs.teams)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TransferStrokeViewModel::class.java)

        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            binding.teamsRecyclerView.adapter = ScoreTeamAdapter(teams)
        }

        viewModel.nextTeam.observe(viewLifecycleOwner, Observer{ newTeam ->
            binding.nextTeamName.text = newTeam
        })

        binding.startRoundButton.setOnClickListener{
            findNavController().navigate(TransferStrokeFragmentDirections
                .actionTransferStrokeFragmentToGameFragment(transferStrokeFragmentArgs.teams)
            )
        }
        binding.endGameButton.setOnClickListener {
            findNavController().navigate(TransferStrokeFragmentDirections
                .actionTransferStrokeFragmentToTitleDestination())
        }

        return binding.root
    }


}