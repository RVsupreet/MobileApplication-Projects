package com.example.calculatorapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var value1: EditText
    private lateinit var value2: EditText
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        value1 = findViewById(R.id.etValue1)
        value2 = findViewById(R.id.etValue2)
        result = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btnAdd).setOnClickListener { calculate('+') }
        findViewById<Button>(R.id.btnSub).setOnClickListener { calculate('-') }
        findViewById<Button>(R.id.btnMul).setOnClickListener { calculate('*') }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { calculate('/') }
        findViewById<Button>(R.id.btnMod).setOnClickListener { calculate('%') }
    }

    private fun calculate(op: Char) {
        val num1Str = value1.text.toString()
        val num2Str = value2.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Enter both values", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = num1Str.toDouble()
        val num2 = num2Str.toDouble()

        val res = when (op) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
            '%' -> num1 % num2
            else -> 0.0
        }

        result.text = res.toString()
    }
}