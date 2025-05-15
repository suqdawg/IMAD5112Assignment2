package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.cardview.widget.CardView

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var reviewButton: Button
    private lateinit var reviewCardView: CardView
    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Initialize UI elements
        scoreTextView = findViewById(R.id.scoreText)
        feedbackTextView = findViewById(R.id.cardView5)
        reviewButton = findViewById(R.id.reviewButton)
        reviewCardView = findViewById(R.id.cardView6)

        // data from the intent
        score = intent.getIntExtra("score", 0)
        questions = intent.getStringArrayExtra("questions") ?: emptyArray()
        answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()

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
            showReview()
        }

        reviewCardView.visibility = View.GONE
    }
}
