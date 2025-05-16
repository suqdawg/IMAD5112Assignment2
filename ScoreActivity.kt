package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var reviewButton: Button
    private lateinit var reviewCardView: CardView
    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray
    private lateinit var userAnswers: BooleanArray // To receive user's answers
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Initialize UI elements
        scoreTextView = findViewById(R.id.scoreText)
        feedbackTextView = findViewById(R.id.cardView6) // Corrected ID
        reviewButton = findViewById(R.id.reviewButton)
        reviewCardView = findViewById(R.id.cardView6)

        // Get data from the intent
        score = intent.getIntExtra("score", 0)
        questions = intent.getStringArrayExtra("questions") ?: emptyArray()  // Handle null
        answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()    // Handle null
        userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf() // Get user answers

        // score
        scoreTextView.text = "Your score is $score out of ${questions.size}"

        // feedback
        val message = when {
            score >= 3 -> "Great Job!"
            else -> "Keep practicing!"
        }
        feedbackTextView.text = message

        // review button listener
        reviewButton.setOnClickListener {
            // Start ReviewActivity and pass data
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("questions", questions)
            intent.putExtra("answers", answers)
            intent.putExtra("userAnswers", userAnswers) // Pass user answers
            startActivity(intent)
        }
    }
}
