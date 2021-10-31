package com.talhadengiz.searhmedia.data.model

data class DataResponse(
    val resultCount: Int,
    val results: MutableList<Result>
)