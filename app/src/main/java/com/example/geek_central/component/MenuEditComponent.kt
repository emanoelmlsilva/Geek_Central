package com.example.geek_central.component

import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.TextView
import com.example.geek_central.R
import com.example.geek_central.enums.TypeMethodMath
import com.example.geek_central.model.WorkGeek
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout


class MenuEditComponent(val view : View, val objGeek: WorkGeek? = null) {

    lateinit var title: TextView

    private lateinit var componentCounteLeft : CounterComponent

    private lateinit var componentCounteRigth : CounterComponent

    private lateinit var inputName : TextInputLayout

    private lateinit var note : NoteComponent

    private lateinit var categories : CategoriesComponent

    private lateinit var inputAuthor : TextInputLayout

    private lateinit var inputSite : TextInputLayout

    private lateinit var btnSave : MaterialButton

    private var status : Float = 0.0f


    init {

        initView()

        clickButtons(componentCounteLeft)
        clickButtons(componentCounteRigth)

        loadingObject()

        visibiliteEditMinExtend()
    }

    private fun initView(){

        title = view.findViewById(R.id.txtTitle)

        title.text = objGeek?.title ?: ""

        componentCounteLeft = CounterComponent(view.findViewById(R.id.edit_Left))
        componentCounteLeft.setHint("Cap. Atual")

        componentCounteRigth = CounterComponent(view.findViewById(R.id.edit_Rigth))
        componentCounteRigth.setHint("Cap. Total")

        note = NoteComponent(view.findViewById(R.id.noteComponent))

        categories = CategoriesComponent(view.findViewById(R.id.categoriesComponent))

        inputName = view.findViewById(R.id.inputLayoutName)

        inputAuthor = view.findViewById(R.id.inputLayoutAuthor)

        inputSite = view.findViewById(R.id.inputLayoutSite)

        btnSave = view.findViewById(R.id.btnSave)

    }

    private fun visibiliteEditMinExtend(){

        //transation slide up/down view title
        title.visibility = if(status < 0.03f) View.VISIBLE else View.GONE
        if(status == 0.0f) slideUp(title)

        setVisibilite(inputName)

        setVisibilite(note.getCard())

        setVisibilite(categories.getCard())

        setVisibilite(inputAuthor)

        setVisibilite(inputSite)

        slideUpButton()
    }

    private fun setVisibilite(view : View){

        var visibilite : Int

        if(status < 0.004f) {
            visibilite = View.GONE
        }else {
            visibilite = View.VISIBLE
        }

        view.visibility = visibilite

        if(status > 0.0f) slideUp(view)
    }

    fun slideUpButton(){

        val animate = TranslateAnimation(
            btnSave.right.toFloat(),  // fromXDelta
            0F,  // toXDelta
            0F,  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true

        btnSave.startAnimation(animate)
    }

    fun setStatus(statusUpdate : Float) {
        status = statusUpdate
        visibiliteEditMinExtend()
    }

    fun slideUp(view: View) {
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat() - (status * 220),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true

        view.startAnimation(animate)
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

    fun loadingObject(){

        objGeek?.currentGeek?.let { componentCounteLeft.setTextLayout(it) }
        objGeek?.totalGeek?.let { componentCounteRigth.setTextLayout(it) }
    }

}