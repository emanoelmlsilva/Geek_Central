package com.example.geek_central.component

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.geek_central.R

class SeasonComponent (val view : View){

    private lateinit var spinner : Spinner
    private lateinit var listItem : MutableList<String>

    init{
        initView()

        setItemSpinner()
    }

    private fun initView(){

        spinner = view.findViewById(R.id.spinnerSeason)

        listItem = mutableListOf("default")

        setList()

        ArrayAdapter(view.context, android.R.layout.simple_spinner_item, listItem)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
    }

    private fun mountItemList(count : Int){
        for(item in 1..count){
            listItem.add(item, item.toString())
        }
    }

    fun getSpinner() : Spinner = spinner

    fun setItemSpinner(itemPostion : Int = 0){
        spinner.setSelection(itemPostion)
    }

    fun setList(newCountItem : Int = 5){
        mountItemList(newCountItem)
    }

    fun getCard() : View = view
}