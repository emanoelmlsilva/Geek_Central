package com.example.geek_central.component

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.geek_central.R
import com.example.geek_central.model.WorkGeek

class MenuEditMinComponent(val view : View, val objGeek: WorkGeek) : View.OnClickListener {

    private lateinit var includeEditMinComponet : ConstraintLayout

    private lateinit var componentCounteLeft : CounterComponent

    private lateinit var componentCounteRigth : CounterComponent

    init{
        initView()
    }

    private fun initView(){

        includeEditMinComponet = view.findViewById(R.id.constraintEditMenuMin)

        componentCounteLeft = CounterComponent(view.findViewById(R.id.edit_Left))
        componentCounteLeft.setHint("Cap. Atual")

        componentCounteRigth = CounterComponent(view.findViewById(R.id.edit_Rigth))
        componentCounteRigth.setHint("Cap. Total")

        loadingObject()

        clickButtons()
    }

    fun setVisible(visible : Int){
        includeEditMinComponet.visibility = visible
    }

    fun loadingObject(){

        componentCounteLeft.setTextLayout(objGeek!!.currentGeek!!)
        componentCounteRigth.setTextLayout(objGeek!!.totalGeek!!)
    }

    fun clickButtons(){
        componentCounteLeft.getBtnAdd().setOnClickListener(this)
        componentCounteLeft.getBtnDel().setOnClickListener(this)
        componentCounteLeft.getInputLayout().setOnClickListener(this)

        componentCounteRigth.getBtnAdd().setOnClickListener(this)
        componentCounteRigth.getBtnDel().setOnClickListener(this)
        componentCounteRigth.getInputLayout().setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            componentCounteLeft.getBtnAdd().id -> {
                componentCounteLeft.valueMoreLess("sum")
            }
            componentCounteLeft.getBtnDel().id -> {
                componentCounteLeft.valueMoreLess("sub")
            }
            componentCounteLeft.getInputLayout().id -> {
                componentCounteLeft.valueMoreLess("set", componentCounteLeft.getValueInput())
            }
            componentCounteRigth.getBtnAdd().id -> {
                componentCounteRigth.valueMoreLess("sum")
            }
            componentCounteRigth.getBtnDel().id -> {
                componentCounteRigth.valueMoreLess("sub")
            }
            componentCounteRigth.getInputLayout().id -> {
                componentCounteRigth.valueMoreLess("set", componentCounteRigth.getValueInput())
            }
        }
    }

}