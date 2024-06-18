package com.example.alias.screen.title


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alias.R
import com.example.alias.databinding.FragmentTitleBinding



class TitleFragment : Fragment(R.layout.fragment_title) {
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TitleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentTitleBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        binding.playGameButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleDestinationToSetupFragment())
        }
        return binding.root
    }
}
