package com.example.geek_central

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.geek_central.adapter.ViewPageAdapter
import com.example.geek_central.component.SearchViewComponent
import com.example.geek_central.databinding.FragmentMainBinding
import com.example.geek_central.enums.TypeOrderBy
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.observer.IObservable
import com.example.geek_central.observer.IObserver
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.reflect.Type

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), IObservable {

    private lateinit var bindBing: FragmentMainBinding;
    private lateinit var adapter: ViewPageAdapter
    private lateinit var componentSearchComponent: SearchViewComponent
    private lateinit var bottomSheetDialog: BottomSheetDialog

    private lateinit var observerManga: IObserver
    private lateinit var observerAnime: IObserver
    private lateinit var observerHq: IObserver

    private var filterValue: String = ""
    private var type: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        bindBing = FragmentMainBinding.inflate(layoutInflater, container, false)
        componentSearchComponent = SearchViewComponent(bindBing.root.findViewById(R.id.search_card))

        return bindBing.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        type = TypeWork.MANGA.toString()

        mountViewPagerWithTabs()
        setClicledViews()
    }

    val clickListenerMenu = View.OnClickListener { view ->

        showPopup(view)
    }

    private fun showPopup(view: View) {
        var popup: PopupMenu? = PopupMenu(context, view)
        popup!!.inflate(R.menu.header_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            val typeOrder = when (item!!.itemId) {
                R.id.Favorites -> TypeOrderBy.FAVORITE.toString()
                R.id.Name -> TypeOrderBy.TITLE.toString()
                R.id.Total -> TypeOrderBy.TOTAL.toString()
                R.id.Note -> TypeOrderBy.GRADE.toString()
                else -> TypeOrderBy.TOTAL.toString()
            }

            sendUpdate(typeOrder = typeOrder)

            true
        })

        popup.show()
    }

    private fun setClicledViews() {

        componentSearchComponent.getPopMenu().setOnClickListener(clickListenerMenu)

        componentSearchComponent.getSearch().setOnQueryTextListener(object :
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterValue = newText
                sendUpdate()
                return false
            }
        })

        bindBing.floatingActionButton.setOnClickListener {
            navToRegister()

        }

    }

    private fun navToRegister() {

        var bundle = bundleOf("type" to type)

        findNavController().navigate(R.id.action_mainFragment_to_registerFragment, bundle)
    }

    private fun mountViewPagerWithTabs() {
        adapter = ViewPageAdapter(childFragmentManager)
        adapter.addFragment(
            WorkGeekFragment(
                this,
                TypeWork.MANGA.toString()
            ), resources.getString(R.string.nameManga)
        )
        adapter.addFragment(
            WorkGeekFragment(
                this,
                TypeWork.ANIME.toString()
            ), resources.getString(R.string.nameAnime)
        )
//        adapter.addFragment(
//            WorkGeekFragment(
//                this,
//                TypeWork.HQ.toString()
//            ), resources.getString(R.string.nameHq)
//        )
        bindBing.viewPager.adapter = adapter
        bindBing.tabs.setupWithViewPager(bindBing.viewPager)


        bindBing.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                setTypeFromPosition(position)

            }

        })

    }

    override fun add(observer: IObserver) {
        println("type add ${type}")
        when (type) {
            TypeWork.MANGA.toString() -> {
                observerManga = observer
                type = TypeWork.ANIME.toString()
            }
            TypeWork.ANIME.toString() -> {
                observerAnime = observer
                type = TypeWork.MANGA.toString()
         //       type = TypeWork.HQ.toString()
            }
            TypeWork.HQ.toString() -> {
                type = TypeWork.MANGA.toString()
                observerHq = observer
            }
        }
        //modificar valor do type ao instanciar como manga
    }

    override fun remove(observer: IObserver) {

    }

    override fun sendUpdate(typeOrder: String) {
        println("type sendUpdate ${type}")
        when (type) {
            TypeWork.MANGA.toString() -> observerManga.update(filterValue, typeOrder, type!!)
            TypeWork.ANIME.toString() -> observerAnime.update(filterValue, typeOrder, type!!)
            TypeWork.HQ.toString() -> observerHq.update(filterValue, typeOrder, type!!)

        }

    }

    private fun setTypeFromPosition(position: Int) {
        type = when (position) {
            0 -> TypeWork.MANGA.toString()
            1 -> TypeWork.ANIME.toString()
            else -> TypeWork.HQ.toString()
        }
    }
}
