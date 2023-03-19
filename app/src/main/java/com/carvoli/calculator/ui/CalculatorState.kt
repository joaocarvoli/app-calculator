package com.carvoli.calculator.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.carvoli.calculator.BR

object CalculatorState : BaseObservable() {

    @get:Bindable
    var result : Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.result)
        }

    var storedValue : Double = 0.0
    var actualValue : Double = 0.0
    var storedOperator : String = ""
}