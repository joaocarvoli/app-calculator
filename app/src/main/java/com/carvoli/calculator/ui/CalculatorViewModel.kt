package com.carvoli.calculator.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carvoli.calculator.domain.Calculator

class CalculatorViewModel : ViewModel(){
    private val calculator = Calculator()
    private var storedValue = MutableLiveData<String>()
    private var actualValue = MutableLiveData<String>()
    private var storedOperator = MutableLiveData<String>()

    fun onDigitClicked(number : String){
        val previousValue = ""
        if(actualValue.value?.length != 0){
            actualValue.postValue(storedOperator.value)
        } else {
            actualValue.postValue("$previousValue$number")
        }
    }

    fun onOperatorClicked(operator : String){
        if(storedOperator.value?.length == 0){
            storedValue.postValue(actualValue.value)
            actualValue.postValue("")
        } else {
            if(actualValue.value?.length != 0 && storedValue.value?.length!! != 0 && storedOperator.value?.length != 0) {
                storedValue.postValue(doCalc(storedValue, actualValue, storedOperator))
                storedOperator.postValue("")
            }
        }
    }

    private fun doCalc(a : MutableLiveData<String>, b : MutableLiveData<String>, operator : MutableLiveData<String>) : String{
        var result = 0.0
        when(operator.value){
            "+" -> result = calculator.add(a.value?.toDouble()!!, b.value?.toDouble()!!)
            "-" -> result = calculator.sub(a.value?.toDouble()!!, b.value?.toDouble()!!)
            "*" -> result = calculator.mul(a.value?.toDouble()!!, b.value?.toDouble()!!)
            "/" -> result = calculator.div(a.value?.toDouble()!!, b.value?.toDouble()!!)
        }
        return result.toString()
    }
}