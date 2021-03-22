package com.android_training.propertymanagement.testcase

interface CalculatorService {
    fun add(input1: Double, input2: Double): Double
    fun subtract(input1: Double, input2: Double): Double
    fun multiply(input1: Double, input2: Double): Double
    fun divide(input1: Double, input2: Double): Double
}
class MathApplication {
    private var calcService: CalculatorService? = null
    fun setCalculatorService(calcService: CalculatorService?) {
        this.calcService = calcService
    }

    fun add(input1: Double, input2: Double): Double {
        return calcService!!.add(input1, input2)
    }

    fun subtract(input1: Double, input2: Double): Double {
        return calcService!!.subtract(input1, input2)
    }

    fun multiply(input1: Double, input2: Double): Double {
        return calcService!!.multiply(input1, input2)
    }

    fun divide(input1: Double, input2: Double): Double {
        return calcService!!.divide(input1, input2)
    }
}