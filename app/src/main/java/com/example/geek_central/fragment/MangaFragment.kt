package com.example.geek_central.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geek_central.adapter.RecyclerMangaAdapter
import com.example.geek_central.model.Manga
import com.example.geek_central.databinding.FragmentMangaBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_manga.*

class MangaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewBinding = FragmentMangaBinding.inflate(layoutInflater, container, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { 
        val list = ArrayList<Manga>()
        list.add(Manga( "One Piece",
            702,
        972,
        4.3,
        true, null, null, null, null))
        list.add(Manga( "Kingdom",
            641,
            641,
            4.3,
            true, null, null, null, null))
        list.add(Manga( "One Piece",
            702,
            972,
            4.3,
            true, null, null, null, null))
        list.add(Manga( "Kingdom",
            641,
            641,
            4.3,
            true, null, null, null, null))
        list.add(Manga( "One Piece",
            702,
            972,
            4.3,
            true, null, null, null, null))
        list.add(Manga( "Kingdom",
            641,
            641,
            4.3,
            true, null, null, null, null))
        list.add(Manga( "One Piece",
            702,
            972,
            4.3,
            true, null, null, null, null))
        list.add(Manga( "Kingdom",
            641,
            641,
            4.3,
            true, null, null, null, null))
        recycler_manga.layoutManager = LinearLayoutManager(context)
        recycler_manga.adapter = RecyclerMangaAdapter(list, this.requireContext())
    }

}
