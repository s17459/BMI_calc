package com.example.bmi_calc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecommendationsActivity extends AppCompatActivity {

    private TextView textViewRecommendations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        textViewRecommendations = findViewById(R.id.textViewRecommendations);

        // Display the recommendations
        displayRecommendations();
    }

    private void displayRecommendations() {
        String recommendation1 = "Posiłek 1: Grillowana pierś z kurczaka z warzywami gotowanymi na parze i komosą ryżową.";
        String recommendation2 = "Posiłek 2: Łosoś z pieczonymi słodkimi ziemniakami i smażonym szpinakiem.";

        String recommendations = recommendation1 + "\n\n" + recommendation2;

        textViewRecommendations.setText(recommendations);
    }
}
