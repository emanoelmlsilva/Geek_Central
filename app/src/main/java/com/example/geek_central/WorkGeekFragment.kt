package com.example.geek_central

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.example.geek_central.adapter.RecyclerWorkGeekAdapter
import com.example.geek_central.databinding.FragmentWorkgeekBinding
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.observer.IObservable
import com.example.geek_central.viewmodels.WorkGeekViewModel
import kotlinx.android.synthetic.main.fragment_workgeek.*


class WorkGeekFragment(mainFragment: MainFragment, private val typeWork: String) : Fragment() {

    private var iOBservable: IObservable = mainFragment

    private lateinit var workGeekViewModel: WorkGeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewBinding = FragmentWorkgeekBinding.inflate(layoutInflater, container, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        workGeekViewModel = ViewModelProvider(this).get(WorkGeekViewModel::class.java)

        val textMessage = when (typeWork) {
            TypeWork.MANGA.toString() -> resources.getString(R.string.nameManga)
            TypeWork.ANIME.toString() -> resources.getString(R.string.nameAnime)
            TypeWork.HQ.toString() -> resources.getString(R.string.nameHq)
            else -> resources.getString(R.string.nameManga)
        }

        txtMessageEmpty.text = "${txtMessageEmpty.text} ${textMessage}"

        val adapter: RecyclerWorkGeekAdapter = RecyclerWorkGeekAdapter(workGeekViewModel)

        recycler_work_geek.layoutManager = LinearLayoutManager(context)
        recycler_work_geek.adapter = adapter

        iOBservable.add(adapter)

        adapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                if (adapter.itemCount == 0) {
                    txtMessageEmpty.visibility = View.VISIBLE
                } else {
                    txtMessageEmpty.visibility = View.GONE
                }
            }
        })

        val list = when (typeWork) {
            TypeWork.MANGA.toString() -> workGeekViewModel.getAllWorkGeeksMangas()
            TypeWork.ANIME.toString() -> workGeekViewModel.getAllWorkGeeksAnimes()
            else -> workGeekViewModel.getAllWorkGeeksMangas()
        }

        list.observe(viewLifecycleOwner, Observer { workGeeks ->
            adapter.setData(workGeeks)
        })
    }

}
