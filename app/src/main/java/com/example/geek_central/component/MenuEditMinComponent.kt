package com.example.geek_central.component

import android.view.View
import android.widget.TextView
import com.example.geek_central.R
import com.example.geek_central.model.WorkGeek

class MenuEditMinComponent(val view : View, val objGeek: WorkGeek) {

    private lateinit var componentCounteLeft : CounterComponent

    private lateinit var componentCounteRigth : CounterComponent

    private lateinit var title: TextView

    init{
        initView()
    }

    private fun initView(){
        title = view.findViewById(R.id.txtTitle)

        componentCounteLeft = CounterComponent(view.findViewById(R.id.edit_Left))
        componentCounteLeft.setHint("Cap. Atual")

        componentCounteRigth = CounterComponent(view.findViewById(R.id.edit_Rigth))
        componentCounteRigth.setHint("Cap. Total")

        loadingObject()

        clickButtons()
    }

    fun loadingObject(){
        title.text = objGeek!!.title
        componentCounteLeft.setTextLayout(objGeek!!.currentGeek!!)
        componentCounteRigth.setTextLayout(objGeek!!.totalGeek!!)
    }

    fun clickButtons(){
        componentCounteLeft.getBtnAdd().setOnClickListener{
            componentCounteLeft.sumMore()
        }
    }

}