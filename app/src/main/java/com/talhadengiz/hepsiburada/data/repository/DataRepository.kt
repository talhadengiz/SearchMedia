package com.talhadengiz.hepsiburada.data.repository

import com.talhadengiz.hepsiburada.data.source.RemoteDataSource

class DataRepository(val remoteDataSource: RemoteDataSource) {
    suspend fun getDataFromRemote(searchQuery:String, media: String){
        remoteDataSource.getDataFromApi(searchQuery, media)
    }
}