package com.example.alias.screen.transfer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alias.R
import com.example.alias.data.Team


class ScoreTeamAdapter(private val teams: List<Team>) : RecyclerView.Adapter<ScoreTeamAdapter.ScoreTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreTeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team_score, parent, false)
        return ScoreTeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreTeamViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team)
    }

    override fun getItemCount() = teams.size

    class ScoreTeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.teamNameTextView)
        private val teamItemLayout: LinearLayout = itemView.findViewById(R.id.teamItemLayout)
        private val imageView: ImageView = itemView.findViewById(R.id.teamImageView)
        private val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)

        fun bind(team: Team) {
            nameTextView.text = team.name
            teamItemLayout.setBackgroundColor(team.color)
            // For simplicity, we're not loading an image here. You can use libraries like Glide or Picasso for real image loading.
            imageView.setImageResource(team.picture)
            scoreTextView.text = team.score.toString()
        }
    }
}