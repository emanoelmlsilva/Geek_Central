package com.example.geek_central.component

import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.geek_central.R

class NoteComponent(val view : View) {

    private lateinit var seekBarNote : SeekBar

    private lateinit var txtNote: TextView

    private lateinit var cardView : CardView

    init{
        initView()
        setValueNote()
    }

    private fun initView(){
        seekBarNote = view.findViewById(R.id.seekBarNote)

        txtNote = view.findViewById(R.id.txtNote)

        cardView = view.findViewById(R.id.noteComponent)
    }

    fun setValueNote(value : Int = 0){
        txtNote.text = value.toString()
    }

    fun getSeekBar() : SeekBar = seekBarNote

    fun setMaxSeekBar(maxValue : Int = 5){
        seekBarNote.max = maxValue
    }

    fun getCard() : CardView = cardView
}