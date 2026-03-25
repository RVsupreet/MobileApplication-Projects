package com.example.scientificcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                onDigit((it as Button).text.toString())
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { onOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { onOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperator("/") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { onEqual() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClear() }

        // Scientific functions
        findViewById<Button>(R.id.btnSin).setOnClickListener { applyFunction("sin") }
        findViewById<Button>(R.id.btnCos).setOnClickListener { applyFunction("cos") }
        findViewById<Button>(R.id.btnTan).setOnClickListener { applyFunction("tan") }
        findViewById<Button>(R.id.btnLog).setOnClickListener { applyFunction("log") }
        findViewById<Button>(R.id.btnSqrt).setOnClickListener { applyFunction("sqrt") }
    }

    private fun onDigit(value: String) {
        currentInput += value
        display.text = currentInput
        lastNumeric = true
    }

    private fun onOperator(op: String) {
        if (lastNumeric) {
            currentInput += op
            display.text = currentInput
            lastNumeric = false
            lastDot = false
        }
    }

    private fun onClear() {
        currentInput = ""
        display.text = "0"
        lastNumeric = false
        lastDot = false
    }

    private fun onEqual() {
        try {
            val result = evaluateExpression(currentInput)
            display.text = result.toString()
            currentInput = result.toString()
        } catch (e: Exception) {
            display.text = "Error"
        }
    }

    private fun applyFunction(func: String) {
        try {
            val value = currentInput.toDouble()
            val result = when (func) {
                "sin" -> sin(Math.toRadians(value))
                "cos" -> cos(Math.toRadians(value))
                "tan" -> tan(Math.toRadians(value))
                "log" -> log10(value)
                "sqrt" -> sqrt(value)
                else -> value
            }
            display.text = result.toString()
            currentInput = result.toString()
        } catch (e: Exception) {
            display.text = "Error"
        }
    }

    // Simple expression evaluator
    private fun evaluateExpression(expr: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expr.length) expr[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseFactor()
                        eat('/'.code) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()

                var x: Double
                val startPos = pos

                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else {
                    while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                    x = expr.substring(startPos, pos).toDouble()
                }

                return x
            }
        }.parse()
    }
}