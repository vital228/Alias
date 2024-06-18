package com.example.alias

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.alias.data.Word
import com.example.alias.data.WordDatabase
import com.example.alias.data.WordDatabaseDao
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class WordDatabaseTest {
    private lateinit var wordDao: WordDatabaseDao
    private lateinit var db: WordDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, WordDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        wordDao = db.getWordDatabaseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val night = Word(name="orange")
        wordDao.insert(night)
        val tonight = wordDao.getAllWords()
        assertEquals(1, tonight.value?.size)
    }
}