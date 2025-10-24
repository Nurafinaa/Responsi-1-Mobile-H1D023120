package com.example.responsi1mobileh1d023120.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023120.data.model.Player
import com.example.responsi1mobileh1d023120.databinding.ItemPlayerBinding

class PlayerAdapter(
    private val players: List<Player>,
    private val onClick: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.VH>() {

    inner class VH(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return VH(ItemPlayerBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = players[position]
        with(holder.binding) {
            tvName.text = p.name ?: "-"
            tvNation.text = p.nationality ?: "-"

            val role = p.position?.lowercase() ?: ""
            val bg = when {
                role.contains("goalkeeper") -> Color.YELLOW
                role.contains("defence") || role.contains("back") -> Color.BLUE
                role.contains("midfield") -> Color.GREEN
                role.contains("forward") || role.contains("winger") || role.contains("offence") -> Color.RED
                else -> Color.LTGRAY
            }
            card.setCardBackgroundColor(bg)

            // Atur kontras teks
            val lum = 0.299 * Color.red(bg) + 0.587 * Color.green(bg) + 0.114 * Color.blue(bg)
            val textColor = if (lum < 186) Color.WHITE else Color.BLACK
            tvName.setTextColor(textColor)
            tvNation.setTextColor(textColor)

            root.setOnClickListener { onClick(p) }
        }
    }

    override fun getItemCount() = players.size
}
