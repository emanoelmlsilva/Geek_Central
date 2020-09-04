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
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.model.BaseWorkGeek
import com.example.geek_central.observer.IObserver
import com.example.geek_central.utils.ConvertToBaseWorkGeek
import com.example.geek_central.viewmodels.WorkGeekViewModel
import com.google.android.material.button.MaterialButton

class RecyclerWorkGeekAdapter(
    private var baseWorkGeeks: MutableList<BaseWorkGeek>,
    val mWorkGeekViewModel: WorkGeekViewModel,
    var typeAdapter: String
) :
    RecyclerView.Adapter<RecyclerWorkGeekAdapter.BaseViewHolder>(),
    IObserver {

    private val copyListData = ArrayList<Comparable<*>>()

    private lateinit var context: Context

    private lateinit var bottomSheetLiveData: BottomSheetLiveData

    fun setData(newData: List<BaseWorkGeek>, type: String) {

        baseWorkGeeks = newData as MutableList<BaseWorkGeek>

        notifyDataSetChanged()
    }

    private fun addValue(holder: BaseViewHolder, baseWorkGeek: BaseWorkGeek) {

        holder.edit.setOnClickListener {
            bottomSheetLiveData = BottomSheetLiveData(context, baseWorkGeek, mWorkGeekViewModel)
            bottomSheetLiveData.showDialog()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {

        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.geek_card_adapter, parent, false)

        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemData = baseWorkGeeks.get(position)

        addValue(holder, itemData)
        holder.bindDate(itemData, typeAdapter)

    }


    override fun getItemCount(): Int {
        return baseWorkGeeks.size
    }

    override fun update(filter: String?, typeOrder: String, typeWorkGeek: String) {

        typeAdapter = typeWorkGeek
        if (filter.isNullOrBlank() && !typeOrder.isNullOrBlank()) {

            if (typeWorkGeek.equals(TypeWork.ANIME.toString())) {

                mWorkGeekViewModel.getAllWorkGeeksAnimes(typeOrder)
                    .observeForever { workgeeks ->
                        baseWorkGeeks =
                            ConvertToBaseWorkGeek.get().workGeekAnimeFromBaseWorkGeek(workgeeks)
                        notifyDataSetChanged()
                    }


            } else {

                mWorkGeekViewModel.getAllWorkGeeksMangas(typeOrder)
                    .observeForever { workgeeks ->

                        baseWorkGeeks =
                            ConvertToBaseWorkGeek.get().workGeekMnagaFromBaseWorkGeek(workgeeks)
                        notifyDataSetChanged()
                    }

            }
        } else {
            println("entrou para filtar")
        }
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
    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

        fun bindDate(baseWorkGeek: BaseWorkGeek, type: String) {

            this.title.text = baseWorkGeek.title
            this.author.text = "nome do autor"
            textMarkCurrent.text = baseWorkGeek.currentGeek.toString()
            textMarkTotal.text = baseWorkGeek.totalGeek.toString()
            note.text = baseWorkGeek.popular.grade.toString()

            if (type.equals(TypeWork.ANIME.toString())) setVisibilitySeason(baseWorkGeek.season)

            setIconLoadingFavorite(baseWorkGeek.popular.favorite)

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

}