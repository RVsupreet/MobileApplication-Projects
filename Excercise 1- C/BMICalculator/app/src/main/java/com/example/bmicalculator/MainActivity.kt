package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {

            val weightStr = etWeight.text.toString()
            val heightStr = etHeight.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {

                val weight = weightStr.toFloat()
                val heightCm = heightStr.toFloat()

                val heightM = heightCm / 100
                val bmi = weight / (heightM * heightM)

                val category = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 24.9 -> "Normal"
                    bmi < 29.9 -> "Overweight"
                    else -> "Obese"
                }

                tvResult.text = "BMI: %.2f\nCategory: %s".format(bmi, category)

            } else {
                tvResult.text = "Please enter weight and height"
            }
        }
    }
}