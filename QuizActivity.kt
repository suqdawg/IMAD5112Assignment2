package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    // arrays for questions and answers
    private val questions = arrayOf(
        "Nelson Mandela was the president of South Africa in 1994.",
        "The capital of France is London.",
        "The Earth is flat.",
        "Water boils at 100 degrees Celsius.",
        "The human body has 206 bones."
    )
    private val answers = arrayOf(true, false, false, true, true)

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // UI elements
        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)

        // first question
        displayQuestion()

        // OnClickListener for the true button
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        // OnClickListener for the false button
        falseButton.setOnClickListener {
            checkAnswer(false)
        }
    }

    private fun displayQuestion() {
        //the question
        if (currentQuestionIndex < questions.size) {
            questionText.text = questions[currentQuestionIndex]
        } else {
            // If all questions have been answered, move to the score activity
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("questions", questions) // Pass questions and answers for review
            intent.putExtra("answers", answers)
            startActivity(intent)
            finish() //prevent back button
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (currentQuestionIndex < answers.size) {
            if (userAnswer == answers[currentQuestionIndex]) {
                score++
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            }

            currentQuestionIndex++
            displayQuestion() //display next question
        }
    }
}
