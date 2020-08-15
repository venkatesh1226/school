package com.ezyschooling.home.API.InTheNews


import com.google.gson.annotations.SerializedName

data class InTheNews(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)