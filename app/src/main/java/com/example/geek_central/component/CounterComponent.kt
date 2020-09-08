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
    private val VALUE_DEFAULT = 0

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

    fun setTextLayout(value: Int) {
        layutValue.editText!!.setText(value.toString())
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
        var counter = if (layutValue.editText!!.text.toString()
                .isBlank()
        ) 0 else layutValue.editText!!.text.toString().toInt()

        counter = sum(counter, typeValue)

        setTextLayout(counter)
    }

    fun isNullSetDefault(){
        if(layutValue.editText!!.text.toString().isBlank()) setTextLayout(VALUE_DEFAULT)

    }

    private fun sum(counterWhen: Int, typeSumSub: String): Int {

        var counterNew = 0
        val valueSum = 1

        if (typeSumSub.equals(TypeMethodMath.SET.toString())) {
            return counterWhen
        } else if (typeSumSub.equals(TypeMethodMath.SUB.toString())) {
            counterNew = counterWhen - valueSum
        } else if (typeSumSub.equals(TypeMethodMath.SUM.toString())) {
            counterNew = counterWhen + valueSum
        }

        return counterNew
    }

    fun getValueInput(): Int = layutValue.editText!!.text.toString().toInt()

    fun getBtnAdd(): MaterialButton = btnAdd

    fun getBtnDel(): MaterialButton = btnDel

    fun getInputLayout(): TextInputLayout = layutValue

    fun getCard(): ConstraintLayout = cardView
}