package com.talhadengiz.hepsiburada.data.model

data class DataResponse(
    val resultCount: Int,
    val results: MutableList<Result>
)