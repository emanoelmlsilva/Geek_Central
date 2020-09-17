package com.example.geek_central

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.geek_central.component.CategoriesComponent
import com.example.geek_central.databinding.FragmentInforBinding
import com.example.geek_central.model.BaseWorkGeek
import com.google.android.material.button.MaterialButton

class InforFragment : Fragment() {

    private lateinit var bindBing: FragmentInforBinding

    lateinit var baseWorkGeek: BaseWorkGeek
    lateinit var favorite: MaterialButton
    lateinit var categoriesComponent: CategoriesComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        baseWorkGeek = arguments?.getParcelable("dataBaseWorkGeek")!!

        bindBing = FragmentInforBinding.inflate(layoutInflater, container, false)

        return bindBing.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favorite = bindBing.root.findViewById(R.id.favorite)
        categoriesComponent = CategoriesComponent(bindBing.root.findViewById(R.id.categoriesInfor))

        setDataInComponents()
        setIconLoadingFavorite(baseWorkGeek.popular.favorite)

        bindBing.btnClose.setOnClickListener {
            popBack()
        }
    }

    private fun setDataInComponents() {
        categoriesComponent.getTitle().visibility = View.GONE
        categoriesComponent.setBackgroundChips(R.color.colorPrimaryDark)
        categoriesComponent.setVisibilityChips(baseWorkGeek.categories)

        bindBing.txtTitle.setText(baseWorkGeek.title)
        bindBing.txtAuthor.setText(baseWorkGeek.author)
        bindBing.txtCapCurrent.setText(baseWorkGeek.currentGeek.toString())
        bindBing.txtCapTotal.setText(baseWorkGeek.totalGeek.toString())
        bindBing.txtGrade.setText(baseWorkGeek.popular.grade.toString())
        bindBing.txtSite.setText(baseWorkGeek.host.site)
    }

    private fun popBack() {
        findNavController().popBackStack()
    }

    private fun setIconLoadingFavorite(checkIcon: Boolean) {

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
