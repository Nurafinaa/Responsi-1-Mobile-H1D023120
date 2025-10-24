package com.example.responsi1mobileh1d023120

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.responsi1mobileh1d023120.databinding.ActivityCoachBinding
import com.example.responsi1mobileh1d023120.viewmodel.CoachViewModel

class CoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoachBinding
    private val vm: CoachViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe LiveData dari ViewModel
        vm.coach.observe(this) { coach ->
            val displayName = when {
                !coach?.name.isNullOrBlank() -> coach!!.name!!
                else -> listOfNotNull(coach?.firstName, coach?.lastName).joinToString(" ")
            }.ifBlank { "-" }

            binding.tvCoachName.text = displayName
            binding.tvCoachDob.text = coach?.dateOfBirth ?: "-"
            binding.tvCoachNationality.text = coach?.nationality ?: "-"
        }


        vm.loading.observe(this) { binding.progress.isVisible = it }


        vm.error.observe(this) { error ->
            if (!error.isNullOrBlank()) {
                binding.tvCoachName.text = "Error: $error"
            }
        }

        vm.fetchTeam()
    }
}
