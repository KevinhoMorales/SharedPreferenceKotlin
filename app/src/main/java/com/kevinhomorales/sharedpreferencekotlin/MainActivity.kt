package com.kevinhomorales.sharedpreferencekotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kevinhomorales.sharedpreferencekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        val NOT_DATA = getString(R.string.not_data_text)
        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val data = sharedPreferences.getString("user_data", NOT_DATA)
        binding.textId.text = if (data != null && data.isNotEmpty()) data else NOT_DATA
        setUpActions()
    }

    private fun setUpActions() {
        binding.buttonId.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val data = binding.editTextId.text.toString()
        with(sharedPreferences.edit()) {
            putString("user_data", data)
            apply()
        }
        binding.textId.setText(data)
    }
}