package com.example.geek_central.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.example.geek_central.adapter.RecyclerWorkGeekAdapter
import com.example.geek_central.databinding.FragmentMangaBinding
import com.example.geek_central.enums.TypeOrderBy
import com.example.geek_central.enums.TypePlataform
import com.example.geek_central.model.Author
import com.example.geek_central.model.Hosted
import com.example.geek_central.model.WorkGeek
import com.example.geek_central.model.Popular
import com.example.geek_central.observer.IObservable
import com.example.geek_central.order.OrderBy
import kotlinx.android.synthetic.main.fragment_manga.*


class MangaFragment(mainFragment: MainFragment) : Fragment() {

    var list : MutableList<WorkGeek> = ArrayList()
    private var iOBservable : IObservable = mainFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewBinding = FragmentMangaBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        txtMessageEmpty.text =  "${txtMessageEmpty.text} Mangás"

        if(list.size == 0) {
            list.add(
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

            list.add(
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

            list.add(
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

            list.add(
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

        list = OrderBy.get().ordeBy(TypeOrderBy.TITLE.toString(), list) as MutableList<WorkGeek>

        val adapterManga =  RecyclerWorkGeekAdapter(list, this.requireContext())

        recycler_manga.layoutManager = LinearLayoutManager(context)
        recycler_manga.adapter = adapterManga


        iOBservable.add(adapterManga, "manga")

        adapterManga.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                if(adapterManga.itemCount == 0){
                    txtMessageEmpty.visibility =  View.VISIBLE
                }else {
                    txtMessageEmpty.visibility =  View.GONE
                }
            }
        })

    }

}
