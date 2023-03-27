package com.carvoli.calculator.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carvoli.calculator.domain.Calculator

class CalculatorViewModel : ViewModel(){
    private val TAG = "CalculatorViewModel"
    private val calculator = Calculator()
    private val state = CalculatorState
    private var result = MutableLiveData<Double>()
    private var storedValue = MutableLiveData<Double>()
    private var actualValue = MutableLiveData<Double>()
    private var storedOperator = MutableLiveData<String>()

    fun onNumberButton(number : String){
        if(state.storedOperator.isEmpty()){
            actualValue.postValue(actualValue.value?.plus(number.toDouble()))
        } else {
            storedOperator.postValue(actualValue.value?.toString())
            when(state.storedOperator){
                "+" -> result.postValue(calculator.add(state.storedValue, state.actualValue))
                "-" -> result.postValue(calculator.sub(state.storedValue, state.actualValue))
                "/" -> result.postValue(calculator.div(state.storedValue, state.actualValue))
                "x" -> result.postValue(calculator.mul(state.storedValue, state.actualValue))
            }
        }
    }

    fun onOperatorButton(operator : String){
        storedOperator.postValue(operator)
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