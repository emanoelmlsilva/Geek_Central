package com.example.geek_central.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geek_central.R
import com.example.geek_central.R.color.iconHeartEnable
import com.example.geek_central.model.Manga
import com.example.geek_central.viewmodels.BottomSheetLiveData
import com.google.android.material.button.MaterialButton

class RecyclerMangaAdapter(private val mangas: ArrayList<Manga>, private val context: Context) :
    RecyclerView.Adapter<RecyclerMangaAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.geek_card_adapter, parent, false)

        BottomSheetLiveData.get(context)

        return MyViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val manga = mangas[position]

        holder.bindDate(manga)

        addValue(holder, manga)
    }

    private fun addValue(holder: MyViewHolder, myManga: Manga){

        holder.edit.setOnClickListener {

            BottomSheetLiveData.get().setObjetWorkGeek(myManga)
            BottomSheetLiveData.get().showDialog()
        }

        holder.favorite.setOnClickListener{
           // setIconFavorite(holder)
        }
    }

    private fun setIconFavorite(holder: MyViewHolder){
        var myColor : Int = R.color.colorAccent

        var myDrawable : Int

        if(holder.checkFavoriteIcon) {
            myDrawable = R.drawable.ic_favorite_black_24dp
            myColor = iconHeartEnable
        }else{
            myDrawable = R.drawable.ic_favorite_border_black_24dp
        }
        holder.favorite.setIconResource(myDrawable)
        holder.favorite.setIconTintResource(myColor)
        holder.checkFavoriteIcon = !holder.checkFavoriteIcon
    }

    override fun getItemCount(): Int {
        return mangas.size
    }

    class MyViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView){

        lateinit var title : TextView
        lateinit var author : TextView
        lateinit var favorite : MaterialButton
        lateinit var textMarkCurrent : TextView
        lateinit var textMarkTotal : TextView
        lateinit var edit : MaterialButton
        lateinit var delete : MaterialButton
        lateinit var note : TextView
        var checkFavoriteIcon: Boolean = false

        init {
            bindView()
        }

        fun bindDate(manga : Manga){
            title.text = manga.title
            textMarkCurrent.text = manga.currentGeek.toString()
            textMarkTotal.text = manga.totalGeek.toString()
            note.text = manga.note.toString()
            checkFavoriteIcon  = manga.favorite!!

        }

        @SuppressLint("ResourceType")
        private fun bindView(){

            title = itemView.findViewById(R.id.title)
            author = itemView.findViewById(R.id.author)
            favorite = itemView.findViewById(R.id.favorite)
            textMarkCurrent = itemView.findViewById(R.id.textMarkCurrent)
            textMarkTotal = itemView.findViewById(R.id.textMarkTotal)
            edit = itemView.findViewById(R.id.btnEdit)
            delete = itemView.findViewById(R.id.btnDelete)
            note = itemView.findViewById(R.id.note)
        }
    }
}