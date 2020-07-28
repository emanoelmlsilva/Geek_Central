package com.example.geek_central.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geek_central.adapter.RecyclerMangaAdapter
import com.example.geek_central.databinding.FragmentMangaBinding
import com.example.geek_central.enums.TypePlataform
import com.example.geek_central.model.Author
import com.example.geek_central.model.Hosted
import com.example.geek_central.model.Manga
import com.example.geek_central.model.Popular
import com.example.geek_central.observer.IObservable
import kotlinx.android.synthetic.main.fragment_manga.*

class MangaFragment(mainFragment: MainFragment) : Fragment() {

    var list = ArrayList<Manga>()
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

        if(list.size == 0) {
            list.add(
                Manga(
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
                Manga(
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
                Manga(
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
                Manga(
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

        val adapterManga =  RecyclerMangaAdapter(list, this.requireContext())

        recycler_manga.layoutManager = LinearLayoutManager(context)
        recycler_manga.adapter = adapterManga


        iOBservable.add(adapterManga, "manga")

    }

}
