package com.example.alias.screen.setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alias.R
import com.example.alias.data.Team
import com.example.alias.databinding.FragmentSetupBinding
import com.example.alias.databinding.FragmentTitleBinding
import com.example.alias.screen.title.TitleViewModel

class SetupFragment : Fragment(R.layout.fragment_setup) {
    private var _binding: FragmentSetupBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SetupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSetupBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SetupViewModel::class.java)

        val adapter = TeamAdapter()
        binding.teamsRecyclerView.adapter = adapter

        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            if (teams!= null)
                adapter.teams = teams
        }


        binding.addTeamButton.setOnClickListener{
            val text = binding.teamNameEditText.text.toString()
            if (text.isNotEmpty()) {
                viewModel.addTeam(text)
            }
        }
        binding.startGameButton.setOnClickListener{
            if (viewModel.teams.value!!.size > 1) {
                viewModel.teams.value!![0].active = true
                findNavController().navigate(
                    SetupFragmentDirections
                        .actionSetupFragmentToTransferStrokeFragment(viewModel.teams.value!!.toTypedArray())
                )
            }
        }
        return binding.root
    }
}