package com.example.geek_central.component

import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.geek_central.R
import com.google.android.material.chip.ChipGroup

class CategoriesComponent(val view : View) {

    private val groupCategories : ChipGroup = view.findViewById(R.id.groupCategories)

    private var linearLayout : LinearLayout = view.findViewById(R.id.categoriesComponent)

    fun getItemGroupCategories() : ChipGroup = groupCategories

    fun getCard() : LinearLayout = linearLayout
}