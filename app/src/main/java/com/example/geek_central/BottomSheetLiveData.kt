package com.example.geek_central

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import com.example.geek_central.component.MenuEditComponent
import com.example.geek_central.model.BaseWorkGeek
import com.example.geek_central.viewmodels.WorkGeekViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class BottomSheetLiveData(
    val context: Context,
    objGeek: BaseWorkGeek,
    mWorkGeekViewModel: WorkGeekViewModel
) :
    LiveData<Int>(), View.OnClickListener {

    private var bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

    private lateinit var btnClose: Button

    private var myView: View

    private var menuEdit: MenuEditComponent

    init {

        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

        menuEdit = MenuEditComponent(
            view.findViewById(R.id.includeEditMenu),
            objGeek, mWorkGeekViewModel
        )

        bottomSheetDialog.setContentView(view)

        bottomSheetDialog.setCancelable(false)

        bottomSheetDialog.behavior.isHideable = true

        myView = view

        setBottomSheetScreenAll()

        statusBottomSheet()

        initView()

        setOnClicks()

    }

    private fun initView() {
        btnClose = myView.findViewById(R.id.btnCloseBotttom)
    }

    private fun setOnClicks() {
        btnClose.setOnClickListener {

            bottomSheetDialog.setCancelable(true)
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_HIDDEN
            bottomSheetDialog.setCancelable(false)
        }


    }

    private fun setBottomSheetScreenAll() {

        val bottomSheetBehavior = bottomSheetDialog.behavior

        val bottomSheetPeekHeight =
            context!!.resources.getDimensionPixelSize(R.dimen.dimension_behavior)

        bottomSheetBehavior.setPeekHeight(bottomSheetPeekHeight)

        val childLayoutParams: ViewGroup.LayoutParams = myView.layoutParams

        val displayMetrics: DisplayMetrics = DisplayMetrics()

        Resources.getSystem().displayMetrics

        (context as Activity).windowManager
            .defaultDisplay
            .getMetrics(displayMetrics)

        childLayoutParams.height = displayMetrics.heightPixels - 200

        myView.layoutParams = childLayoutParams
    }

    fun statusBottomSheet() {

        bottomSheetDialog.behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                menuEdit.setStatus(slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {}
        })
    }

    fun showDialog() {
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