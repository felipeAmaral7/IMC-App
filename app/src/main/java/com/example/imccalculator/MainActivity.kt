package com.example.imccalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var weightLayout : TextInputLayout
    lateinit var heightLayout : TextInputLayout
    lateinit var textWeight : EditText
    lateinit var textHeight : EditText
    lateinit var buttonImc : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeVariables()
    }

    private fun initializeVariables() {
        weightLayout = findViewById(R.id.textInputLayout_weight)
        heightLayout = findViewById(R.id.textInputLayout_height)
        textWeight = findViewById(R.id.text_weight)
        textHeight = findViewById(R.id.text_height)
        buttonImc = findViewById(R.id.button_imc)
        buttonImc.setOnClickListener {
            if (checkIfIsNull(textWeight)) {
                weightLayout.error = "Este campo não pode ser vazio!"
            } else {
                weightLayout.isErrorEnabled = false
            }
            if (checkIfIsNull(textHeight)) {
                heightLayout.error = "Este campo não pode ser vazio!"
            } else {
                heightLayout.isErrorEnabled = false
            }
            if (!checkIfIsNull(textWeight) && !checkIfIsNull(textHeight)){
                val intent = Intent(this, DiagnosticActivity::class.java)
                intent.putExtra("weight", textWeight.text.toString().toDouble())
                intent.putExtra("height", textHeight.text.toString().toDouble())
                startActivity(intent)
            }
        }
    }
    private fun checkIfIsNull(editText : EditText) : Boolean{
        return editText.text.isEmpty()
    }
}