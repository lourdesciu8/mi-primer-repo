package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var operacion: String = ""
    private var num1: Double = 0.0
    private var num2: Double = 0.0
    private var nuevaOperacion: Boolean = true
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar el estado de la variable resultado
        result = savedInstanceState?.getDouble("resultado") ?: 0.0
        binding.textViewResult.text = result.toString()

    // Configuración de los listeners para todos los botones
        binding.button0.setOnClickListener(this)
        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
        binding.button8.setOnClickListener(this)
        binding.button9.setOnClickListener(this)
        binding.buttonMas.setOnClickListener(this)
        binding.buttonMenos.setOnClickListener(this)
        binding.buttonMult.setOnClickListener(this)
        binding.buttonDiv.setOnClickListener(this)
        binding.buttonIgual.setOnClickListener(this)
        binding.buttonReset.setOnClickListener(this)

        // Listeners para los botones en orientación landscape
        binding.buttonSen?.setOnClickListener { calcTrigonom("sin") }
        binding.buttonCos?.setOnClickListener { calcTrigonom("cos") }
        binding.buttonTan?.setOnClickListener { calcTrigonom("tan") }
        binding.buttonRaiz?.setOnClickListener { calcRaiz() }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.button0.id -> agregarAlResultado("0")
            binding.button1.id -> agregarAlResultado("1")
            binding.button2.id -> agregarAlResultado("2")
            binding.button3.id -> agregarAlResultado("3")
            binding.button4.id -> agregarAlResultado("4")
            binding.button5.id -> agregarAlResultado("5")
            binding.button6.id -> agregarAlResultado("6")
            binding.button7.id -> agregarAlResultado("7")
            binding.button8.id -> agregarAlResultado("8")
            binding.button9.id -> agregarAlResultado("9")

            binding.buttonMas.id -> selOperacion("+")
            binding.buttonMenos.id -> selOperacion("-")
            binding.buttonMult.id -> selOperacion("*")
            binding.buttonDiv.id -> selOperacion("/")

            binding.buttonIgual.id -> calcResultados()
            binding.buttonReset.id -> reset()
        }
    }

    private fun agregarAlResultado(value: String) {
        if (nuevaOperacion) {
            binding.textViewResult.text = value
            nuevaOperacion = false
        } else {
            binding.textViewResult.append(value)
        }
    }

    private fun selOperacion(op: String) {
        operacion = op
        num1 = binding.textViewResult.text.toString().toDouble()
        nuevaOperacion = true
    }

    private fun calcResultados() {
        num2 = binding.textViewResult.text.toString().toDouble()
        result = when (operacion) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }
        binding.textViewResult.text = result.toString()
        nuevaOperacion = true
    }

    private fun reset() {
        binding.textViewResult.text = "0"
        operacion = ""
        num1 = 0.0
        num2 = 0.0
        result = 0.0
        nuevaOperacion = true
    }

    // Guardar el valor del resultado al cambiar la orientación
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("resultado", result)
    }

    // Función para cálculos trigonométricos de seno, coseno y tangente
    private fun calcTrigonom(function: String) {
        val value = binding.textViewResult.text.toString().toDouble()
        result = when (function) {
            "sin" -> sin(Math.toRadians(value))
            "cos" -> cos(Math.toRadians(value))
            "tan" -> tan(Math.toRadians(value))
            else -> 0.0
        }
        binding.textViewResult.text = result.toString()
        nuevaOperacion = true
    }

    // Función para calcular el cuadrado de un número
    private fun calcRaiz() {
        val value = binding.textViewResult.text.toString().toDouble()
        result = value * value
        binding.textViewResult.text = result.toString()
        nuevaOperacion = true
    }
}
