package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.toColorInt

class Result_Activity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }

    private val returnButton : Button
        get() = findViewById(R.id.return_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.background_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(intent != null) {
            val colorCode = intent.getStringExtra(COLOR_KEY)

            val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)

            try {
                backgroundScreen.setBackgroundColor("#$colorCode".toColorInt())
            } catch(ex: IllegalArgumentException) {
                Intent().let {
                    errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(RESULT_OK, errorIntent)
                    finish()
                }
            }

            val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message, colorCode?.uppercase())
        }

        returnButton.setOnClickListener {
            this.finish()
        }
    }
}