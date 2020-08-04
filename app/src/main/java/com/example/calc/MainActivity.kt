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
    var newCalc: Boolean = false // check if the equale was pressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * Insert Numbers
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
     * Insert Dot
     */
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            textView.append(".")
            textView_history.append((view as Button).text)

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

        if (newCalc){
            textView_history.text = curNumStr
            newCalc = false
        } else {
            textView_history.append(curNumStr)
        }

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
            textView_history.append((view as Button).text)

            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }

    /**
     * Remove last Character
     */

    fun onDelete(view: View){
        if (lastNumeric || lastDot){
            textView.text = textView.text.dropLast(1)
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
        textView_history.text = ""
        reset()
    }

    /**
     * Calculate Result and Reset Variables
     */
    fun onEqual(view: View) {
        if (lastNumeric) {
            calcOperators()
            textView.text = curResult.toString()
            newCalc = true
            reset()
        }
    }

}

