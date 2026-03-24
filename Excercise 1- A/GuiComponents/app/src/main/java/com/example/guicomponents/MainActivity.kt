package com.example.guicomponents

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var btnSize: Button
    private lateinit var btnTextColor: Button
    private lateinit var btnBgColor: Button

    private var textSize = 10f

    private val colors = arrayOf(Color.RED, Color.BLUE, Color.GREEN)
    private var textColorIndex = 0
    private var bgColorIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        btnSize = findViewById(R.id.btnSize)
        btnTextColor = findViewById(R.id.btnTextColor)
        btnBgColor = findViewById(R.id.btnBgColor)

        // Change Text Size
        btnSize.setOnClickListener {
            textSize += 5f
            if (textSize > 25f) {
                textSize = 5f
            }
            textView.textSize = textSize
        }

        // Change Text Color
        btnTextColor.setOnClickListener {
            textColorIndex = (textColorIndex + 1) % colors.size
            textView.setTextColor(colors[textColorIndex])
        }

        // Change Background Color
        btnBgColor.setOnClickListener {
            bgColorIndex = (bgColorIndex + 1) % colors.size
            textView.setBackgroundColor(colors[bgColorIndex])
        }
    }
}