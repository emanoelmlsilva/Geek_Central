package com.example.geek_central.viewmodels

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.example.geek_central.R
import com.example.geek_central.component.MenuEditComponent
import com.example.geek_central.model.WorkGeek
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class BottomSheetLiveData(val context: Context? = null, var objGeek: WorkGeek? = null,var type: String = "min") : LiveData<Int>(),View.OnClickListener {

    private var bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context!!)

    private var myView : View

    private lateinit var menuEdit : MenuEditComponent

    private val listener = {v: View -> showDialog()}

    init{

        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

        bottomSheetDialog.setContentView(view)

        myView = view

        setBottomSheetScreenAll()

        statusBottomSheet()

        if(objGeek != null) initComponets()

    }

    private fun initComponets(){

        menuEdit = MenuEditComponent(myView, this.objGeek!!)
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

    companion object{
        private lateinit var instance: BottomSheetLiveData

        @MainThread
        fun get(context: Context? = null, objt: WorkGeek? = null): BottomSheetLiveData {
            instance = if(::instance.isInitialized) instance else BottomSheetLiveData(context, objt)
            return instance
        }
    }
}