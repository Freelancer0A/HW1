package com.example.hw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.hw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button
    private lateinit var btnCheat: Button
    private lateinit var tvQuestions: TextView
    private lateinit var tvPoint: TextView
    private lateinit var viewModel: MainViewModel
    private lateinit var resultActivityLauncher: ActivityResultLauncher<Intent>
    private var hasCheat: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resultActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    hasCheat = it.data?.extras?.getBoolean(CheatActivity.key_Cheat) ?: false
                }
            }
        init()
        update()
        //set onClick listeners
        btnTrue.setOnClickListener {
            if (viewModel.value[viewModel.currentQuestionsIndex] && !hasCheat) {
                viewModel.increasePoint()
                Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show()
                //  update()
            } else if (viewModel.value[viewModel.currentQuestionsIndex] && hasCheat) {
                Toast.makeText(this, "Cheating is Wrong.", Toast.LENGTH_SHORT).show()
                hasCheat = false
            } else if (!viewModel.value[viewModel.currentQuestionsIndex] && hasCheat) {
                Toast.makeText(
                    this,
                    "Despite the cheating, you still gave the wrong answer",
                    Toast.LENGTH_SHORT
                ).show()
                hasCheat = false
            } else {
                viewModel.decreasePoint()
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            }
            viewModel.answered[viewModel.currentQuestionsIndex] = true
            update()
        }
        btnFalse.setOnClickListener {
            if (!viewModel.value[viewModel.currentQuestionsIndex] && !hasCheat) {
                viewModel.increasePoint()
                Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show()
            } else if (!viewModel.value[viewModel.currentQuestionsIndex] && hasCheat) {
                Toast.makeText(this, "Cheating is Wrong.", Toast.LENGTH_SHORT).show()
            } else if (viewModel.value[viewModel.currentQuestionsIndex] && hasCheat) {
                Toast.makeText(
                    this,
                    "Despite the cheating, you still gave the wrong answer",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.decreasePoint()
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            }
            viewModel.answered[viewModel.currentQuestionsIndex] = true
            update()
        }
        btnNext.setOnClickListener {
            if (viewModel.currentQuestionsIndex == viewModel.countriesCapitalMixed.size - 1) Toast.makeText(
                this,
                "The questions are over",
                Toast.LENGTH_SHORT
            ).show()
            else {
                viewModel.increaseCurrentQuestionsIndex()
                update()
            }
        }
        btnPrev.setOnClickListener {
            if (viewModel.currentQuestionsIndex == 0) Toast.makeText(
                this,
                "now you are in first question",
                Toast.LENGTH_SHORT
            ).show()
            else {
                viewModel.decreaseCurrentQuestionsIndex()
                update()
            }
            init()
            update()
        }
        btnCheat.setOnClickListener {
            resultActivityLauncher.launch(
                CheatActivity.newInstance(
                    this,
                    viewModel.currentQuestionsIndex
                )
            )
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.countriesCapitalMixed = resources.getStringArray(R.array.countriesCapitalMixed)
        viewModel.cheat = resources.getStringArray(R.array.cheat)
        tvQuestions = binding.tvQuestions
        tvPoint = binding.tvPoint
        btnTrue = binding.btnTrue
        btnFalse = binding.btnFalse
        btnNext = binding.btnNext
        btnPrev = binding.btnPrev
        btnCheat = binding.btnCheat
    }

    private fun update() {
        tvQuestions.text = viewModel.countriesCapitalMixed[viewModel.currentQuestionsIndex]
        tvPoint.text = "Point: ${viewModel.point}"
        if (viewModel.answered[viewModel.currentQuestionsIndex]) {
            btnFalse.visibility = View.INVISIBLE
            btnTrue.visibility = View.INVISIBLE
        } else if (!viewModel.answered[viewModel.currentQuestionsIndex]) {
            btnFalse.visibility = View.VISIBLE
            btnTrue.visibility = View.VISIBLE
        }
    }
}