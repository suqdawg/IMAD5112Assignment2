package com.example.assignment2

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.view.View
import android.widget.Button // Import Button
import android.widget.Toast

class ReviewActivity : AppCompatActivity() {

    private lateinit var reviewLayout: LinearLayout
    private lateinit var exitButton: Button // Declare exitButton
    private lateinit var reviewCardView: CardView

    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray
    private lateinit var userAnswers: BooleanArray // To store user's answers
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review) // Create activity_review.xml

        // Initialize UI elements
        reviewLayout = findViewById(R.id.reviewText)
        exitButton = findViewById(R.id.exitButton)
        reviewCardView = findViewById(R.id.cardView5)

        // Get data from the intent
        questions = intent.getStringArrayExtra("questions") ?: emptyArray()
        answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()
        score = intent.getIntExtra("score", 0)

        // Set click listener for the exit button
        exitButton.setOnClickListener {
            Toast.makeText(this, "Closing Quiz...", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
        }

        displayReview()
    }

    private fun displayReview() {
        reviewLayout.removeAllViews()

        for (i in questions.indices) {
            val linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.setPadding(16, 16, 16, 16)

            val questionText = TextView(this)
            questionText.text = questions[i]
            questionText.textSize = 18f
            questionText.setTextColor(getColor(R.color.black))

            val correctAnswerText = TextView(this)
            correctAnswerText.text = "Correct Answer: ${if (answers[i]) "True" else "False"}"
            correctAnswerText.textSize = 16f
            correctAnswerText.setTextColor(getColor(R.color.black))

            val userAnswerText = TextView(this)
            userAnswerText.text = "Your Answer: ${if (userAnswers[i]) "True" else "False"}"
            userAnswerText.textSize = 16f
            userAnswerText.setTextColor(getColor(R.color.black))

            val resultText = TextView(this)
            resultText.text = "Result: ${if (userAnswers[i] == answers[i]) "Correct" else "Incorrect"}"
            resultText.textSize = 16f
            resultText.setTextColor(
                if (userAnswers[i] == answers[i]) getColor(R.color.green_dark)  // corrected
                else getColor(R.color.red_dark)    // corrected
            )

            linearLayout.addView(questionText)
            linearLayout.addView(correctAnswerText)
            linearLayout.addView(userAnswerText)
            linearLayout.addView(resultText)

            reviewLayout.addView(linearLayout)
        }
    }
}
