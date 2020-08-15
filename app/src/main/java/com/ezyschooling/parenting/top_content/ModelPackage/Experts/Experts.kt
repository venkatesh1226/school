package com.ezyschooling.parenting.top_content.ModelPackage.Experts


import com.google.gson.annotations.SerializedName

data class Experts(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)