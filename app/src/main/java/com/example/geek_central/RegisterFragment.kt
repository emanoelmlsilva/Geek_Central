package com.example.geek_central

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geek_central.component.CategoriesComponent
import com.example.geek_central.component.CounterComponent
import com.example.geek_central.component.NoteComponent
import com.example.geek_central.component.SeasonComponent
import com.example.geek_central.databinding.FragmentRegisterBinding
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.model.Host
import com.example.geek_central.model.Popular
import com.example.geek_central.model.WorkGeekAnime
import com.example.geek_central.model.WorkGeekManga
import com.example.geek_central.viewmodels.WorkGeekViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class RegisterFragment : Fragment() {

    lateinit var bindBing : FragmentRegisterBinding
    private lateinit var typeWork: String
    private lateinit var counterLef : CounterComponent
    private lateinit var counterRigth : CounterComponent
    private lateinit var noteComponent: NoteComponent
    private lateinit var seasonComponent: SeasonComponent
    private lateinit var categoriesComponent: CategoriesComponent
    private var cont = 0L;
    private lateinit var mWorkGeekViewModel : WorkGeekViewModel
    private var isFavorite : Boolean = false
    private var check : Boolean = false

    //objects
    private val popular = Popular()
    private val host = Host()
    private val workGeek = WorkGeekManga()
    private val workGeekAnime = WorkGeekAnime()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindBing = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        mWorkGeekViewModel = ViewModelProvider(this).get(WorkGeekViewModel::class.java)

        return bindBing.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        typeWork = arguments?.getString("type").toString()

        val textTitle = when(typeWork){
            TypeWork.MANGA.toString() -> "Mangá"
            TypeWork.ANIME.toString() -> "Anime"
            TypeWork.HQ.toString() -> "Hq"
            else -> "Mangá"
        }
        bindBing.txtTitle.text = " ${bindBing.txtTitle.text} ${textTitle}"

        initView(view)

        setOnClicks()
    }

    private fun initView(view: View){
        counterLef = CounterComponent(view.findViewById(R.id.edit_LeftRegister))
        counterLef.setHint("Cap. Atual")
        counterRigth = CounterComponent(view.findViewById(R.id.edit_RigthRegister))
        counterRigth.setHint("Cap. Total")

        noteComponent = NoteComponent(view.findViewById(R.id.noteIncludeMenuRegister))

        seasonComponent = SeasonComponent(view.findViewById(R.id.seasonIncludeMenuRegister))

        categoriesComponent = CategoriesComponent(view.findViewById(R.id.categorisRegister))

        if(typeWork.equals(TypeWork.ANIME.toString())) seasonComponent.getCard().visibility = View.VISIBLE
    }

    private fun setOnClicks(){
        bindBing.btnClose.setOnClickListener{
            navToMainFragment()
        }

        counterLef.setLessValue()
        counterLef.setMoreValue()

        counterLef.setInputLayoutValue()

        counterRigth.setLessValue()
        counterRigth.setMoreValue()

        counterRigth.setInputLayoutValue()

        noteComponent.setOnClickProgress()

        bindBing.btnSaveRegister.setOnClickListener { insertDataToDatabase() }

        actionInputLayoutName()

        categoriesComponent.getClickCips()
    }

    private fun setIconFavorite(isFavorite: Boolean) {

        var myDrawable: Drawable = resources.getDrawable(
            R.drawable.ic_favorite_border_black_24dp,
            null
        )
        var iconColor =  R.color.colorAccent

        if (isFavorite) {
            iconColor = R.color.iconHeartEnable
            myDrawable = resources.getDrawable(R.drawable.ic_favorite_black_24dp, null)
        }

        bindBing.inputLayoutNameRegister.startIconDrawable = myDrawable

        bindBing.inputLayoutNameRegister.setStartIconTintList(
            ContextCompat.getColorStateList(
                requireContext(),
                iconColor
            )
        )

    }

    private fun actionInputLayoutName(){
        bindBing.inputLayoutNameRegister.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verificarInputName(s.toString())
            }
        })

        bindBing.inputLayoutNameRegister.setStartIconOnClickListener {
            isFavorite = !isFavorite

            setIconFavorite(isFavorite)
        }
    }

    private fun verificarInputName(title: String){

       runBlocking {  launch(Dispatchers.IO) {
           check =  when(typeWork){
               TypeWork.MANGA.toString() -> mWorkGeekViewModel.findByTitleManta(title)
               TypeWork.ANIME.toString() -> mWorkGeekViewModel.findByTitleAnime(title)
                else -> false
            }
        } }

        val message = if(title.isNullOrBlank()) getString(R.string.errorNotNull) else getString(R.string.error)
        bindBing.inputLayoutNameRegister.error = if(check || title.isNullOrBlank()) message  else null


    }

    private fun insertDataToDatabase() {

        if(checkDataToSave()){

            counterLef.isNullSetDefault()
            counterRigth.isNullSetDefault()

            val title = bindBing.inputLayoutNameRegister.editText?.text.toString()
            val currentGeek = counterLef.getValueInput()
            val totalGeek = counterRigth.getValueInput()
            val grade = noteComponent.getGrade()
            val favorite = isFavorite
            val site = bindBing.inputLayoutSiteRegister.editText?.text.toString()

            when(typeWork){

                TypeWork.MANGA.toString() -> {

                    workGeek.title = title
                    workGeek.currentGeek = currentGeek
                    workGeek.totalGeek = totalGeek
                    popular.grade = grade
                    popular.favorite = favorite

                    host.site = site

                    mWorkGeekViewModel.insert(popular, host, workGeek)
                }
                TypeWork.ANIME.toString() -> {

                    workGeekAnime.title = title
                    workGeekAnime.currentGeek = currentGeek
                    workGeekAnime.totalGeek = totalGeek
                    workGeekAnime.season = seasonComponent.getSeason()
                    popular.grade = grade
                    popular.favorite = favorite

                    host.site = site

                    mWorkGeekViewModel.insert(popular, host, workGeekAnime)
                }
                else -> "Mangá"
            }

            Toast.makeText(requireContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show()
            navToMainFragment()
        }else{
            Toast.makeText(context, "Dados incorretos", Toast.LENGTH_LONG).show()
        }


    }

    private fun checkDataToSave(): Boolean{

        val checkName = !bindBing.inputLayoutNameRegister.editText?.text.toString().isNullOrBlank()
        verificarInputName(bindBing.inputLayoutNameRegister.editText?.text.toString())
        return checkName
    }

    private fun navToMainFragment() {
        findNavController().popBackStack()
    }
}