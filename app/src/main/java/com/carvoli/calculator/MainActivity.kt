package com.carvoli.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import com.carvoli.calculator.ui.CalculatorViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var calculatorViewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
    }


    fun getDigit(view : View){
        val digit = findViewById<Button>(view.id)
        updateTextViewCalculus(digit.text)
        calculatorViewModel.onNumberButton(digit.text.toString())
    }

    fun getOperator(view : View){
        val operator = findViewById<Button>(view.id)
        updateTextViewCalculus(operator.text)
        calculatorViewModel.onOperatorButton(operator.text.toString())
    }

    fun removeDigit(view : View?){
        val tvCalculus = findViewById<TextView>(R.id.tvCalculus)
        tvCalculus.text = tvCalculus.text.dropLast(1)
    }

    fun showResult(view : View?){
        val tvResult = findViewById<TextView>(R.id.tvResult)
        tvResult.isVisible = true
    }


    private fun updateTextViewCalculus(value : CharSequence){
        val tvCalculus = findViewById<TextView>(R.id.tvCalculus)
        val textUpdated = tvCalculus.text.toString() + value
        tvCalculus.text = textUpdated
    }
}