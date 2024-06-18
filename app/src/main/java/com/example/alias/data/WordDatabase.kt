package com.example.alias.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alias.R

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDatabaseDao(): WordDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getInstance(context: Context): WordDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        WordDatabase::class.java, context.getString(R.string.db_name))
                        .createFromAsset(context.getString(R.string.db_file))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}