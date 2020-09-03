package com.example.geek_central.component

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.geek_central.R
import com.example.geek_central.model.BaseWorkGeek
import com.example.geek_central.model.WorkGeekAnimeWithPopularAndHosted
import com.example.geek_central.model.WorkGeekMangaWithPopularAndHosted
import com.example.geek_central.viewmodels.WorkGeekViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout


class MenuEditComponent(
    val view: View,
    val objGeek: BaseWorkGeek,
    val mWorkGeekViewModel: WorkGeekViewModel
) {

    lateinit var title: TextView

    private lateinit var componentCounteLeft: CounterComponent

    private lateinit var componentCounteRigth: CounterComponent

    private lateinit var inputName: TextInputLayout

    private lateinit var note: NoteComponent

    private lateinit var season: SeasonComponent

    private lateinit var categories: CategoriesComponent

    private lateinit var inputAuthor: TextInputLayout

    private lateinit var inputSite: TextInputLayout

    private lateinit var btnSave: MaterialButton

    private var status: Float = 0.0f

    private val isSeason = objGeek.season != null

    init {

        initView()

        clickButtons(componentCounteLeft)
        clickButtons(componentCounteRigth)

        loadingObject()

        visibiliteEditMinExtend()

        onClick()

    }

    private fun initView() {

        title = view.findViewById(R.id.txtTitle)

        componentCounteLeft = CounterComponent(view.findViewById(R.id.edit_Left))
        componentCounteLeft.setHint("Cap. Atual")

        componentCounteRigth = CounterComponent(view.findViewById(R.id.edit_Rigth))
        componentCounteRigth.setHint("Cap. Total")

        note = NoteComponent(view.findViewById(R.id.noteIncludeMenu))

        if (isSeason) season = SeasonComponent(view.findViewById(R.id.seasonIncludeMenu))

        categories = CategoriesComponent(view.findViewById(R.id.categoriesComponent))

        inputName = view.findViewById(R.id.inputLayoutName)

        inputAuthor = view.findViewById(R.id.inputLayoutAuthor)

        inputSite = view.findViewById(R.id.inputLayoutSite)

        btnSave = view.findViewById(R.id.btnSave)

    }

    private fun visibiliteEditMinExtend() {

        //transation slide up/down view title
        title.visibility = if (status < 0.002F) View.VISIBLE else View.GONE

        //set visibility components

        setVisibilite(inputName)

        setVisibilite(note.getCard())

        if (isSeason) {

            setVisibilite(season.getCard())

        }

        setVisibilite(categories.getCard())

        setVisibilite(inputAuthor)

        setVisibilite(inputSite)


    }


    private fun setVisibilite(myView: View) {

        if (status <= 0.12) {
            myView.visibility = View.GONE
        } else {
            myView.visibility = View.VISIBLE
        }
    }

    fun setStatus(statusUpdate: Float) {
        status = statusUpdate
        visibiliteEditMinExtend()
        animationSlide()
    }

    private fun animationSlide() {

        //set slide animation component

        slideUp(title)

        if (status == 0.0f) slideUp(inputName, toX = view.width.toFloat()) else slideUp(inputName)

        slideUp(note.getCard(), toX = view.width.toFloat() - (status * 1080))

        if (isSeason) slideUp(season.getCard(), toX = view.width.toFloat() - (status * 1080))

        slideUp(categories.getCard())

        slideUp(inputAuthor)

        slideUp(inputSite)

        slideUp(btnSave)
    }

    private fun slideUp(
        myView: View,
        fromX: Float = myView.width.toFloat() - (status * 1000),
        toX: Float = 0F,
        fromY: Float = 0F,
        toY: Float = 0F
    ) {

        val animate = TranslateAnimation(
            fromX,  // fromXDelta
            toX,  // toXDelta
            fromY,  // fromYDelta
            toY
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true

        myView.startAnimation(animate)
    }

    fun clickButtons(componentDefault: CounterComponent) {

        componentDefault.setLessValue()

        componentDefault.setMoreValue()

        componentDefault.setInputLayoutValue()

        btnSave.setOnClickListener {

            objGeek.title = inputName.editText?.text.toString()
            saveManga(objGeek)
        }

        inputName.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                title.text = s.toString()
            }
        })

        inputName.setStartIconOnClickListener {

            objGeek.popular.favorite = !objGeek.popular.favorite

            setIconFavorite(objGeek.popular.favorite)

        }

    }

    fun loadingObject() {

        if(objGeek.season != null) season.setItemSpinner(objGeek.season.toString())

        setIconFavorite(objGeek.popular.favorite)

        objGeek.title.let {
            title.text = it
            inputName.editText?.setText(it)
        }

        objGeek.currentGeek.let { componentCounteLeft.setTextLayout(it) }
        objGeek.totalGeek.let { componentCounteRigth.setTextLayout(it) }
        objGeek.popular.grade.let { setValueNote(it.toFloat()) }
        objGeek.host.site.let { inputSite.editText?.setText(it) }


    }

    private fun saveManga(objGeek: BaseWorkGeek) {

        val workGeekAnimeWithPopularAndHosted = WorkGeekMangaWithPopularAndHosted(objGeek.workGeekManga!!, objGeek.popular, objGeek.host)
        mWorkGeekViewModel.update(workGeekAnimeWithPopularAndHosted)
    }

    private fun loagindAnime(objGeek: WorkGeekAnimeWithPopularAndHosted) {

        setIconFavorite(objGeek.popular.favorite)

        season.setItemSpinner(objGeek.workGeek.season.toString())

        objGeek.workGeek.title.let {
            title.text = it
            inputName.editText?.setText(it)
        }

        objGeek.workGeek.currentGeek.let { componentCounteLeft.setTextLayout(it) }
        objGeek.workGeek.totalGeek.let { componentCounteRigth.setTextLayout(it) }
        objGeek.popular.grade.let { setValueNote(it.toFloat()) }
        objGeek.host.site.let { inputSite.editText?.setText(it) }
    }

    private fun loagindManga(objGeek: WorkGeekMangaWithPopularAndHosted) {

        setIconFavorite(objGeek.popular.favorite)

        objGeek.workGeek.title.let {
            title.text = it
            inputName.editText?.setText(it)
        }

        objGeek.workGeek.currentGeek.let { componentCounteLeft.setTextLayout(it) }
        objGeek.workGeek.totalGeek.let { componentCounteRigth.setTextLayout(it) }
        objGeek.popular.grade.let { setValueNote(it.toFloat()) }
        objGeek.host.site.let { inputSite.editText?.setText(it) }
    }

    private fun setIconFavorite(isFavorite: Boolean) {

        var myDrawable: Drawable =
            view.resources.getDrawable(R.drawable.ic_favorite_border_black_24dp, null)
        var iconColor = R.color.colorAccent

        if (isFavorite) {
            iconColor = R.color.iconHeartEnable
            myDrawable = view.resources.getDrawable(R.drawable.ic_favorite_black_24dp, null)
        }

        inputName.startIconDrawable = myDrawable

        inputName.setStartIconTintList(
            ContextCompat.getColorStateList(
                view.context,
                iconColor
            )
        )

    }

    private fun setValueNote(value: Float) {
        note.getSeekBar().progress = value.toInt() * 2
        note.setValueComponentTextNote(value)
    }

    private fun onClick() {
        note.setOnClickProgress()

        inputName.setStartIconOnClickListener {

            objGeek.popular.favorite = !objGeek.popular.favorite

            setIconFavorite(objGeek.popular.favorite)

        }
    }


}