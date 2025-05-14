package com.example.flashcardapp

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // Get the questions and answers from the intent
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        // Find the UI elements
        val reviewLayout = findViewById<LinearLayout>(R.id.reviewLayout)
        val exitButton = findViewById<Button>(R.id.exitButton)

        // Loop through the questions and answers, and display them in the reviewLayout
        if (questions != null && answers != null) {
            for (i in questions.indices) {
                val questionTextView = TextView(this)
                questionTextView.text = questions[i]
                questionTextView.textSize = 18f
                questionTextView.setPadding(8)
                reviewLayout.addView(questionTextView)

                val answerTextView = TextView(this)
                answerTextView.text = "Answer: ${if (answers[i]) "True" else "False"}"
                answerTextView.textSize = 16f
                answerTextView.setPadding(8)
                reviewLayout.addView(answerTextView)

                // Add a divider between questions
                val divider = android.view.View(this)
                divider.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1
                )
                divider.setBackgroundColor(resources.getColor(R.color.light_gray, theme)) // Use a color from your resources
                reviewLayout.addView(divider)
            }
        }

        // Set OnClickListener for the exit button to finish the activity
        exitButton.setOnClickListener {
            finish()
        }
    }
}
