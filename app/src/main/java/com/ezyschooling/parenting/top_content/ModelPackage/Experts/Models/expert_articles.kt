package com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models


import com.google.gson.annotations.SerializedName

data class expert_articles(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>

)