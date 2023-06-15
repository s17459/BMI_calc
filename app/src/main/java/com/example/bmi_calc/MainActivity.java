package com.example.bmi_calc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private Button buttonCalculateBMI;
    private TextView textViewResult;
    private EditText editTextAge;
    private Button buttonCalculateCalories;
    private TextView textViewCaloriesResult;
    private Button buttonRecommendations;
    private Button buttonRedirectToQuiz;
    private Button buttonGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextAge = findViewById(R.id.editTextAge);
        buttonCalculateBMI = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        buttonRedirectToQuiz = findViewById(R.id.buttonQuiz);
        buttonGraph = findViewById(R.id.buttonGraphBMI);
        buttonCalculateCalories = findViewById(R.id.buttonCalculate);
        textViewCaloriesResult = findViewById(R.id.textViewCaloriesResult);
        buttonRecommendations = findViewById(R.id.buttonRecommendations);
        buttonRecommendations.setVisibility(View.GONE);

        // Set click listener for BMI calculation button
        buttonRedirectToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToQuiz();
            }
        });
        buttonRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToRecommendations();
            }
        });
        buttonCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
                calculateCalories();
            }
        });

        buttonGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });
    }


    private void calculateBMI() {
        String weightString = editTextWeight.getText().toString();
        String heightString = editTextHeight.getText().toString();

        if (weightString.isEmpty() || heightString.isEmpty()) {
            textViewResult.setText(R.string.wprowadz_dane);
            return;
        }

        float weight = Float.parseFloat(weightString);
        float height = Float.parseFloat(heightString) / 100; // Convert height to meters

        float bmi = weight / (height * height);

        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = getString(R.string.niedowaga);
        } else if (bmi < 25) {
            bmiCategory = getString(R.string.normalna_waga);
        } else if (bmi < 30) {
            bmiCategory = getString(R.string.nadwaga);
        } else {
            bmiCategory = getString(R.string.oty_o);
        }

        String result = "BMI: " + String.format("%.1f", bmi) + "\n" + bmiCategory;
        textViewResult.setText(result);
    }

    private void calculateCalories() {
        String weightString = editTextWeight.getText().toString();
        String heightString = editTextHeight.getText().toString();
        String ageString = editTextAge.getText().toString();

        if (weightString.isEmpty() || heightString.isEmpty() || ageString.isEmpty()) {
            return;
        }

        float weight = Float.parseFloat(weightString);
        float height = Float.parseFloat(heightString);
        int age = Integer.parseInt(ageString);

        // Calculate calories using Benedict-Harris formula for sedentary lifestyle
        double bmr = 66.5 + (13.75 * weight) + (5 * height) - (6.75 * age);

        String result = getString(R.string.kalorie_na_dzien) + Math.round(bmr);
        textViewCaloriesResult.setText(result);

        buttonRecommendations.setVisibility(View.VISIBLE);
    }

    private void redirectToRecommendations() {
        Intent intent = new Intent(MainActivity.this, RecommendationsActivity.class);
        startActivity(intent);
    }
    private void redirectToQuiz() {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
