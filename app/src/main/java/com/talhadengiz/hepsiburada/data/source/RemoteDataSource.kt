package com.talhadengiz.hepsiburada.data.source

import com.talhadengiz.hepsiburada.network.RetrofitProvider

class RemoteDataSource {
    suspend fun searchMedia(searchQuery: String, media: String, page:Int) =
        RetrofitProvider.api.getData(searchTerm = searchQuery, media = media, page = page)
}