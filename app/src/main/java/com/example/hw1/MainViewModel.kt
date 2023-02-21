package com.example.hw1

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    lateinit var countriesCapitalMixed: Array<String>
    lateinit var cheat: Array<String>
    var currentQuestionsIndex = 0
    var point = 0
    val value = arrayListOf(true, true, false, true, false, true, false, true, true, false)
    var answered =
        mutableListOf(false, false, false, false, false, false, false, false, false, false)

    fun increasePoint() {
        point++
    }

    fun decreasePoint() {
        point--
    }

    fun increaseCurrentQuestionsIndex() {
        currentQuestionsIndex++
    }

    fun decreaseCurrentQuestionsIndex() {
        currentQuestionsIndex--
    }
}