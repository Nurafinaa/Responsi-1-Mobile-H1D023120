package com.example.responsi1mobileh1d023120

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023120.data.model.Player
import com.example.responsi1mobileh1d023120.databinding.ActivitySquadBinding
import com.example.responsi1mobileh1d023120.ui.PlayerAdapter
import com.example.responsi1mobileh1d023120.ui.PlayerDetailFragment
import com.example.responsi1mobileh1d023120.viewmodel.TeamViewModel

class SquadActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySquadBinding
    private val vm: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView setup
        val lm = LinearLayoutManager(this)
        binding.recyclerView.apply {
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(this@SquadActivity, lm.orientation))
        }

        // Load default team (113) -> di VM
        vm.loadTeam()

        // Observe data
        vm.squad.observe(this) { list -> renderSquad(list) }
        vm.error.observe(this) { it?.let { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        } }
    }

    private fun renderSquad(players: List<Player>) {
        if (players.isNotEmpty()) {
            binding.recyclerView.adapter = PlayerAdapter(players) { player ->
                PlayerDetailFragment.newInstance(player)
                    .show(supportFragmentManager, "playerDetail")
            }
        } else {
            binding.recyclerView.adapter = null
            Toast.makeText(this, "No team data available", Toast.LENGTH_SHORT).show()
        }
    }
}
