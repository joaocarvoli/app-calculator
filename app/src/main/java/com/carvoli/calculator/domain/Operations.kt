package com.carvoli.calculator.domain

class Operations {
    private var calculator : Calculator = Calculator()

    fun doOperation(a : Float, b : Float, operator: String) : String{
        val values = preProcessing(a, b)

        val result = if(operator == "+"){
            calculator.add(values[0], values[1]).toString()
        } else if(operator == "-"){
            calculator.sub(values[0], values[1]).toString()
        } else if(operator == "*"){
            calculator.mul(values[0], values[1]).toString()
        } else if(operator == "/"){
            calculator.div(values[0], values[1]).toString()
        } else {
            return "operation not supported"
        }

        return result
    }

    private fun preProcessing(a : Float, b : Float) : Array<Double>{
        return arrayOf(a.toDouble(), b.toDouble())
    }
}