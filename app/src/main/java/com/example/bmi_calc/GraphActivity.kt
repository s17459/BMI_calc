package com.example.bmi_calc

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GraphActivity : AppCompatActivity() {
    private var buttonBack: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        buttonBack = findViewById(R.id.buttonBack)
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener { finish() }
    }
}