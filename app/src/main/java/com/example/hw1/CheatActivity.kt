package com.example.hw1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.hw1.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private lateinit var viewModel: MainViewModel
    private var cheat: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.countriesCapitalMixed = resources.getStringArray(R.array.countriesCapitalMixed)
        viewModel.cheat = resources.getStringArray(R.array.cheat)
        val index = intent.getIntExtra(key_Index, 0)
        binding.btnSecondCheat.setOnClickListener {
            binding.tvCheat.text = viewModel.cheat[index]
            cheat = true
        }
    }

    companion object {
        const val key_Cheat = "CHEAT"
        const val key_Index = "INDEX"
        fun newInstance(context: Context, index: Int): Intent = Intent(
            context,
            CheatActivity::class.java,
        ).apply {
            putExtra(key_Index, index)
        }
    }

    private fun passData() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        intent.putExtra(key_Cheat, cheat)
        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        passData()
        super.onBackPressed()
    }
}