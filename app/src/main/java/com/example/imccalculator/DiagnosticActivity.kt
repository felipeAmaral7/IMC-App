package com.example.imccalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DiagnosticActivity : AppCompatActivity() {
    lateinit var weightText : TextView
    lateinit var heightText : TextView
    lateinit var resultImcText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_diagnostic)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeVariables()
    }

    private fun initializeVariables() {
        weightText = findViewById(R.id.weight_result)
        heightText = findViewById(R.id.height_result)
        resultImcText = findViewById(R.id.diagnostic_result)

        val bundle = intent.extras
        if (bundle != null) {
            val weight = bundle.getDouble("weight")
            val height = bundle.getDouble("height")
            weightText.text = "Peso informado: $weight Kg"
            heightText.text = "Altura informada: $height m"
            resultImcText.text = calcularIMC(weight, height)
        }
    }

    private fun calcularIMC(weight : Double, height: Double) : String {
        val imc = weight / (height * height)
        val result =  when {
            (imc <= 18) -> "Baixo"
            (imc in 18.5 .. 25.0) -> "Normal"
            (imc in 25.0 .. 29.9) -> "Sobrepeso"
            (imc in 30.0 .. 39.9) -> "Obesidade"
            else -> "Acima da Obesidade"
        }
        return result
    }
}