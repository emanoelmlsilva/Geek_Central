package com.example.geek_central.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.geek_central.R
import com.example.geek_central.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var bindBing: FragmentMainBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

         bindBing = FragmentMainBinding.inflate(layoutInflater, container, false)

        setClicledViews()

        return bindBing.root
    }

    val clickListener = View.OnClickListener {view ->

        showPopup(view)
    }

    private fun showPopup(view: View){
        var popup: PopupMenu? = PopupMenu(context, view)
        popup!!.inflate(R.menu.header_menu)

        popup.setOnMenuItemClickListener (PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId){
                R.id.Favorites -> Toast.makeText(context, "Favorito", Toast.LENGTH_SHORT).show();
                R.id.Name -> Toast.makeText(context, "Nome", Toast.LENGTH_SHORT).show();
                R.id.Total -> Toast.makeText(context, "Tota", Toast.LENGTH_SHORT).show();
                R.id.Note -> Toast.makeText(context, "Nota", Toast.LENGTH_SHORT).show();
            }

            true
        } )

        popup.show()
    }

    private fun setClicledViews(){
        bindBing.btnPopMenu.setOnClickListener(clickListener)
    }


}
