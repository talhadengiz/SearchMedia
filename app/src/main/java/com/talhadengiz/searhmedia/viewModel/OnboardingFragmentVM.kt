package com.talhadengiz.searhmedia.viewModel

import androidx.lifecycle.ViewModel
import com.talhadengiz.searhmedia.R
import com.talhadengiz.searhmedia.data.model.OnboardingItem

class OnboardingFragmentVM : ViewModel(){
    val sliderList: Array<OnboardingItem> = arrayOf(
        OnboardingItem(
            resId = R.drawable.img_movie,
            title = "Search Movie"
        ),
        OnboardingItem(
            resId = R.drawable.img_music,
            title = "Search Music"
        ),
        OnboardingItem(
            resId = R.drawable.img_app,
            title = "Search App"
        ),
        OnboardingItem(
            resId = R.drawable.img_ebook,
            title = "Seerch E-Book"
        )
    )
}