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
    private var actualOperator = MutableLiveData<String>()

    fun onDigitClicked(number : String){
        var previousValue = ""
        if(actualValue.value?.length != 0 && actualValue.value?.length != null) {
            previousValue = actualValue.value!!
        }
        actualValue.postValue("$previousValue$number")
    }

    fun onOperatorClicked(operator : String){
        if(storedOperator.value?.length == null || storedOperator.value?.length == 0){
            storedOperator.postValue(operator)
            storedValue.postValue(actualValue.value)
            actualValue.postValue("")
        } else if(storedValue.value?.length!! > 0 && actualValue.value?.length!! > 0){
            storedOperator.postValue(operator)
            storedValue.postValue(doCalc(storedValue, actualValue, storedOperator).toString())
            actualValue.postValue("")
        }
        else {
            storedValue.postValue(doCalc(storedValue, actualValue, storedOperator).toString())
            storedOperator.postValue("")
        }
    }

    fun doCalc(a : MutableLiveData<String> = storedValue,
               b : MutableLiveData<String> = actualValue,
               operator : MutableLiveData<String> = storedOperator) : Double {
        var result = 0.0
        when(operator.value){
            "+" -> result = calculator.add(a.value?.toDouble()!!, b.value?.toDouble()!!)
            "-" -> result = calculator.sub(a.value?.toDouble()!!, b.value?.toDouble()!!)
            "*" -> result = calculator.mul(a.value?.toDouble()!!, b.value?.toDouble()!!)
            "/" -> result = calculator.div(a.value?.toDouble()!!, b.value?.toDouble()!!)
        }
        storedValue.postValue(result.toString())
        return result
    }

    fun getStoredValue(): MutableLiveData<String> {
        return storedValue
    }
    fun getStoredOperator(): MutableLiveData<String> {
        return storedOperator
    }
    fun getActualValue(): MutableLiveData<String> {
        return actualValue
    }

    fun setStoredValue(value : String){
        storedValue.postValue(value)
    }

    fun setActualValue(value : String){
        actualValue.postValue(value)
    }

    fun setStoredOperator(value : String){
        storedOperator.postValue(value)
    }
}