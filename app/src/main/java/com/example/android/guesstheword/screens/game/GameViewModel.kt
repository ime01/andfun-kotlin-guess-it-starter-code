package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    companion object{
        //This is when the game is over
        private const val DONE = 0L

        //This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        //This is the total time of the game
        private const val COUNTDOWN_TIME = 10000L
    }

    private val timer: CountDownTimer

    // The current word
     var word = ""

    // The current score
   private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
    get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private fun resetList() {
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


    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
           // gameFinished()
            resetList()
        }
            word = wordList.removeAt(0)

    }


     fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect() {
         _score.value = (score.value)?.plus(1)
        nextWord()
    }

   private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
    get() = _eventGameFinish

    fun onGameFinishedComplete(){
       _eventGameFinish.value = false
    }

    init {
      Log.i("GameViewModel", "GameViewModel created!")

        _eventGameFinish.value = false
        resetList()
        nextWord()
        _score.value = 0;

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long){
            }

            override fun onFinish() {

            }
        }

        // DateUtils.formatElapsedTime()

  }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}