package com.carvoli.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.carvoli.calculator.data.CalculatorObserver
import com.carvoli.calculator.domain.Operations

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val isThereDigit = false
    private var calculatorObserver = CalculatorObserver
    private val operations = Operations()
    private var preOperationValue : Float = 0F
    private var actualOperator : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun putOperation(view : View){
        val operation = findViewById<Button>(view.id)
        updateCalc(operation.text.toString())
    }

    fun putDigit(view : View){
        checkIfIsThereDigit()
        val digit = findViewById<Button>(view.id)
        updateValue(digit.text.toString())
    }

    fun removeDigit(view: View?){
        val tvCalculus = findViewById<TextView>(R.id.tvCalculus)
        val text = tvCalculus.text.toString()
        val clearedText = text.dropLast(1)
        tvCalculus.text = clearedText
        updateResult(clearedText)
        updateViewResult()
    }

    private fun updateValue(value : String){
        val tvCalculus = findViewById<TextView>(R.id.tvCalculus)
        val calculusText = tvCalculus.text.toString() + value
        tvCalculus.text = calculusText
        updateResult(calculusText)
        updateViewResult()
    }

    private fun updateCalc(operation : String){
        if(preOperationValue == 0F){
            preOperationValue = calculatorObserver.calcResult
            calculatorObserver.calcResult = 0F
            actualOperator = operation
        } else {
            val result = operations.doOperation(preOperationValue, calculatorObserver.calcResult, operation)
            updateResult(result)
            updateViewResult()
        }
    }


    private fun checkIfIsThereDigit(){
        if(!isThereDigit){
            val tvCalculus = findViewById<TextView>(R.id.tvCalculus)
            tvCalculus.isVisible = true
        }
    }

    private fun updateResult(value : String){
        if(value.isNotEmpty()){
            calculatorObserver.calcResult = value.toFloat()
        } else {
            calculatorObserver.calcResult = 0F
            updateViewResult()
        }
    }
    private fun updateViewResult(){
        val tvResult = findViewById<TextView>(R.id.tvResult)
        if(calculatorObserver.calcResult == 0F){
            tvResult.text = ""
        } else {
            tvResult.text = calculatorObserver.calcResult.toString()
        }
    }
}