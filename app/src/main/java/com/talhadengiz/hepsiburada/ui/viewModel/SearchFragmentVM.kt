package com.talhadengiz.hepsiburada.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhadengiz.hepsiburada.data.model.DataResponse
import com.talhadengiz.hepsiburada.data.model.Result
import com.talhadengiz.hepsiburada.data.repository.DataRepository
import com.talhadengiz.hepsiburada.data.source.RemoteDataSource
import kotlinx.coroutines.launch
import java.io.IOException

class SearchFragmentVM : ViewModel() {
    private val repository = DataRepository(RemoteDataSource())
    var dataLiveData = MutableLiveData<DataResponse>()

    fun getData(searchQuery: String) = viewModelScope.launch {
        try {
            val response = repository.remoteDataSource.getDataFromApi(searchQuery)
            Log.d("Test", "Data: ${response.body()}")

            if (response.isSuccessful) {
                dataLiveData.postValue(response.body())
            }
        } catch (exception: IOException) {
            Log.d("Test", "exception: $exception")
        }
    }
}
