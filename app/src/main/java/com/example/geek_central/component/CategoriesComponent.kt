package com.example.geek_central.component

import android.view.View
import android.widget.LinearLayout
import com.example.geek_central.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class CategoriesComponent(val view: View) {

    private val groupCategories: ChipGroup = view.findViewById(R.id.groupCategories)

    private var linearLayout: LinearLayout = view.findViewById(R.id.categoriesComponent)

    private val listNames: MutableList<String> = mutableListOf()

    fun getItemGroupCategories(): ChipGroup = groupCategories

    fun getClickCips() {
        for (index in 0 until groupCategories.childCount) {
            val chipCurrent = groupCategories.getChildAt(index) as Chip
            chipCurrent.setOnCheckedChangeListener { view, isChecked ->
                if (isChecked) {
                    listNames.add(view.text.toString())
                } else {
                    listNames.remove(view.text.toString())
                }
            }

        }
    }

    fun setListCategories(list: List<String>){
        for(index in list.indices){
            val chipCurrent = groupCategories.getChildAt(index) as Chip
            chipCurrent.isChecked = true
        }
    }

    fun getCard(): LinearLayout = linearLayout

    fun getListItem() = listNames
}