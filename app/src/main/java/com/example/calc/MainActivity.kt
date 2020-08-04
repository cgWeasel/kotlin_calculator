package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false // Last Input was a number
    var lastDot: Boolean = false // Only one dot per number
    var lastOp: String = "" // last operator used
    var curNumStr: String = "" // current number in String
    var curNumD: Double = 0.0 // current number converted from String to Double
    var curResult: Double = 0.0 // current Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * Append the Button.text to the TextView
     */
    fun onDigit(view: View) {
        if (lastNumeric || lastDot) {
            textView.append((view as Button).text)
        } else {
            textView.text = (view as Button).text
        }
        lastNumeric = true
    }

    /**
     * Append . to the TextView
     */
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            textView.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    /**
     * calc Operators
     */
    fun calcOperators() {
        curNumStr = textView.text.toString()
        curNumD = curNumStr.toDouble()

        when(lastOp) {
            "+" -> curResult = curResult + curNumD
            "-" -> curResult = curResult - curNumD
            "*" -> curResult = curResult * curNumD
            "/" -> curResult = curResult / curNumD
            "" -> curResult = curNumD
        }

    }

    /**
     * Click Operators
     */
    fun onOperator(view: View) {
        if (lastNumeric) {
            calcOperators()
            lastOp = (view as Button).text.toString()
            textView.text = curResult.toString()
            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }

    /**
     * Reset Variables
     */

    fun reset(){
        lastNumeric = false
        lastDot = false
        curResult = 0.0
        lastOp = ""
    }

    /**
     * Clear all
     */
    fun onClear(view: View) {
        textView.text = ""
        reset()
    }

    /**
     * Calculate Resust and Reset Variables
     */
    fun onEqual(view: View) {
        if (lastNumeric) {
            calcOperators()
            textView.text = curResult.toString()
            reset()
        }
    }

}

