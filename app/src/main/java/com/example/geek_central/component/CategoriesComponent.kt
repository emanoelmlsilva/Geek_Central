package com.example.geek_central.component

import android.view.View
import com.example.geek_central.R
import com.google.android.material.chip.ChipGroup

class CategoriesComponent(val view : View) {

    private val groupCategories : ChipGroup = view.findViewById(R.id.groupCategories)

    fun getItemGroupCategories() : ChipGroup = groupCategories
}