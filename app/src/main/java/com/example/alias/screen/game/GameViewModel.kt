package com.example.alias.screen.game

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alias.data.Team
import com.example.alias.data.Word
import com.example.alias.data.WordDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(private val activeTeam: Team, val dao: WordDatabaseDao, application: Application) : AndroidViewModel(application) {
    companion object {
        // These represent different important times in the game, such as game length.

        // This is when the game is over
        private const val DONE = 0L

        // This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        // This is the total time of the game
        private const val COUNTDOWN_TIME = 60000L

    }

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _team = MutableLiveData<String>()
    val team: LiveData<String>
        get() = _team


    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score



    private lateinit var wordList: MutableList<String>

    lateinit var playedWordList: MutableList<Word>

    // Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        resetList()

        _score.value = 0
        _team.value = activeTeam.name
        playedWordList = mutableListOf()
        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }
        }
        timer.start()
    }

    private fun resetList() {
        wordList = mutableListOf()
        uiScope.launch {
            wordList = getWordsFromDatabase()
            _word.value = wordList.removeAt(0)
        }
    }

    private suspend fun getWordsFromDatabase() : MutableList<String> {
        return withContext(Dispatchers.IO) {
            dao.getWords().toMutableList()
        }
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        }
        else
            _word.value = wordList.removeAt(0)
    }

    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        playedWordList.add(Word(name = _word.value!!, isGuess = false))
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        playedWordList.add(Word(name = _word.value!!, isGuess = true))
        nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        viewModelJob.cancel()
    }

}