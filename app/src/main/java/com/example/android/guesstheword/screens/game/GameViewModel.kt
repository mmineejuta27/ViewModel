package com.example.android.guesstheword.screens.game

import android.database.DatabaseUtils
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current _word
    private val _word = MutableLiveData<String>()
    val word : LiveData<String>
    get() = _word

    // The current _score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
    get() = _score

    private val _evenGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
    get () = _evenGameFinish

    private val _currentTime = MutableLiveData<Long>()
    val currenTime: LiveData<Long>
    get() = _currentTime

    val currentTimeString = Transformations.map(currenTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val timer: CountDownTimer



    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewModel", "GameViewModel created!")
        _word.value = ""
        _score.value = 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECOND
            }
        }
        timer.start()
    }

    companion object {
        // Time when the game is over
        private const val DONE = 0L

        // Coundown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()

    }

    fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        if (!wordList.isEmpty()) {
            _score.value = _score.value?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (!wordList.isEmpty()) {
            _score.value = _score.value?.plus(1)
        }
        nextWord()
    }

    /**
     * Moves to the next _word in the list
     */
    fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        } else {
            //Select and remove a _word from the list
            _word.value = wordList.removeAt(0)
        }

    }

    fun onGameFinish() {
        _evenGameFinish.value = true
    }

    fun  onGameFinishComplete() {
        _evenGameFinish.value = false
    }

}