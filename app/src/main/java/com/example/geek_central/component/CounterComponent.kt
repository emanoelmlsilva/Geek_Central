package com.example.geek_central.component

import android.view.View
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.geek_central.R
import com.example.geek_central.enums.TypeMethodMath
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class CounterComponent(view : View) {

    private lateinit var btnAdd : MaterialButton
    private lateinit var btnDel : MaterialButton
    private lateinit var layutValue : TextInputLayout
    private lateinit var cardView : ConstraintLayout

    init{
        setType(view)
    }

    private fun setType(view : View){
        btnAdd = view.findViewById(R.id.btnMax)
        btnDel = view.findViewById(R.id.btnMin)
        layutValue = view.findViewById(R.id.InputlayoutValue)
        cardView = view.findViewById(R.id.test)
    }

    fun setHint(text : String){
        layutValue.hint = text
    }

    fun setTextLayout(value: Int){
        layutValue.editText!!.setText(value.toString())
    }

    fun valueMoreLess(typeValue: String){
        var counter = layutValue.editText!!.text.toString().toInt()

        counter = sum(counter, typeValue)

        setTextLayout(counter)
    }

    private fun sum(counterWhen: Int, typeSumSub: String) : Int{

        var counterNew = 0
        val valueSum = 1

        if(typeSumSub.equals(TypeMethodMath.SET.toString())){
            return counterWhen
        }else  if(typeSumSub.equals(TypeMethodMath.SUB.toString())){
            counterNew = counterWhen - valueSum
        }else if(typeSumSub.equals(TypeMethodMath.SUM.toString())){
            counterNew = counterWhen + valueSum
        }

        return counterNew
    }

    fun getValueInput() : Int = layutValue.editText!!.text.toString().toInt()

    fun getBtnAdd() : MaterialButton = btnAdd

    fun getBtnDel() : MaterialButton = btnDel

    fun getInputLayout(): TextInputLayout = layutValue

    fun getCard() : ConstraintLayout = cardView
}