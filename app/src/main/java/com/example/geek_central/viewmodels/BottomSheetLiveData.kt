package com.example.geek_central.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.example.geek_central.R
import com.example.geek_central.component.CounterComponent
import com.example.geek_central.component.MenuEditMinComponent
import com.example.geek_central.model.WorkGeek
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetLiveData(val context: Context? = null, var objGeek: WorkGeek? = null,var type: String = "min") : LiveData<Int>(),View.OnClickListener {

    private var bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context!!)

    private var myView : View

    private val listener = {v: View -> showDialog()}

    init{

        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

        bottomSheetDialog.setContentView(view)

        myView = view

        setTypeMenu(type)

    }

    fun setTypeMenu(myType: String){
        if(myType.equals("min")){
            MenuEditMinComponent(myView, this!!.objGeek!!)
            type = myType
        }else{
            //MenuEditAllComponent()
        }
    }

    fun setObjetWorkGeek(objt: WorkGeek){
        objGeek = objt
    }

    fun showDialog(){
        bottomSheetDialog.show()
    }

    override fun onActive() {
        //btnEdit.setOnClickListener(listener)
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