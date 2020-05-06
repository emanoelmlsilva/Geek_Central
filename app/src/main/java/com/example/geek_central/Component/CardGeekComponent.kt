package com.example.geek_central.Component

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.geek_central.R
import com.google.android.material.button.MaterialButton

class CardGeekComponent(view : View) {

    private lateinit var image : ImageView
    private lateinit var title : TextView
    private lateinit var author : TextView
    private lateinit var favorite : MaterialButton
    private lateinit var markCurrent : MaterialButton
    private lateinit var textMarkCurrent : TextView
    private lateinit var markTotal : MaterialButton
    private lateinit var textMarkTotal : TextView
    private lateinit var edit : MaterialButton
    private lateinit var delete : MaterialButton
    private lateinit var note : TextView

    init{
        setType(view)
    }

    private fun setType(view: View){
        image = view.findViewById(R.id.image)
        title = view.findViewById(R.id.title)
        author = view.findViewById(R.id.author)
        favorite = view.findViewById(R.id.favorite)
        markCurrent = view.findViewById(R.id.btnMarkCurrent)
        textMarkCurrent = view.findViewById(R.id.textMarkCurrent)
        markTotal = view.findViewById(R.id.btnMarkTotal)
        textMarkTotal = view.findViewById(R.id.textMarkTotal)
        edit = view.findViewById(R.id.btnEdit)
        delete = view.findViewById(R.id.btnDelete)
        note = view.findViewById(R.id.note)
    }

    public fun getImage() : ImageView {
        return image
    }

    public fun setImage(id : Int, resources : Resources){
        val src = BitmapFactory.decodeResource(resources, id)
        val dr = RoundedBitmapDrawableFactory.create(resources, src)
        dr.cornerRadius = Math.max(src.width, src.height) / 2.0f
        image.setImageDrawable(dr)
    }

    public fun getTextTitle() : TextView {return title}

    public fun setTextTitle(newtitle : String){
        title.setText(newtitle)
    }

    public fun getTextAuthor() : TextView { return author}

    public fun setTextAuthor(newAuthor : String){
        author.setText(newAuthor)
    }

    public fun getButtonFavorite() : MaterialButton {
        return favorite
    }

    public fun setIconFavorite(id : Int){
        favorite.setIconResource(id)
    }

    public fun getButtonMarkCurrent(): MaterialButton {return markCurrent}

    public fun setTextMarkCurrent(text : String){
        textMarkCurrent.setText(text)
    }

    public fun getButtonMarkTotal() : MaterialButton{
        return markTotal
    }

    public fun setTextMarkTotal(text : String){
        textMarkTotal.setText(text)
    }

    public fun getButtonEdit() : MaterialButton {
        return edit
    }

    public fun getButtonDelete() : MaterialButton {
        return delete
    }

    public fun setNote(newNote : String){
        note.setText(newNote)
    }

}