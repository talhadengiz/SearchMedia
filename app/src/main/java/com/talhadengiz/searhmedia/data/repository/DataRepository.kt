package com.talhadengiz.searhmedia.data.repository

import com.talhadengiz.searhmedia.data.source.RemoteDataSource

class DataRepository(val remoteDataSource: RemoteDataSource) {
    suspend fun getData(searchQuery: String, media: String, page: Int) {
        remoteDataSource.searchMedia(searchQuery, media, page)
    }
}