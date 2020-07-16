package com.example.geek_central.Component

import android.view.View
import androidx.cardview.widget.CardView
import com.example.geek_central.R
import com.google.android.material.textfield.TextInputLayout

class CounterComponent(private val view : View) {

    private lateinit var btnAdd : CardView
    private lateinit var btnDel : CardView
    private lateinit var layutValue : TextInputLayout

    init{
        setType(view)
    }

    private fun setType(view : View){
        btnAdd = view.findViewById(R.id.cardViewAdd)
        btnDel = view.findViewById(R.id.cardViewDel)
        layutValue = view.findViewById(R.id.InputlayoutValue)
    }

    fun setHint(text : String){
        layutValue.hint = text
    }

    fun getValueInput() : String = layutValue.editText!!.text.toString()

    fun getBtnAdd() : CardView = btnAdd

    fun getBtnDel() : CardView = btnDel
}