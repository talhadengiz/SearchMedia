package com.talhadengiz.hepsiburada.data.source

import com.talhadengiz.hepsiburada.network.RetrofitProvider

class RemoteDataSource {
    suspend fun getDataFromApi(searchQuery:String) = RetrofitProvider.api.getData(searchQuery)
}