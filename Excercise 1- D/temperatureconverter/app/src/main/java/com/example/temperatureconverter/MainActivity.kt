package com.example.temperatureconverter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTemp: EditText
    private lateinit var rgFrom: RadioGroup
    private lateinit var rgTo: RadioGroup
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTemp = findViewById(R.id.etTemp)
        rgFrom = findViewById(R.id.rgFrom)
        rgTo = findViewById(R.id.rgTo)
        btnConvert = findViewById(R.id.btnConvert)
        tvResult = findViewById(R.id.tvResult)

        btnConvert.setOnClickListener {

            val tempStr = etTemp.text.toString()

            if (tempStr.isEmpty()) {
                tvResult.text = "Enter temperature"
                return@setOnClickListener
            }

            val temp = tempStr.toDouble()

            val fromId = rgFrom.checkedRadioButtonId
            val toId = rgTo.checkedRadioButtonId

            if (fromId == -1 || toId == -1) {
                tvResult.text = "Select units"
                return@setOnClickListener
            }

            val result = convertTemperature(temp, fromId, toId)

            tvResult.text = "Result: %.2f".format(result)
        }
    }

    private fun convertTemperature(value: Double, from: Int, to: Int): Double {

        // Convert FROM → Celsius first
        val celsius = when (from) {
            R.id.rbFromC -> value
            R.id.rbFromF -> (value - 32) * 5 / 9
            R.id.rbFromK -> value - 273.15
            else -> value
        }

        // Convert Celsius → TO
        return when (to) {
            R.id.rbToC -> celsius
            R.id.rbToF -> (celsius * 9 / 5) + 32
            R.id.rbToK -> celsius + 273.15
            else -> celsius
        }
    }
}