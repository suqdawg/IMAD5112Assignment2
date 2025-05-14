package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI elements
        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val startButton = findViewById<Button>(R.id.startButton)

        // welcome message and description
        welcomeText.text = "Welcome to Flashcard Study App!"
        descriptionText.text = "This app will help you study with flashcards. You will be presented with a series of questions, and you can answer them by selecting 'True' or 'False'. Good luck!"

        // OnClickListener for the start button to navigate to the QuizActivity
        startButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}
