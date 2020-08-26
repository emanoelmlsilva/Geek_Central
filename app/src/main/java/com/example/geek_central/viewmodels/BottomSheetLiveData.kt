package com.example.geek_central.viewmodels

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.example.geek_central.R
import com.example.geek_central.component.MenuEditComponent
import com.example.geek_central.model.WorkGeek
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class BottomSheetLiveData(val context: Context? = null, var objGeek: WorkGeek? = null,var type: String = "min") : LiveData<Int>(),View.OnClickListener {

    private var bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context!!)

    private lateinit var btnClose : Button

    private var myView : View

    private lateinit var menuEdit : MenuEditComponent

    private val listener = {v: View -> showDialog()}

    init{

        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

        bottomSheetDialog.setContentView(view)

        bottomSheetDialog.setCancelable(false)

        bottomSheetDialog.behavior.isHideable = true

        myView = view

        setBottomSheetScreenAll()

        statusBottomSheet()

        if(objGeek != null) initComponets()

        initView()

        setOnClicks()

    }

    private fun initView(){
        btnClose = myView.findViewById(R.id.btnCloseBotttom)
    }

    private fun setOnClicks() {
        btnClose.setOnClickListener {

            bottomSheetDialog.setCancelable(true)
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_HIDDEN
            bottomSheetDialog.setCancelable(false)
        }


    }


    private fun initComponets(){

        menuEdit = MenuEditComponent(myView.findViewById(R.id.includeEditMenu), this.objGeek!!)
    }

    private fun setBottomSheetScreenAll() {

        val bottomSheetBehavior  = bottomSheetDialog.behavior

        val bottomSheetPeekHeight = context!!.resources.getDimensionPixelSize(R.dimen.dimension_behavior)

        bottomSheetBehavior.setPeekHeight(bottomSheetPeekHeight)

        val childLayoutParams : ViewGroup.LayoutParams = myView.layoutParams

        val displayMetrics : DisplayMetrics = DisplayMetrics()

        Resources.getSystem().displayMetrics

        (context as Activity).windowManager
            .defaultDisplay
            .getMetrics(displayMetrics)

        childLayoutParams.height = displayMetrics.heightPixels - 200

        myView.layoutParams = childLayoutParams
    }

    fun statusBottomSheet(){

        bottomSheetDialog.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                menuEdit.setStatus(slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                val text = when(newState){
                    BottomSheetBehavior.STATE_EXPANDED -> "STATE_EXPANDED"
                    BottomSheetBehavior.STATE_COLLAPSED -> "STATE_COLLAPSED"
                    BottomSheetBehavior.STATE_DRAGGING -> "STATE_DRAGGING"
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> "STATE_HALF_EXPANDED"
                    BottomSheetBehavior.STATE_HIDDEN -> "STATE_HIDDEN"
                    BottomSheetBehavior.STATE_SETTLING -> "STATE_SETTLING"
                    else -> null
                }
                println("printando ===================== ${text}")
            }
        })
    }

    fun setObjetWorkGeek(objt: WorkGeek){
        objGeek = objt
        initComponets()
    }

    fun showDialog(){
        bottomSheetDialog.show()
    }

    override fun onActive() {

    }

    override fun onInactive() {

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}