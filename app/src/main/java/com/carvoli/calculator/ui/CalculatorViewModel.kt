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
        } else {
            storedValue.postValue(doCalc(storedValue, actualValue, storedOperator))
            storedOperator.postValue("")
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

    fun getStoredValue(): MutableLiveData<String> {
        return storedValue
    }
    fun getStoredOperator(): MutableLiveData<String> {
        return storedOperator
    }
    fun getActualValue(): MutableLiveData<String> {
        return actualValue
    }

    fun updateResult(): MutableLiveData<List<String>> {
        val list = arrayListOf("","")
        val mutableList = MutableLiveData<List<String>>()

        if(storedValue.value != null && actualValue.value != null){
            list.add(storedValue.value!!)
            list.add(actualValue.value!!)
        }

        mutableList.postValue(list)

        return mutableList
    }

}