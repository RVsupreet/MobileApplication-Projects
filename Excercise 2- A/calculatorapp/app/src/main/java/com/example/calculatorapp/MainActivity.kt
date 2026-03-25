pacom.example.calculatorappckage

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var tvResult: TextView

    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnMul: Button
    private lateinit var btnDiv: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        tvResult = findViewById(R.id.tvResult)

        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnMul = findViewById(R.id.btnMul)
        btnDiv = findViewById(R.id.btnDiv)

        btnAdd.setOnClickListener { calculate("+") }
        btnSub.setOnClickListener { calculate("-") }
        btnMul.setOnClickListener { calculate("*") }
        btnDiv.setOnClickListener { calculate("/") }
    }

    private fun calculate(op: String) {
        val n1Str = etNum1.text.toString()
        val n2Str = etNum2.text.toString()

        if (n1Str.isEmpty() || n2Str.isEmpty()) {
            tvResult.text = "Enter both numbers"
            return
        }

        val n1 = n1Str.toDouble()
        val n2 = n2Str.toDouble()

        val result = when (op) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> {
                if (n2 == 0.0) {
                    tvResult.text = "Cannot divide by zero"
                    return
                }
                n1 / n2
            }
            else -> 0.0
        }

        tvResult.text = "Result: $result"
    }
}