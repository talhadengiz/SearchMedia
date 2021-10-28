package com.talhadengiz.hepsiburada.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.util.AnimUtils.getAnimationToPositionX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SwitchCategoryButton(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    companion object {
        fun valueOf(value: Int): Category? = Category.values().find { it.ordinal == value }
    }

    enum class Category(val queryName: String) { // todo queryName
        MOVIE("MOVIE"), MUSIC("MUSIC"),
        APP("APP"), BOOK("BOOK")
    }

    var onSelectionChanged: ((Category) -> Unit)? = null

    private lateinit var tvSelection: TextView
    private lateinit var tvMovie: TextView
    private lateinit var tvMusic: TextView
    private lateinit var tvApp: TextView
    private lateinit var tvBook: TextView

    private var isColdStart = true
    private var selectedCategoryState: Int = Category.MOVIE.ordinal

    init {
        View.inflate(context, R.layout.custom_category_switch_button, this)
        initView()
        initListeners()
    }

    private fun initView() {
        tvSelection = findViewById(R.id.tv_selection)
        tvMovie = findViewById(R.id.tv_movie)
        tvMusic = findViewById(R.id.tv_music)
        tvApp = findViewById(R.id.tv_app)
        tvBook = findViewById(R.id.tv_book)
    }

    private fun initListeners() {
        tvMovie.setOnClickListener {
            changeSelection(Category.MOVIE)
            tvMovie.moveSelection()
        }
        tvMusic.setOnClickListener {
            changeSelection(Category.MUSIC)
            tvMusic.moveSelection()
        }
        tvApp.setOnClickListener {
            changeSelection(Category.APP)
            tvApp.moveSelection()
        }
        tvBook.setOnClickListener {
            changeSelection(Category.APP)
            tvBook.moveSelection()
        }

    }

    private fun changeSelection(movies: Category) {
//        hideKeyboard(rootView.context, rootView) todo
        selectedCategoryState = movies.ordinal
        onSelectionChanged?.let { it(movies) }
    }

    private fun TextView.moveSelection(isUseAnimation: Boolean = true) {
        tvSelection.text = this.text.toString()
        if (isUseAnimation) {
            tvSelection.getAnimationToPositionX(this.x).start()
        } else {
            tvSelection.x = this.x
        }
    }

    fun setCategory(status: Int, useAnimation: Boolean = true) {
        valueOf(status)?.let { priority ->
            GlobalScope.launch(Dispatchers.Main) {
                if (isColdStart) {
                    delay(50)
                    isColdStart = false
                }
                when (priority) {
                    Category.MOVIE -> tvMovie.moveSelection(useAnimation)
                    Category.MUSIC -> tvMusic.moveSelection(useAnimation)
                    Category.APP -> tvApp.moveSelection(useAnimation)
                    Category.BOOK -> tvBook.moveSelection(useAnimation)
                }
                onSelectionChanged?.let {
                    if (useAnimation) {
                        it(priority)
                    }
                }
            }
        }
    }
}