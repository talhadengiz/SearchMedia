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
import retrofit2.Response
import java.io.IOException

class SearchFragmentVM : ViewModel() {
    private val repository = DataRepository(RemoteDataSource())
    var dataLiveData = MutableLiveData<DataResponse>()
    var dataLiveData_ : DataResponse?=null
    var page = 1

    fun getData(searchQuery: String, media: String) = viewModelScope.launch {
        try {
            var response = repository.remoteDataSource.getDataFromApi(searchQuery=searchQuery,media=media)
            if (response.isSuccessful){
                response.body()?.let{ resultResponse ->
                   /* page++
                    if (dataLiveData_ == null){
                        dataLiveData_ = response.body()
                    }else{
                        val oldData = dataLiveData_?.results
                        val newData = response.body()?.results
                        if (newData != null) {
                            oldData?.addAll(newData)
                        }
                    }*/
                    //dataLiveData.postValue(dataLiveData_ ?: resultResponse)
                    dataLiveData.postValue(resultResponse)
                }
            }
        } catch (exception: IOException) {
            Log.d("Test", "exception: $exception")
        }
    }
}
