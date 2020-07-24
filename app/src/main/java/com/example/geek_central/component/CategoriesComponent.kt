package com.example.geek_central.component

import android.view.View
import androidx.cardview.widget.CardView
import com.example.geek_central.R
import com.google.android.material.chip.ChipGroup

class CategoriesComponent(val view : View) {

    private val groupCategories : ChipGroup = view.findViewById(R.id.groupCategories)

    private var cardView : CardView = view.findViewById(R.id.categoriesComponent)

    fun getItemGroupCategories() : ChipGroup = groupCategories

    fun getCard() : CardView = cardView
}