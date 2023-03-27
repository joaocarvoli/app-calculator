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
    private var storedValue = MutableLiveData<String>()
    private var actualValue = MutableLiveData<String>()
    private var storedOperator = MutableLiveData<String>()

    fun onNumberButton(number : String){
        if(storedOperator.value == null){
            var previousValue = ""
            if(actualValue.value != null){
                previousValue = actualValue.value!!
            }
            actualValue.postValue(previousValue.plus(number))
        } else {
            if(storedValue.value == null){
                if(storedValue.value != null){
                    storedValue.postValue(actualValue.value)
                }
                actualValue.postValue(number)
            } else {
                var previousValue = ""
                if(actualValue.value != null){
                    previousValue = actualValue.value!!
                }
                actualValue.postValue(previousValue.plus(number))
            }
        }
    }

    fun onOperatorButton(operator : String){
        if(storedOperator.value?.length != 0){
            doCalc(storedValue, actualValue, storedOperator)
        }
        storedValue.postValue(result.value.toString())
        storedOperator.postValue(operator)
    }

    fun result(): MutableLiveData<Double> {
        return result
    }

    fun actualValue() : MutableLiveData<String> {
        return actualValue
    }

    fun storedValue() : MutableLiveData<String> {
        return storedValue
    }

    private fun doCalc(a : MutableLiveData<String>, b : MutableLiveData<String>, operator : MutableLiveData<String>){
        when(operator.value){
            "+" -> result.postValue(calculator.add(a.value?.toDouble()!!, b.value?.toDouble()!!))
            "-" -> result.postValue(calculator.sub(a.value?.toDouble()!!, b.value?.toDouble()!!))
            "*" -> result.postValue(calculator.mul(a.value?.toDouble()!!, b.value?.toDouble()!!))
            "/" -> result.postValue(calculator.div(a.value?.toDouble()!!, b.value?.toDouble()!!))
        }
    }
}