package com.example.responsi1mobileh1d023120.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.responsi1mobileh1d023120.data.model.Player
import com.example.responsi1mobileh1d023120.databinding.FragmentSquadDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerDetailFragment : BottomSheetDialogFragment() {

    companion object {
        private const val ARG_PLAYER = "player"
        fun newInstance(player: Player) = PlayerDetailFragment().apply {
            arguments = Bundle().apply { putSerializable(ARG_PLAYER, player) }
        }
    }

    private var _binding: FragmentSquadDetailBinding? = null
    private val binding get() = _binding!!
    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(ARG_PLAYER, Player::class.java)
        } else {
            @Suppress("DEPRECATION") arguments?.getSerializable(ARG_PLAYER) as? Player
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSquadDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player?.let {
            binding.tvName.text = it.name ?: "-"
            binding.tvPosition.text = it.position ?: "-"
            binding.tvNationality.text = it.nationality ?: "-"
            binding.tvBirth.text = it.dateOfBirth ?: "-"
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
