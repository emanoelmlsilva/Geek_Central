package com.example.geek_central.component

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.geek_central.R
import com.example.geek_central.enums.TypeMethodMath
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class CounterComponent(view: View) {

    private lateinit var btnAdd: MaterialButton
    private lateinit var btnDel: MaterialButton
    private lateinit var layutValue: TextInputLayout
    private lateinit var cardView: ConstraintLayout
    private val VALUE_DEFAULT = "0.0"
    private val VALOR_MINIMUM = 0.0

    init {
        setType(view)
    }

    private fun setType(view: View) {
        btnAdd = view.findViewById(R.id.btnMax)
        btnDel = view.findViewById(R.id.btnMin)
        layutValue = view.findViewById(R.id.InputlayoutValue)
        cardView = view.findViewById(R.id.test)
    }

    fun setHint(text: String) {
        layutValue.hint = text
    }

    fun setTextLayout(value: String) {
        layutValue.editText!!.setText(value)
    }

    fun setLessValue() {
        btnDel.setOnClickListener {
            valueMoreLess(TypeMethodMath.SUB.toString())
        }
    }

    fun setMoreValue() {
        btnAdd.setOnClickListener {
            valueMoreLess(TypeMethodMath.SUM.toString())
        }
    }

    fun setInputLayoutValue() {
        layutValue.setOnClickListener {
            valueMoreLess(TypeMethodMath.SET.toString())
        }
    }

    private fun valueMoreLess(typeValue: String) {
        var counter = sum(typeValue)

        setTextLayout(counter)
    }

    fun isNullSetDefault(){
        if(layutValue.editText!!.text.toString().isBlank()) setTextLayout(VALUE_DEFAULT)

    }

    private fun sum(typeSumSub: String): String {

        var counterNew = 0.0
        val valueSum = 1.0

        try {
           if (!layutValue.editText!!.text.toString().isNullOrBlank() && checkValorMin(layutValue.editText!!.text.toString()) || typeSumSub != TypeMethodMath.SUB.toString()) {

               counterNew = layutValue.editText!!.text.toString().toDouble()

               if (typeSumSub == TypeMethodMath.SET.toString()) {
                   return counterNew.toString()
               } else if (typeSumSub == TypeMethodMath.SUB.toString()) {
                   counterNew -= valueSum
               } else if (typeSumSub == TypeMethodMath.SUM.toString()) {
                   counterNew += valueSum
               }
           }

        } catch (e: NumberFormatException) {
            counterNew = 0.0
        }

        return counterNew.toString()
    }

    private fun checkValorMin(value: String): Boolean{
        return value.toDouble().toInt() > VALOR_MINIMUM
    }

    fun getValueInput(): Double = layutValue.editText!!.text.toString().toDouble()

    fun getBtnAdd(): MaterialButton = btnAdd

    fun getBtnDel(): MaterialButton = btnDel

    fun getInputLayout(): TextInputLayout = layutValue

    fun getCard(): ConstraintLayout = cardView

}