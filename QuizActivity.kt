package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class QuizActivity : AppCompatActivity() {

    // the arrays
    private val questions = arrayOf(
        "Nelson Mandela was the president of South Africa in 1994?",
        "The capital of France is London?",
        "The Earth is flat?",
        "Water boils at 100 degrees Celsius?",
        "The Great Wall of China is visible from space?"
    )
    private val answers = booleanArrayOf(true, false, false, true, false)

    // variables for UI elements
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var feedbackCardView: CardView
    private lateinit var feedbackTextView: TextView

    // variable for score
    private var score = 0
    private var currentQuestionIndex = 0
    private val userAnswers = mutableListOf<Boolean>() //store's user's answers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initializes UI elements
        questionTextView = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        feedbackCardView = findViewById(R.id.cardView7)
        feedbackTextView = findViewById(R.id.feedbackView)

        // for the listeners for the buttons
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
        nextButton.setOnClickListener { nextQuestion() }

        // Displays the first question
        displayQuestion()
        feedbackCardView.visibility = View.GONE // Hide feedback card initially
        nextButton.visibility = View.GONE
    }

    // Function to display a question
    private fun displayQuestion() {
        questionTextView.text = questions[currentQuestionIndex]
        // Enables True and False buttons for each question.
        trueButton.isEnabled = true
        falseButton.isEnabled = true
        feedbackCardView.visibility = View.GONE // Hides feedback when a new question is displayed.
        nextButton.visibility = View.GONE
    }

    // Function to check the answer
    private fun checkAnswer(userAnswer: Boolean) {
        userAnswers.add(userAnswer) // Store's the user's answer
        val correctAnswer = answers[currentQuestionIndex]
        if (userAnswer == correctAnswer) {
            feedbackTextView.text = "Correct!"
            score++
        } else {
            feedbackTextView.text = "Incorrect!"
        }
        feedbackCardView.visibility = View.VISIBLE // Show feedback
        trueButton.isEnabled = false  // Disables buttons after answering
        falseButton.isEnabled = false // Disables buttons after answering
        nextButton.visibility = View.VISIBLE //show the next button
    }

    // Function to move to the next question
    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            displayQuestion()
        } else {
            // Shows the result (all questions answered)
            Toast.makeText(this, "Your score is $score out of ${questions.size}", Toast.LENGTH_LONG).show()
            finish() //ends the activity
        }
    }
}
