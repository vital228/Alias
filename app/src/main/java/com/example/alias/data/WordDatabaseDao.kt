package com.example.alias.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordDatabaseDao {

    @Query("SELECT name FROM word_table ORDER BY RANDOM() LIMIT 40")
    fun getWords(): List<String>
}