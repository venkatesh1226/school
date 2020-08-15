package com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi


import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)