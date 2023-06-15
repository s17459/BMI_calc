package com.example.bmi_calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup radioGroup;
    private Button submitButton;

    private String[] questions;
    private String[][] answerOptions;
    private String[] correctAnswers;

    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        radioGroup = findViewById(R.id.radioGroup);
        submitButton = findViewById(R.id.submitButton);

        initializeQuizData();

        displayQuestionAndOptions();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuiz();
            }
        });
    }

    private void initializeQuizData() {
        // Initialize the quiz questions, answer options, and correct answers
        questions = new String[]{
                "Pytanie 1: Co jest dobrym źródłem białka?",
                "Pytanie 2: Którą grupę żywności należy spożywać z umiarem?",
                "Pytanie 3: Co jest dobrym źródłem błonnika pokarmowego?",
                "Pytanie 4: Która metoda gotowania jest zdrowsza?",
                "Pytanie 5: Co należy ograniczyć w zdrowej diecie?",
                "Pytanie 6: Co jest dobrym źródłem zdrowych tłuszczów?"
        };

        answerOptions = new String[][]{
                {"Pierś z kurczaka", "Stek wołowy", "Łosoś"},
                {"Słodycze i desery", "Owoce i warzywa", "Pełnoziarniste"},
                {"Brokuły", "Mleko", "Biały chleb"},
                {"Grillowanie", "Smażenie", "Głębokie smażenie"},
                {"Dodane cukry", "Białko", "Woda"},
                {"Awokado", "Masło", "Lody"}
        };

        correctAnswers = new String[]{
                "Pierś z kurczaka",
                "Słodycze i desery",
                "Brokuły",
                "Grillowanie",
                "Dodane cukry",
                "Awokado"
        };

        currentQuestionIndex = 0;
    }

    private void displayQuestionAndOptions() {
        questionTextView.setText(questions[currentQuestionIndex]);

        radioGroup.removeAllViews();

        for (int i = 0; i < answerOptions[currentQuestionIndex].length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answerOptions[currentQuestionIndex][i]);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }
    }

    private void submitQuiz() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(getApplicationContext(), "Proszę wybrać opcję.", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString();

            checkAnswer(selectedAnswer);
        }
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = correctAnswers[currentQuestionIndex];

        if (selectedAnswer.equals(correctAnswer)) {
            Toast.makeText(getApplicationContext(), "Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Zła odpowiedź!", Toast.LENGTH_SHORT).show();
        }

        // Move to the next question
        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            displayQuestionAndOptions();
        } else {
            // Quiz completed
            Toast.makeText(getApplicationContext(), "Quiz ukończony!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
