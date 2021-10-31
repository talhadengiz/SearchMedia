package com.talhadengiz.searhmedia.data.source

import com.talhadengiz.searhmedia.network.RetrofitProvider

class RemoteDataSource {
    suspend fun searchMedia(searchQuery: String, media: String, page:Int) =
        RetrofitProvider.api.getData(searchTerm = searchQuery, media = media, page = page)
}