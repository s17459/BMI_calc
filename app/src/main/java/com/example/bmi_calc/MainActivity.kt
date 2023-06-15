package com.example.bmi_calc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextWeight: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var buttonCalculateBMI: Button
    private lateinit var textViewResult: TextView
    private lateinit var editTextAge: EditText
    private lateinit var buttonCalculateCalories: Button
    private lateinit var textViewCaloriesResult: TextView
    private lateinit var buttonRecommendations: Button
    private lateinit var buttonRedirectToQuiz: Button
    private lateinit var buttonGraph: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        editTextWeight = findViewById(R.id.editTextWeight)
        editTextHeight = findViewById(R.id.editTextHeight)
        editTextAge = findViewById(R.id.editTextAge)
        buttonCalculateBMI = findViewById(R.id.buttonCalculate)
        textViewResult = findViewById(R.id.textViewResult)
        buttonRedirectToQuiz = findViewById(R.id.buttonQuiz)
        buttonGraph = findViewById(R.id.buttonGraphBMI)
        buttonCalculateCalories = findViewById(R.id.buttonCalculate)
        textViewCaloriesResult = findViewById(R.id.textViewCaloriesResult)
        buttonRecommendations = findViewById(R.id.buttonRecommendations)
        buttonRecommendations.visibility = View.GONE

        // Set click listener for BMI calculation button
        buttonRedirectToQuiz.setOnClickListener { redirectToQuiz() }
        buttonRecommendations.setOnClickListener { redirectToRecommendations() }
        buttonCalculateBMI.setOnClickListener {
            calculateBMI()
            calculateCalories()
        }
        buttonGraph.setOnClickListener {
            val intent = Intent(this@MainActivity, GraphActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calculateBMI() {
        val weightString = editTextWeight.text.toString()
        val heightString = editTextHeight.text.toString()
        if (weightString.isEmpty() || heightString.isEmpty()) {
            textViewResult.setText(R.string.wprowadz_dane)
            return
        }
        val weight = weightString.toFloat()
        val height = heightString.toFloat() / 100 // Convert height to meters
        val bmi = weight / (height * height)
        val bmiCategory = when {
            bmi < 18.5 -> getString(R.string.niedowaga)
            bmi < 25 -> getString(R.string.normalna_waga)
            bmi < 30 -> getString(R.string.nadwaga)
            else -> getString(R.string.oty_o)
        }
        val result = """
            BMI: ${String.format("%.1f", bmi)}
            $bmiCategory
            """.trimIndent()
        textViewResult.text = result
    }

    private fun calculateCalories() {
        val weightString = editTextWeight.text.toString()
        val heightString = editTextHeight.text.toString()
        val ageString = editTextAge.text.toString()
        if (weightString.isEmpty() || heightString.isEmpty() || ageString.isEmpty()) {
            return
        }
        val weight = weightString.toFloat()
        val height = heightString.toFloat()
        val age = ageString.toInt()

        // Calculate calories using Benedict-Harris formula for sedentary lifestyle
        val bmr = 66.5 + 13.75 * weight + 5 * height - 6.75 * age
        val result = getString(R.string.kalorie_na_dzien) + Math.round(bmr)
        textViewCaloriesResult.text = result
        buttonRecommendations.visibility = View.VISIBLE
    }

    private fun redirectToRecommendations() {
        val intent = Intent(this@MainActivity, RecommendationsActivity::class.java)
        startActivity(intent)
    }

    private fun redirectToQuiz() {
        val intent = Intent(this@MainActivity, QuizActivity::class.java)
        startActivity(intent)
    }
}
