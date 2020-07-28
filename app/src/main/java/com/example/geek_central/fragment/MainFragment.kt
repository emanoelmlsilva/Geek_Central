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
                R.id.Favorites -> Toast.makeText(context, "Favorito", Toast.LENGTH_SHORT).show();
                R.id.Name -> Toast.makeText(context, "Nome", Toast.LENGTH_SHORT).show();
                R.id.Total -> Toast.makeText(context, "Tota", Toast.LENGTH_SHORT).show();
                R.id.Note -> Toast.makeText(context, "Nota", Toast.LENGTH_SHORT).show();
            }

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

    override fun sendUpdate() {

        when (type) {
            "manga" -> observerManga.update(filterValue)
            "anime" -> observerAnime.update(filterValue)
            "hq" -> observerHq.update(filterValue)

        }

    }

}
