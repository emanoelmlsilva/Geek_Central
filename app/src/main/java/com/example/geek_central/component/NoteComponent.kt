package com.example.geek_central.component

import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.geek_central.R

class NoteComponent(val view : View) {

    private lateinit var seekBarNote : SeekBar

    private lateinit var txtNote: TextView

    private lateinit var constraintView : ConstraintLayout

    private val valueInital = 10

    init{
        initView()
        setValueComponentTextNote()
    }

    private fun initView(){
        seekBarNote = view.findViewById(R.id.seekBarNote)

        txtNote = view.findViewById(R.id.txtNote)

        constraintView = view.findViewById(R.id.noteComponent)

        setMaxSeekBar(valueInital)
    }

    fun setValueComponentTextNote(value : Int = 0){
        txtNote.text = value.toString()
    }

    fun getSeekBar() : SeekBar = seekBarNote

    fun setMaxSeekBar(maxValue : Int = 5){
        seekBarNote.max = maxValue
    }

    fun getTxtNote() : TextView = txtNote

    fun getCard() : ConstraintLayout = constraintView
}