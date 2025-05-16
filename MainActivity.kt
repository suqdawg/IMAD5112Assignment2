package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.LinearLayout
import com.example.assignment2.QuizActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Make sure this layout file exists

        //the button from the layout
        val startButton: Button = findViewById(R.id.startButton) //  Replace with your button's ID

        //a click listener for the button
        startButton.setOnClickListener {
            // 3. Show a toast message
            Toast.makeText(this , "Starting Quiz..." , Toast.LENGTH_SHORT).show()

            // 4. Starts the QuizActivity
            val intent = Intent(this , QuizActivity::class.java) // Replace QuizActivity if needed.
            startActivity(intent)
        }
    }
}
