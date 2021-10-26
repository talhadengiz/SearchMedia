package com.talhadengiz.hepsiburada.network

import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {
    @GET("search?")
    suspend fun getData(@Query("term") searchTerm:String)
}
