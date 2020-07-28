package com.example.geek_central.component

import android.view.View
import android.widget.SearchView
import com.example.geek_central.R
import com.google.android.material.button.MaterialButton

class SearchViewComponent(private val view : View){

    private lateinit var searchComponent : SearchView
    private lateinit var btnMenu : MaterialButton
    private var submit = false;

    init {
        setType(view)
    }

    private fun setType(view : View){
        searchComponent = view.findViewById(R.id.search_view)
        btnMenu = view.findViewById(R.id.btn_pop_menu)
    }

    fun setSubmit(submit : Boolean){
        this.submit = submit;
    }

    fun setHint(hint : String){
        searchComponent.setQuery(hint, submit)
    }

    fun getPopMenu() : MaterialButton {
        return this.btnMenu
    }

    fun getSearch() : SearchView = searchComponent
}