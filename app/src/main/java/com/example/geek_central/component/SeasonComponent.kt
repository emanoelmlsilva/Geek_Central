package com.example.geek_central.component

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.geek_central.R

class SeasonComponent (val view : View){

    private lateinit var spinner : Spinner
    private lateinit var listItem : MutableList<String>
    private val limitItemList : Int = 100

    init{
        initView()

        setItemSpinner()

        mountItemList()

    }

    private fun initView(){

        spinner = view.findViewById(R.id.spinnerSeason)

        listItem = mutableListOf("default")

        ArrayAdapter(view.context, android.R.layout.simple_spinner_item, listItem)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
    }

    private fun mountItemList(){
        for(item in 1..limitItemList){
            listItem.add(item, item.toString())
        }
    }

    fun getSpinner() : Spinner = spinner

    fun setItemSpinner(item : String = "default"){
        for(index in 0..limitItemList){
            if(item.equals(spinner.adapter.getItem(index).toString())) {

                spinner.setSelection(index)
                break
            }
        }

    }

    fun getCard() : View = view
}