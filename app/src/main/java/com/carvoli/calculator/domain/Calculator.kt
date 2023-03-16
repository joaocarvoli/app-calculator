package com.carvoli.calculator.domain

class Calculator {
    fun add(a : Double, b : Double) : Double {
        return a.plus(b)
    }

    fun sub(a : Double, b : Double) : Double {
        return a.minus(b)
    }

    fun mul(a : Double, b : Double) : Double {
        return a.times(b)
    }

    fun div(a : Double, b : Double) : Double {
        if(b.toInt() != 0){
            return a.div(b)
        }
        return 0.0
    }
}