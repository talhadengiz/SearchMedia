package com.talhadengiz.searhmedia.network

import com.talhadengiz.searhmedia.data.model.DataResponse
import com.talhadengiz.searhmedia.util.Api
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {
    @GET(Api.SEARCH)
    suspend fun getData(
        @Query("term") searchTerm: String,
        @Query("media") media: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") page: Int = 1
    ): Response<DataResponse>
}
