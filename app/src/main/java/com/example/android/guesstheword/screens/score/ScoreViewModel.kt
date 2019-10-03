package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    // The final _score
    var score = finalScore
    init {
        Log.i("ScoreViewModel", "Final _score is $finalScore")
    }
}