package com.talhadengiz.hepsiburada.network

import com.talhadengiz.hepsiburada.data.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {
    @GET("search?")
    suspend fun getData(
        @Query("term") searchTerm: String,
        @Query("limit") limit: Int? = 20,
        @Query("media") media: String
    ): Response<DataResponse>
}
