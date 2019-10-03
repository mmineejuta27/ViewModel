package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    // The final _score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score
    init {
        Log.i("ScoreViewModel", "Final _score is $finalScore")
        _score.value = finalScore
    }
}