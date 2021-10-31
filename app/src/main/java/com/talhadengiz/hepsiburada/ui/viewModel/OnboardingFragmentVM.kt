package com.talhadengiz.hepsiburada.ui.viewModel

import androidx.lifecycle.ViewModel
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.data.model.OnboardingItem

class OnboardingFragmentVM : ViewModel(){
    val sliderList: Array<OnboardingItem> = arrayOf(
        OnboardingItem(
            resId = R.drawable.img_movie,
            title = "Lorem Ipsum"
        ),
        OnboardingItem(
            resId = R.drawable.img_music,
            title = "Lorem Ipsum"
        ),
        OnboardingItem(
            resId = R.drawable.img_app,
            title = "Lorem Ipsum"
        ),
        OnboardingItem(
            resId = R.drawable.img_ebook,
            title = "Lorem Ipsum"
        )
    )
}