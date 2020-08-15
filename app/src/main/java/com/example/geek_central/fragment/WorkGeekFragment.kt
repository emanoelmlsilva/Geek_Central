package com.example.geek_central.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.example.geek_central.R
import com.example.geek_central.adapter.RecyclerWorkGeekAdapter
import com.example.geek_central.databinding.FragmentWorkgeekBinding
import com.example.geek_central.enums.TypeOrderBy
import com.example.geek_central.enums.TypePlataform
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.model.Author
import com.example.geek_central.model.Hosted
import com.example.geek_central.model.WorkGeek
import com.example.geek_central.model.Popular
import com.example.geek_central.observer.IObservable
import com.example.geek_central.order.OrderBy
import kotlinx.android.synthetic.main.fragment_workgeek.*


class WorkGeekFragment(mainFragment: MainFragment, private val typeWork: String) : Fragment() {

    var listManga : MutableList<WorkGeek> = ArrayList()
    var listAnime : MutableList<WorkGeek> = ArrayList()
    var listHq : MutableList<WorkGeek> = ArrayList()

    private var iOBservable : IObservable = mainFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewBinding = FragmentWorkgeekBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val textMessage = when(typeWork){
            TypeWork.MANGA.toString() -> resources.getString(R.string.nameManga)
            TypeWork.ANIME.toString() -> resources.getString(R.string.nameAnime)
            TypeWork.HQ.toString() -> resources.getString(R.string.nameHq)
            else -> resources.getString(R.string.nameManga)
        }

        txtMessageEmpty.text =  "${txtMessageEmpty.text} ${textMessage}"

        loadingList()

        listManga = OrderBy.get().ordeBy(TypeOrderBy.TITLE.toString(), listManga) as MutableList<WorkGeek>
        listAnime = OrderBy.get().ordeBy(TypeOrderBy.TOTAL.toString(), listAnime) as MutableList<WorkGeek>
        listHq = OrderBy.get().ordeBy(TypeOrderBy.GRADE.toString(), listHq) as MutableList<WorkGeek>

        val adapter = when(typeWork){
            TypeWork.MANGA.toString() -> RecyclerWorkGeekAdapter(listManga, this.requireContext())
            TypeWork.ANIME.toString() -> RecyclerWorkGeekAdapter(listAnime, this.requireContext())
            TypeWork.HQ.toString() -> RecyclerWorkGeekAdapter(listHq, this.requireContext())
            else -> RecyclerWorkGeekAdapter(listManga, this.requireContext())
        }


        recycler_work_geek.layoutManager = LinearLayoutManager(context)
        recycler_work_geek.adapter = adapter


        iOBservable.add(adapter, typeWork)

        adapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                if(adapter.itemCount == 0){
                    txtMessageEmpty.visibility =  View.VISIBLE
                }else {
                    txtMessageEmpty.visibility =  View.GONE
                }
            }
        })

    }

    fun loadingList(){

        if(listManga.size == 0) {
            listManga.add(
                WorkGeek(
                    title = "One Piece",
                    currentGeek = 824,
                    totalGeek = 977,
                    author = Author("Echiro Oda"),
                    popular = Popular(5.0, true),
                    hosted = Hosted(
                        "unionmangas.top/perfil-manga/one-piece",
                        TypePlataform.SITE.toString()
                    )
                )
            )

            listManga.add(
                WorkGeek(
                    title = "Kingdom",
                    currentGeek = 648,
                    totalGeek = 648,
                    author = Author("Yasuhisa Hara"),
                    popular = Popular(4.5, true),
                    hosted = Hosted(
                        "unionmangas.top/perfil-manga/kingdom",
                        TypePlataform.SITE.toString()
                    )
                )
            )

            listManga.add(
                WorkGeek(
                    title = "Boku No Hero Academia",
                    currentGeek = 702,
                    totalGeek = 972,
                    author = Author("Horikoshi Kouhei"),
                    popular = Popular(4.0, false),
                    hosted = Hosted(
                        "unionleitor.top/perfil-manga/boku-no-hero-academia-pt-br",
                        TypePlataform.SITE.toString()
                    )
                )
            )

            listManga.add(
                WorkGeek(
                    title = "One Punch-Man",
                    currentGeek = 131,
                    totalGeek = 132,
                    author = Author("ONE"),
                    popular = Popular(3.5, false),
                    hosted = Hosted(
                        "unionleitor.top/perfil-manga/one-punch-man",
                        TypePlataform.SITE.toString()
                    )
                )
            )
        }

        if(listAnime.size == 0) {
            listAnime.add(
                WorkGeek(
                    title = "One Piece",
                    season = 1,
                    currentGeek = 824,
                    totalGeek = 977,
                    author = Author("Echiro Oda"),
                    popular = Popular(5.0, true),
                    hosted = Hosted(
                        "unionmangas.top/perfil-manga/one-piece",
                        TypePlataform.SITE.toString()
                    )
                )
            )

            listAnime.add(
                WorkGeek(
                    title = "Kingdom",
                    season = 1,
                    currentGeek = 648,
                    totalGeek = 648,
                    author = Author("Yasuhisa Hara"),
                    popular = Popular(4.5, true),
                    hosted = Hosted(
                        "unionmangas.top/perfil-manga/kingdom",
                        TypePlataform.SITE.toString()
                    )
                )
            )

            listAnime.add(
                WorkGeek(
                    title = "Boku No Hero Academia",
                    season = 1,
                    currentGeek = 702,
                    totalGeek = 972,
                    author = Author("Horikoshi Kouhei"),
                    popular = Popular(4.0, false),
                    hosted = Hosted(
                        "unionleitor.top/perfil-manga/boku-no-hero-academia-pt-br",
                        TypePlataform.SITE.toString()
                    )
                )
            )
        }

        if(listHq.size == 0) {
            listHq.add(
                WorkGeek(
                    title = "One Piece",
                    currentGeek = 824,
                    totalGeek = 977,
                    author = Author("Echiro Oda"),
                    popular = Popular(5.0, true),
                    hosted = Hosted(
                        "unionmangas.top/perfil-manga/one-piece",
                        TypePlataform.SITE.toString()
                    )
                )
            )
        }

    }
}
