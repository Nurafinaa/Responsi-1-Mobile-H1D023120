package com.example.responsi1mobileh1d023120

import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val imgHeader = findViewById<ImageView>(R.id.imgHeader)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvStory = findViewById<TextView>(R.id.tvStory)

        // aktifkan justify jika versi Android >= Oreo (API 26)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvStory.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }

        imgHeader.setImageResource(R.drawable.bg)
        tvTitle.text = getString(R.string.club_story_title)
        tvStory.text = getString(R.string.club_story)
    }
}