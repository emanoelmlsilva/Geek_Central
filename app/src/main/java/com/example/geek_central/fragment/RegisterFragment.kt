package com.example.geek_central.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.geek_central.R
import com.example.geek_central.component.*
import com.example.geek_central.databinding.FragmentRegisterBinding
import com.example.geek_central.enums.TypeWork
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    lateinit var bindBing : FragmentRegisterBinding
    private lateinit var typeWork: String
    private lateinit var counterLef : CounterComponent
    private lateinit var counterRigth : CounterComponent
    private lateinit var noteComponent: NoteComponent
    private lateinit var seasonComponent: SeasonComponent
    private lateinit var categoriesComponent: CategoriesComponent


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bindBing = FragmentRegisterBinding.inflate(layoutInflater, container, false)
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
            natToMainFragment()
        }

        counterLef.setLessValue()
        counterLef.setMoreValue()

        counterLef.setInputLayoutValue()

        counterRigth.setLessValue()
        counterRigth.setMoreValue()

        counterRigth.setInputLayoutValue()

        noteComponent.setOnClickProgress()


    }

    private fun natToMainFragment() {
        //setar se o conteudo da lista foi atualizado e adicionar qual dos tabs
        findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
    }
}