package com.example.geek_central.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.example.geek_central.R
import com.example.geek_central.component.CounterComponent
import com.example.geek_central.model.WorkGeek
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

class BottomSheetLiveData(val context: Context, val objGeek: WorkGeek) : LiveData<Int>(),View.OnClickListener {

    private var bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

    private lateinit var componentCounteLeft : CounterComponent

    private lateinit var componentCounteRigth : CounterComponent

    private lateinit var title: TextView

    private val listener = {v: View -> showDialog()}

    init{

        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

        bottomSheetDialog.setContentView(view)


        initView(view)

    }

    private fun initView(view : View){
        title = view.findViewById(R.id.txtTitle)

        componentCounteLeft = CounterComponent(view.findViewById(R.id.edit_Left))
        componentCounteLeft.setHint("Cap. Atual")

        componentCounteRigth = CounterComponent(view.findViewById(R.id.edit_Rigth))
        componentCounteRigth.setHint("Cap. Total")
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
        fun get(context: Context, obj: WorkGeek): BottomSheetLiveData {
            instance = if(::instance.isInitialized) instance else BottomSheetLiveData(context, obj)
            return instance
        }
    }
}