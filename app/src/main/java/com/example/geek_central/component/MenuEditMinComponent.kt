package com.example.geek_central.component

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.geek_central.R
import com.example.geek_central.enums.TypeMethodMath
import com.example.geek_central.model.WorkGeek

class MenuEditMinComponent(val view : View, val objGeek: WorkGeek) {

    private lateinit var includeEditMinComponet : ConstraintLayout

    private lateinit var componentCounteLeft : CounterComponent

    private lateinit var componentCounteRigth : CounterComponent

    private lateinit var title: TextView

    init{
        initView()
    }

    private fun initView(){

        title = view.findViewById(R.id.txtTitle)

        title.text = objGeek!!.title

        includeEditMinComponet = view.findViewById(R.id.constraintEditMenuMin)

        componentCounteLeft = CounterComponent(view.findViewById(R.id.edit_Left))
        componentCounteLeft.setHint("Cap. Atual")

        componentCounteRigth = CounterComponent(view.findViewById(R.id.edit_Rigth))
        componentCounteRigth.setHint("Cap. Total")

        loadingObject()

        clickButtons(componentCounteLeft)
        clickButtons(componentCounteRigth)
    }

    fun setVisible(visible : Int){
        includeEditMinComponet.visibility = visible
    }

    fun loadingObject(){

        componentCounteLeft.setTextLayout(objGeek!!.currentGeek!!)
        componentCounteRigth.setTextLayout(objGeek!!.totalGeek!!)
    }

    fun clickButtons(componentDefault : CounterComponent){

        componentDefault.getBtnAdd().setOnClickListener{
            componentDefault.valueMoreLess(TypeMethodMath.SUM.toString())
        }

        componentDefault.getBtnDel().setOnClickListener{
            componentDefault.valueMoreLess(TypeMethodMath.SUB.toString())
        }

        componentDefault.getInputLayout().setOnClickListener{
            componentDefault.valueMoreLess(TypeMethodMath.SET.toString())
        }

    }

}