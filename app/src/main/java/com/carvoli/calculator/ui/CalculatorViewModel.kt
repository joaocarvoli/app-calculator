package com.carvoli.calculator.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carvoli.calculator.domain.Calculator

class CalculatorViewModel : ViewModel(){
    private val TAG = "CalculatorViewModel"
    private val calculator = Calculator()
    private val state = CalculatorState

    fun onNumberButton(number : String){
        if(state.storedOperator.isEmpty()){
            state.actualValue += number.toDouble()
        } else {
            state.storedValue = state.actualValue
            when(state.storedOperator){
                "+" -> {
                    state.result = calculator.add(state.storedValue, state.actualValue)
                }
                "-" -> {
                    state.result = calculator.sub(state.storedValue, state.actualValue)
                }
                "/" -> {
                    state.result = calculator.div(state.storedValue, state.actualValue)
                }
                "x" -> {
                    state.result = calculator.mul(state.storedValue, state.actualValue)
                }
            }
        }
    }

    fun onOperatorButton(operator : String){
        state.storedOperator = operator
        /*Log.d(TAG, "HERE")
        if(state.storedOperator.isEmpty()){
            state.storedOperator = operator
            state.storedValue = state.actualValue
            state.actualValue = 0.0
        } else {
            when(state.storedOperator){
                "+" -> {
                    state.result = calculator.add(state.storedValue, state.actualValue)
                }
                "-" -> {
                    state.result = calculator.sub(state.storedValue, state.actualValue)
                }
                "/" -> {
                    state.result = calculator.div(state.storedValue, state.actualValue)
                }
                "x" -> {
                    state.result = calculator.mul(state.storedValue, state.actualValue)
                }
            }
        }*/
    }
}