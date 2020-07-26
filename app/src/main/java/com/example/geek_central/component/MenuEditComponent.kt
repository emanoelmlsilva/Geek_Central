package com.example.geek_central.component

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        title.visibility = if(status < 0.0006F) View.VISIBLE else View.GONE

        //set visibility components
        setVisibilite(note.getCard())

        setVisibilite(categories.getCard())

        setVisibilite(inputAuthor)

        setVisibilite(inputSite)

        setVisibilite(inputName)

        //set slide animation component

        slideUp(title)

        if(status == 0.0f) slideUp(inputName, toX = view.width.toFloat()) else slideUp(inputName)

        slideUp(note.getCard())

        slideUp(categories.getCard())

        slideUp(inputAuthor)

        slideUp(inputSite)

        slideUp(btnSave)


    }


    private fun setVisibilite(myView : View){

        if(status == 0.0F) {
            myView.visibility = View.GONE
        }else {
            myView.visibility = View.VISIBLE
        }
    }

    fun setStatus(statusUpdate : Float) {
        status = statusUpdate
        visibiliteEditMinExtend()
    }

    fun slideUp(myView: View, fromX : Float = myView.width.toFloat() - (status * 1000), toX : Float =  0F , fromY : Float = 0F, toY : Float = 0F) {

        val animate = TranslateAnimation(
            fromX,  // fromXDelta
            toX,  // toXDelta
            fromY,  // fromYDelta
            toY
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true

        myView.startAnimation(animate)
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

        objGeek?.title?.let { title.text = it}
        objGeek?.title?.let { inputName.editText?.setText(it) }
        objGeek?.currentGeek?.let { componentCounteLeft.setTextLayout(it) }
        objGeek?.totalGeek?.let { componentCounteRigth.setTextLayout(it) }
        objGeek?.popular?.note?.let { note.setValueNote(it.toInt())}
        objGeek?.author?.name?.let { inputAuthor.editText?.setText(it) }
        objGeek?.hosted?.site?.let { inputSite.editText?.setText(it)}

        setIconFavorite()
    }

    private fun setIconFavorite(){
        if(objGeek?.popular?.favorite!!) inputName.setStartIconTintList(ContextCompat.getColorStateList(view.context,R.color.iconHeartEnable))
    }

}