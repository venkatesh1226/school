package com.ezyschooling.parenting.top_content.DiscussionsSeeMoreAPI


import com.google.gson.annotations.SerializedName

data class BoardXXXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("max_age")
    val maxAge: Int,
    @SerializedName("min_age")
    val minAge: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)