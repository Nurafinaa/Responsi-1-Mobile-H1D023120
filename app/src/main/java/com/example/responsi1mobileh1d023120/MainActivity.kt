package com.example.responsi1mobileh1d023120

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.deskripsi).text = getString(R.string.desc_club)

        // === History ===
        val menuHistory = findViewById<View>(R.id.layout_history)
        menuHistory.findViewById<ImageView>(R.id.menu_icon).setImageResource(R.drawable.ball)
        menuHistory.findViewById<TextView>(R.id.menu_text).text = getString(R.string.menu_history)
        menuHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // === Coach ===
        val menuCoach = findViewById<View>(R.id.layout_coach)
        menuCoach.findViewById<ImageView>(R.id.menu_icon).setImageResource(R.drawable.avatar)
        menuCoach.findViewById<TextView>(R.id.menu_text).text = getString(R.string.menu_coach)
        menuCoach.setOnClickListener {
            startActivity(Intent(this, CoachActivity::class.java))
        }

        // === Squad ===
        val menuSquad = findViewById<View>(R.id.layout_squad)
        menuSquad.findViewById<ImageView>(R.id.menu_icon).setImageResource(R.drawable.people)
        menuSquad.findViewById<TextView>(R.id.menu_text).text = getString(R.string.menu_squad)
        menuSquad.setOnClickListener {
            startActivity(Intent(this, SquadActivity::class.java))
        }
    }
}
