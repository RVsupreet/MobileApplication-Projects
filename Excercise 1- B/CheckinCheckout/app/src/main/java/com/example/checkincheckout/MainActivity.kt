package com.example.checkincheckout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var btnCheckIn: Button
    private lateinit var btnCheckOut: Button

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        btnCheckIn = findViewById(R.id.btnCheckIn)
        btnCheckOut = findViewById(R.id.btnCheckOut)

        // Initial value
        textView.text = count.toString()

        // Check In (Increment)
        btnCheckIn.setOnClickListener {
            count++
            textView.text = count.toString()
        }

        // Check Out (Decrement)
        btnCheckOut.setOnClickListener {
            count--
            textView.text = count.toString()
        }
    }
}