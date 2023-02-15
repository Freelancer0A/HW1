package com.example.hw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var point = 0
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button
    private lateinit var btnCheat: Button
    private lateinit var tvQuestions: TextView
    private lateinit var tvPoint: TextView
    private var currentQuestionsIndex = 0
    private lateinit var countriesCapitalMixed: Array<String>
    private lateinit var cheat: Array<String>
    private val value = arrayListOf(true, true, false, true, false, true, false, true, true, false)
    private var answered =
        mutableListOf(false, false, false, false, false, false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countriesCapitalMixed = resources.getStringArray(R.array.countriesCapitalMixed)
        cheat = resources.getStringArray(R.array.cheat)
        init()
        update()
    }

    private fun init() {
        tvQuestions = findViewById(R.id.tv_questions)
        tvPoint = findViewById(R.id.tv_point)
        btnTrue = findViewById(R.id.btn_true)
        btnFalse = findViewById(R.id.btn_false)
        btnNext = findViewById(R.id.btn_next)
        btnPrev = findViewById(R.id.btn_prev)
        btnCheat = findViewById(R.id.btn_cheat)
    }

    private fun update() {
        tvQuestions.text = countriesCapitalMixed[currentQuestionsIndex]
        tvPoint.text = "Point: ${point.toString()}"
        if (answered[currentQuestionsIndex]) {
            btnFalse.visibility = View.INVISIBLE
            btnTrue.visibility = View.INVISIBLE
        } else if (!answered[currentQuestionsIndex]) {
            btnFalse.visibility = View.VISIBLE
            btnTrue.visibility = View.VISIBLE
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            btnCheat.id -> {
            }
            btnFalse.id -> {
                if (!value[currentQuestionsIndex]) {
                    point++
                    Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show()
                    //   update()
                } else {
                    point--
                    Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
                }
                answered[currentQuestionsIndex] = true
                update()
                currentQuestionsIndex++
            }
            btnTrue.id -> {
                if (value[currentQuestionsIndex]) {
                    point++
                    Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show()
                    //  update()
                } else {
                    point--
                    Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
                }
                answered[currentQuestionsIndex] = true
                update()
                currentQuestionsIndex++
            }
            btnNext.id -> {
                if (currentQuestionsIndex == countriesCapitalMixed.size - 1) Toast.makeText(
                    this,
                    "The questions are over",
                    Toast.LENGTH_SHORT
                ).show()
                else {
                    currentQuestionsIndex++
                    update()
                }
            }
            btnPrev.id -> {
                if (currentQuestionsIndex == 0) Toast.makeText(
                    this,
                    "now you are in first question",
                    Toast.LENGTH_SHORT
                ).show()
                else {
                    currentQuestionsIndex--
                    update()
                }
            }
        }
    }
}