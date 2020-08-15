package com.example.geek_central.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geek_central.R
import com.example.geek_central.R.color.iconHeartEnable
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.model.WorkGeek
import com.example.geek_central.observer.IObserver
import com.example.geek_central.order.OrderBy
import com.example.geek_central.utils.FilterSearch
import com.example.geek_central.viewmodels.BottomSheetLiveData
import com.google.android.material.button.MaterialButton

class RecyclerWorkGeekAdapter(private var workGeeks: MutableList<WorkGeek>, private val context: Context) :
    RecyclerView.Adapter<RecyclerWorkGeekAdapter.MyViewHolder>(), IObserver {

    private val copyListWorkGeek = workGeeks

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.geek_card_adapter, parent, false)

        BottomSheetLiveData.get(context)

        return MyViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val workGeek = workGeeks[position]

        holder.bindDate(workGeek)

        addValue(holder, workGeek)
    }

    private fun addValue(holder: MyViewHolder, myWorkGeek: WorkGeek) {

        holder.edit.setOnClickListener {

            BottomSheetLiveData.get().setObjetWorkGeek(myWorkGeek)
            BottomSheetLiveData.get().showDialog()
        }

    }

    override fun getItemCount(): Int {
        return workGeeks.size
    }

    override fun update(filter: String,  typeOrder : Boolean) {

        if(typeOrder){

            workGeeks = OrderBy.get().ordeBy(filter, workGeeks) as MutableList<WorkGeek>

        }else {

            val newlist : List<WorkGeek> = FilterSearch(copyListWorkGeek , filter).searchText()

            workGeeks = if (filter.isNullOrBlank()) copyListWorkGeek else newlist as MutableList<WorkGeek>

        }

        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        lateinit var title: TextView
        lateinit var season: TextView
        lateinit var author: TextView
        lateinit var favorite: MaterialButton
        lateinit var textMarkCurrent: TextView
        lateinit var textMarkTotal: TextView
        lateinit var edit: MaterialButton
        lateinit var delete: MaterialButton
        lateinit var note: TextView
        lateinit var linearSeason: LinearLayout

        init {
            bindView()
        }

        fun bindDate(workGeek: WorkGeek) {
            title.text = workGeek.title
            textMarkCurrent.text = workGeek.currentGeek.toString()
            textMarkTotal.text = workGeek.totalGeek.toString()
            note.text = workGeek.popular?.grade.toString()
            setVisibilitySeason(workGeek.season)
            setIconLoadingFavorite(workGeek.popular?.favorite!!)

        }

        private fun bindView() {

            title = itemView.findViewById(R.id.title)
            season = itemView.findViewById(R.id.txtSeason)
            author = itemView.findViewById(R.id.author)
            favorite = itemView.findViewById(R.id.favorite)
            textMarkCurrent = itemView.findViewById(R.id.textMarkCurrent)
            textMarkTotal = itemView.findViewById(R.id.textMarkTotal)
            edit = itemView.findViewById(R.id.btnEdit)
            delete = itemView.findViewById(R.id.btnDelete)
            note = itemView.findViewById(R.id.note)
            linearSeason = itemView.findViewById(R.id.linearLayoutSeason)
        }

        private fun setVisibilitySeason(txtSeason: Int?){

            var visibilited = 0

            if(txtSeason == null){
                visibilited = View.GONE
            }else{
                visibilited = View.VISIBLE
                season.text = txtSeason.toString()
            }

            linearSeason.visibility = visibilited
        }

        private fun setIconLoadingFavorite(checkIcon: Boolean) {

            var myColor: Int = R.color.colorAccent

            var myDrawable: Int

            if (checkIcon) {
                myDrawable = R.drawable.ic_favorite_black_24dp
                myColor = iconHeartEnable
            } else {
                myDrawable = R.drawable.ic_favorite_border_black_24dp
            }

            favorite.setIconResource(myDrawable)
            favorite.setIconTintResource(myColor)
        }

    }

}