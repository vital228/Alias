package com.example.alias.screen.setup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alias.R
import com.example.alias.data.Team

class TeamAdapter() : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    var teams = listOf<Team>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team)
    }

    override fun getItemCount() = teams.size

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.teamNameTextView)
        private val teamItemLayout: LinearLayout = itemView.findViewById(R.id.teamItemLayout)
        private val imageView: ImageView = itemView.findViewById(R.id.teamImageView)

        fun bind(team: Team) {
            nameTextView.text = team.name
            teamItemLayout.setBackgroundColor(team.color)
            // For simplicity, we're not loading an image here. You can use libraries like Glide or Picasso for real image loading.
            imageView.setImageResource(team.picture)
        }
    }
}