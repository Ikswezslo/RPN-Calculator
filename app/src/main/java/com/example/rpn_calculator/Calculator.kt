package com.example.rpn_calculator

import android.widget.TextView
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class Calculator(private var editBox: TextView) {
    private var stack: Stack<Double> = Stack()
    private val history: Stack<Stack<Double>> = Stack()

    fun onPressed0() {
        val newValue = editBox.text.toString() + "0"
        if(newValue != "00")
            editBox.text = newValue
    }

    fun onPressedX(x: String) {
        var value = editBox.text.toString()
        if(value == "0")
            value = ""
        value += x
        editBox.text = value
    }

    fun onPressedDot() {
        var value = editBox.text.toString()
        if(!value.contains('.')) {
            value += "."
            editBox.text = value
        }
    }

    fun onPressedDelete() {
        val value = editBox.text.toString().dropLast(1)
        editBox.text = value
    }

    fun onPressedEnter() {
        var value = editBox.text.toString()
        if(value != "") {
            pushToHistory()
            if(value.toDoubleOrNull() == null)
                value = "0"
            stack.push(value.toDouble())
            editBox.text = ""
        }
        else {
            if(stack.size >= 1)
                editBox.text = stack[stack.size-1].toString()
        }
    }

    fun onPressedSwap() {
        if(stack.size >= 2) {
            pushToHistory()
            val tmp = stack[stack.size-1]
            stack[stack.size-1] = stack[stack.size-2]
            stack[stack.size-2] = tmp
        }
    }

    fun onPressedDrop() {
        if(stack.size >= 1) {
            pushToHistory()
            stack.pop()
        }
    }

    fun onPressedClear() {
        if(stack.size != 0) {
            pushToHistory()
            stack.clear()
        }
    }

    fun onPressedUndo() {
        if(history.size > 0) {
            stack = history.pop()
        }
    }

    fun onPressedPlus() {
        if(stack.size >= 2) {
            pushToHistory()
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b+a)
        }
    }

    fun onPressedMinus() {
        if(stack.size >= 2) {
            pushToHistory()
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b-a)
        }
    }

    fun onPressedMul() {
        if(stack.size >= 2) {
            pushToHistory()
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b*a)
        }
    }

    fun onPressedDiv() {
        if(stack.size >= 2) {
            pushToHistory()
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b/a)
        }
    }

    fun onPressedPow() {
        if(stack.size >= 2) {
            pushToHistory()
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b.pow(a))
        }
    }

    fun onPressedSqrt() {
        if(stack.size >= 1) {
            pushToHistory()
            val a = stack.pop()
            stack.push(sqrt(a))
        }
    }

    fun onPressedNeg() {
        if(stack.size >= 1) {
            pushToHistory()
            val a = stack.pop()
            stack.push(-a)
        }
    }

    fun getStack(): Stack<Double> {
        return stack
    }

    private fun pushToHistory() {
        if(history.size >= 50) {
            history.removeAt(0)
        }
        history.push(stack.clone() as Stack<Double>)
    }
}