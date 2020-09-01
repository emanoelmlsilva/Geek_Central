package com.example.geek_central.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geek_central.BottomSheetLiveData
import com.example.geek_central.R
import com.example.geek_central.model.WorkGeekAnimeWithPopularAndHosted
import com.example.geek_central.model.WorkGeekMangaWithPopularAndHosted
import com.example.geek_central.observer.IObserver
import com.example.geek_central.viewmodels.WorkGeekViewModel
import com.google.android.material.button.MaterialButton

class RecyclerWorkGeekAdapter(val mWorkGeekViewModel: WorkGeekViewModel) :
    RecyclerView.Adapter<RecyclerWorkGeekAdapter.BaseViewHolder<*>>(),
    IObserver {

    private var data: MutableList<Comparable<*>> = ArrayList()

    private val copyListData = ArrayList<Comparable<*>>()

    private lateinit var context: Context

    private lateinit var bottomSheetLiveData: BottomSheetLiveData

    companion object {
        private val TYPE_MANGA = 0
        private val TYPE_ANIME = 1
        private val TYPE_HQ = 2
    }

    fun setData(newData: List<Comparable<*>>) {
        data = newData as MutableList<Comparable<*>>
        notifyDataSetChanged()
    }

    private fun addValue(holder: BaseViewHolder<*>, itemData: Comparable<*>) {

        holder.edit.setOnClickListener {
            bottomSheetLiveData = BottomSheetLiveData(context, itemData, mWorkGeekViewModel)
            bottomSheetLiveData.showDialog()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {

        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.geek_card_adapter, parent, false)

        return when (viewType) {
            TYPE_MANGA -> MangaViewHolder(view)
            TYPE_ANIME -> AnimeViewHolder(view)
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val itemData = data.get(position)

        when (holder) {
            is MangaViewHolder -> {
                addValue(holder, itemData)
                holder.bindDate(itemData as WorkGeekMangaWithPopularAndHosted)
            }
            is AnimeViewHolder -> {
                addValue(holder, itemData)
                holder.bindDate(itemData as WorkGeekAnimeWithPopularAndHosted)
            }
            else -> throw IllegalArgumentException()
        }

    }

    override fun getItemViewType(position: Int): Int {
        val comparable = data[position]

        return when (comparable) {
            is WorkGeekMangaWithPopularAndHosted -> TYPE_MANGA
            is WorkGeekAnimeWithPopularAndHosted -> TYPE_ANIME
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun update(filter: String, typeOrder: Boolean) {
        println("trestando")
    }

//
//
//    override fun update(filter: String, typeOrder: Boolean) {
//
//        if (typeOrder) {
//
//            //workGeeks = OrderBy.get().ordeBy(filter, workGeeks) as MutableList<WorkGeek>
//
//        } else {
//
//            //val newlist: List<WorkGeek> = FilterSearch(copyListWorkGeek, filter).searchText()
//
//            //workGeeks = if (filter.isNullOrBlank()) copyListWorkGeek else newlist as MutableList<WorkGeek>
//
//        }
//
//        notifyDataSetChanged()
//    }


    //viewholder padrão para a criação dos tipos manga, anime e hq
    abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

        abstract fun bindDate(item: T)

        fun bindView() {

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

        fun setVisibilitySeason(txtSeason: Int?) {

            var visibilited = 0

            if (txtSeason == null) {
                visibilited = View.GONE
            } else {
                visibilited = View.VISIBLE
                season.text = txtSeason.toString()
            }

            linearSeason.visibility = visibilited
        }

        fun setIconLoadingFavorite(checkIcon: Boolean) {

            var myColor: Int = R.color.colorAccent

            var myDrawable: Int

            if (checkIcon) {
                myDrawable = R.drawable.ic_favorite_black_24dp
                myColor = R.color.iconHeartEnable
            } else {
                myDrawable = R.drawable.ic_favorite_border_black_24dp
            }

            favorite.setIconResource(myDrawable)
            favorite.setIconTintResource(myColor)
        }


    }

    class MangaViewHolder(val view: View) :
        BaseViewHolder<WorkGeekMangaWithPopularAndHosted>(view) {

        init {
            this.bindView()
        }

        override fun bindDate(item: WorkGeekMangaWithPopularAndHosted) {

            this.title.text = item.workGeek.title
            this.author.text = "nome do autor"
            textMarkCurrent.text = item.workGeek.currentGeek.toString()
            textMarkTotal.text = item.workGeek.totalGeek.toString()
            note.text = item.popular.grade.toString()

            setIconLoadingFavorite(item.popular.favorite)
        }

    }

    class AnimeViewHolder(val view: View) :
        BaseViewHolder<WorkGeekAnimeWithPopularAndHosted>(view) {

        init {
            this.bindView()
        }

        override fun bindDate(item: WorkGeekAnimeWithPopularAndHosted) {

            this.title.text = item.workGeek.title
            this.author.text = "nome do autor"
            textMarkCurrent.text = item.workGeek.currentGeek.toString()
            textMarkTotal.text = item.workGeek.totalGeek.toString()
            note.text = item.popular.grade.toString()

            setVisibilitySeason(item.workGeek.season)

            setIconLoadingFavorite(item.popular.favorite)
        }
    }

}