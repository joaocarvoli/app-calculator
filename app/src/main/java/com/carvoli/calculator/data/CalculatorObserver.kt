package com.carvoli.calculator.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.carvoli.calculator.BR

object CalculatorObserver : BaseObservable() {

    @get:Bindable
    var calcResult : Float = 0F
        set(value) {
            field = value
            notifyPropertyChanged(BR.calcResult)
        }
}