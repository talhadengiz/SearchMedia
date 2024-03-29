package com.talhadengiz.searhmedia.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhadengiz.searhmedia.data.model.DataResponse
import com.talhadengiz.searhmedia.data.repository.DataRepository
import com.talhadengiz.searhmedia.data.source.RemoteDataSource
import kotlinx.coroutines.launch
import java.io.IOException

class SearchFragmentVM : ViewModel() {
    private val repository = DataRepository(RemoteDataSource())
    var dataLiveData = MutableLiveData<DataResponse>()
    var dataLiveData_ : DataResponse?=null
    var page = 1

    fun getData(searchQuery: String, media: String) = viewModelScope.launch {
        try {
            var response = repository.remoteDataSource.searchMedia(searchQuery=searchQuery,media=media,page = page)
            if (response.isSuccessful){
                response.body()?.let{ resultResponse ->
                    page++
                    if (dataLiveData_ == null){
                        dataLiveData_ = response.body()
                    }else{
                        val oldData = dataLiveData_?.results
                        val newData = response.body()?.results
                        if (newData != null) {
                            oldData?.addAll(newData)
                        }
                    }
                    dataLiveData.postValue(dataLiveData_ ?: resultResponse)
                }
            }
        } catch (exception: IOException) {
            Log.d("Test", "exception: $exception")
        }
    }
}
