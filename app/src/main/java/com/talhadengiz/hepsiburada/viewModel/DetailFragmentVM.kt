package com.talhadengiz.hepsiburada.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talhadengiz.hepsiburada.data.model.Result

class DetailFragmentVM : ViewModel() {
    var search = MutableLiveData<String>()
    var media = MutableLiveData<Result>()
}