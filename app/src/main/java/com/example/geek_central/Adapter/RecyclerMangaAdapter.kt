package com.example.geek_central.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.geek_central.Model.Manga
import com.example.geek_central.R
import com.google.android.material.button.MaterialButton

class RecyclerMangaAdapter(private val mangas: ArrayList<Manga>, private val context: Context) :
    RecyclerView.Adapter<RecyclerMangaAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.geek_card_adapter, parent, false)
        return MyViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val manga = mangas[position]
        holder.bindDate(manga)
        holder.bindClick()
    }

    override fun getItemCount(): Int {
        return mangas.size
    }

    class MyViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        lateinit var title : TextView
        lateinit var author : TextView
        lateinit var favorite : MaterialButton
        lateinit var textMarkCurrent : TextView
        lateinit var textMarkTotal : TextView
        lateinit var edit : MaterialButton
        lateinit var delete : MaterialButton
        lateinit var note : TextView

        init {
            bindView()
        }

        fun bindDate(manga : Manga){
            title.text = manga.title
            textMarkCurrent.text = manga.currentGeek.toString()
            textMarkTotal.text = manga.totalGeek.toString()
            note.text = manga.note.toString()
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

        fun bindClick(){
            favorite.setOnClickListener(this)
            edit.setOnClickListener(this)
            delete.setOnClickListener(this)
            title.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v!!.id){
                R.id.favorite -> {
                    Toast.makeText(context, "favorite", Toast.LENGTH_SHORT).show();
                }

                R.id.btnEdit -> {
                    Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                }

                R.id.btnDelete -> {
                    Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                }

                R.id.title -> {
                    Toast.makeText(context, "title", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}