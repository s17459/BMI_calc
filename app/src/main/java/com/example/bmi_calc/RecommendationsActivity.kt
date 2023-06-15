package com.example.bmi_calc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecommendationsActivity : AppCompatActivity() {
    private var textViewRecommendations: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendations)
        textViewRecommendations = findViewById(R.id.textViewRecommendations)

        // Display the recommendations
        displayRecommendations()
    }

    private fun displayRecommendations() {
        val recommendation1 =
            "Posiłek 1: Grillowana pierś z kurczaka z warzywami gotowanymi na parze i komosą ryżową."
        val recommendation2 =
            "Posiłek 2: Łosoś z pieczonymi słodkimi ziemniakami i smażonym szpinakiem."
        val recommendations = """
            $recommendation1
            
            $recommendation2
            """.trimIndent()
        textViewRecommendations!!.text = recommendations
    }
}