package com.example.bmi_calc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    private lateinit var textViewQuestion: TextView
    private lateinit var radioButtonOption1: RadioButton
    private lateinit var radioButtonOption2: RadioButton
    private lateinit var radioButtonOption3: RadioButton
    private lateinit var radioButtonOption4: RadioButton
    private lateinit var buttonNext: Button
    private lateinit var radioGroupOptions: RadioGroup

    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = listOf(
        Question("Pytanie 1: Co jest dobrym źródłem białka?", "Pierś z kurczaka", "stek wołowy", "Łosoś", "Biały ryż", 1),
        Question("Pytanie 2: Którą grupę żywności należy spożywać z umiarem?", "Słodycze i desery", "Owoce i warzywa", "Pełnoziarniste", "Śmietana 18%", 1),
        Question("Pytanie 3: Co jest dobrym źródłem błonnika pokarmowego?", "Brokuły", "Mleko", "Biały chleb", "Bułka", 1),
        Question("Pytanie 4: Która metoda gotowania jest zdrowsza?", "Na parze", "Smażenie", "Głębokie smażenie", "Grillowanie", 1),
        Question("Pytanie 5: Co należy ograniczyć w zdrowej diecie?", "Dodane cukry", "Białko", "Woda", "Piwo", 1),
        Question("Pytanie 6: Co jest dobrym źródłem zdrowych tłuszczów?", "Awokado", "Masło", "Lody", "Burger", 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initialize views
        textViewQuestion = findViewById(R.id.textViewQuestion)
        radioButtonOption1 = findViewById(R.id.radioButtonOption1)
        radioButtonOption2 = findViewById(R.id.radioButtonOption2)
        radioButtonOption3 = findViewById(R.id.radioButtonOption3)
        radioButtonOption4 = findViewById(R.id.radioButtonOption4)
        buttonNext = findViewById(R.id.buttonNext)
        radioGroupOptions = findViewById(R.id.radioGroupOptions)

        displayQuestion()

        buttonNext.setOnClickListener {
            checkAnswer()
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
            } else {
                showResult()
            }
        }
    }

    private fun displayQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        textViewQuestion.text = currentQuestion.question
        radioButtonOption1.text = currentQuestion.option1
        radioButtonOption2.text = currentQuestion.option2
        radioButtonOption3.text = currentQuestion.option3
        radioButtonOption4.text = currentQuestion.option4

        // Clear previous selection
        radioGroupOptions.clearCheck()
    }

    private fun checkAnswer() {
        val selectedOption = radioGroupOptions.checkedRadioButtonId
        val selectedAnswer = when (selectedOption) {
            R.id.radioButtonOption1 -> 1
            R.id.radioButtonOption2 -> 2
            R.id.radioButtonOption3 -> 3
            R.id.radioButtonOption4 -> 4
            else -> 0
        }

        val currentQuestion = questions[currentQuestionIndex]
        if (selectedAnswer == currentQuestion.correctOption) {
            score++
        }
    }

    private fun showResult() {
        val resultText = "Twój wynik to $score na ${questions.size}"
        textViewQuestion.text = resultText

        // Hide the next button
        buttonNext.visibility = View.GONE

        // Disable the radio group
        radioGroupOptions.isEnabled = false
    }
}

data class Question(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctOption: Int
)
