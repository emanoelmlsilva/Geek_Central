package com.example.geek_central.component

import android.view.View
import com.example.geek_central.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class CounterComponent(view : View) {

    private lateinit var btnAdd : MaterialButton
    private lateinit var btnDel : MaterialButton
    private lateinit var layutValue : TextInputLayout

    init{
        setType(view)
    }

    private fun setType(view : View){
        btnAdd = view.findViewById(R.id.btnMax)
        btnDel = view.findViewById(R.id.btnMin)
        layutValue = view.findViewById(R.id.InputlayoutValue)
    }

    fun setHint(text : String){
        layutValue.hint = text
    }

    fun setTextLayout(value: Int){
        layutValue.editText!!.setText(value.toString())
    }

    fun getValueInput() : String = layutValue.editText!!.text.toString()

    fun getBtnAdd() : MaterialButton = btnAdd

    fun getBtnDel() : MaterialButton = btnDel
}