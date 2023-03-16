package com.carvoli.calculator.domain

class DoOperation {
    private var calculator : Calculator = Calculator()

    fun doOperation(input: Array<String>, operator : String) : String{
        val values = preProcessing(input)

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

    private fun preProcessing(values: Array<String>) : Array<Double>{
        val a = values[0].toDouble()
        val b = values[1].toDouble()
        return arrayOf(a, b)
    }
}