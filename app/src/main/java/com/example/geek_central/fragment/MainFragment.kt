package com.example.geek_central.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.geek_central.R
import com.example.geek_central.adapter.ViewPageAdapter
import com.example.geek_central.component.SearchViewComponent
import com.example.geek_central.databinding.FragmentMainBinding
import com.example.geek_central.enums.TypeOrderBy
import com.example.geek_central.observer.IObservable
import com.example.geek_central.observer.IObserver
import com.google.android.material.bottomsheet.BottomSheetDialog

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
    private var type: String = "manga"

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
            when (item!!.itemId) {
                R.id.Favorites -> {
                    filterValue = TypeOrderBy.FAVORITE.toString()
                }
                R.id.Name ->  filterValue = TypeOrderBy.NAME.toString()
                R.id.Total ->  filterValue = TypeOrderBy.TOTAL.toString()
                R.id.Note ->  filterValue = TypeOrderBy.GRADE.toString()
            }

            sendUpdate(true)

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
                sendUpdate(false)
                return false
            }
        })

    }

    private fun mountViewPagerWithTabs() {
        adapter = ViewPageAdapter(childFragmentManager)
        adapter.addFragment(MangaFragment(this), resources.getString(R.string.nameManga))
        adapter.addFragment(AnimeFragment(), resources.getString(R.string.nameAnime))
        adapter.addFragment(HqFragment(), resources.getString(R.string.nameHq))
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

            }

        })

    }

    override fun add(observer: IObserver, type: String) {
        this.type = type
        when (type) {
            "manga" -> observerManga = observer
            "anime" -> observerAnime = observer
            "hq" -> observerHq = observer
        }
    }

    override fun remove(observer: IObserver) {

    }

    override fun sendUpdate(typeOrder : Boolean) {

        when (type) {
            "manga" -> observerManga.update(filterValue, typeOrder)
            "anime" -> observerAnime.update(filterValue, typeOrder)
            "hq" -> observerHq.update(filterValue, typeOrder)

        }

    }

}
