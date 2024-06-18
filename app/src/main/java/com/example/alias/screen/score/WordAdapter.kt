package com.example.alias.screen.score

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alias.R
import com.example.alias.data.Team
import com.example.alias.data.Word
import com.example.alias.screen.setup.TeamAdapter

class WordAdapter(private val words: List<Word>): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word)
    }

    override fun getItemCount() = words.size

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.wordNameTextView)
        private val wordItemLayout: LinearLayout = itemView.findViewById(R.id.wordItemLayout)
        private val scoreWordTextView: TextView = itemView.findViewById(R.id.wordScoreTextView)

        @SuppressLint("ResourceAsColor")
        fun bind(word: Word) {
            nameTextView.text = word.name
            if (word.isGuess){
                wordItemLayout.setBackgroundColor(R.color.ok_green_color)
                scoreWordTextView.text = "1"
            }
            else {
                wordItemLayout.setBackgroundColor(R.color.skip_red_color)
                scoreWordTextView.text = "-1"
            }
        }
    }
}