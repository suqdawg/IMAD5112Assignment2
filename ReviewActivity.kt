package com.example.assignment2

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.view.View

class ReviewActivity : AppCompatActivity() {

    private lateinit var reviewLayout: LinearLayout
    private lateinit var exitButton: Button
    private lateinit var reviewCardView: CardView

    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray
    private lateinit var userAnswers: BooleanArray // To store user's answers
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // Initialize UI elements
        reviewLayout = findViewById(R.id.reviewLayout)
        exitButton = findViewById(R.id.exitButton)
        reviewCardView = findViewById(R.id.reviewCardView)

        // Get data from the intent
        questions = intent.getStringArrayExtra("questions") ?: emptyArray()
        answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()
        score = intent.getIntExtra("score", 0)

        // Set click listener for the exit button
        exitButton.setOnClickListener {
            finish() 
        }

        displayReview()
    }

    private fun displayReview() {
        reviewLayout.removeAllViews()

        for (i in questions.indices) {
            val questionText = TextView(this)
            questionText.text = questions[i]

            val correctAnswerText = TextView(this)
            correctAnswerText.text = "Correct Answer: ${if (answers[i]) "True" else "False"}"

            val userAnswerText = TextView(this)
            userAnswerText.text = "Your Answer: ${if (userAnswers[i]) "True" else "False"}"

            val resultText = TextView(this)
            if (userAnswers[i] == answers[i]) {
                resultText.text = "Result: Correct"
                resultText.setTextColor(getColor(android.R.color.holo_green_dark))
            } else {
                resultText.text = "Result: Incorrect"
                resultText.setTextColor(getColor(android.R.color.holo_red_dark))
            }

            // Add padding and margins
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 8, 0, 8)
            questionText.layoutParams = layoutParams
            correctAnswerText.layoutParams = layoutParams
            userAnswerText.layoutParams = layoutParams
            resultText.layoutParams = layoutParams
            questionText.setPadding(16, 8, 16, 8)
            correctAnswerText.setPadding(16, 8, 16, 8)
            userAnswerText.setPadding(16, 8, 16, 8)
            resultText.setPadding(16, 8, 16, 8)

            questionText.textSize = 18f
            correctAnswerText.textSize = 16f
            userAnswerText.textSize = 16f
            resultText.textSize = 16f

            reviewLayout.addView(questionText)
            reviewLayout.addView(correctAnswerText)
            reviewLayout.addView(userAnswerText)
            reviewLayout.addView(resultText)
        }
    }
}
