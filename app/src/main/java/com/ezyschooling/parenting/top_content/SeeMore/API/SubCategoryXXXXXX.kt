package com.ezyschooling.parenting.top_content.SeeMore.API


import com.google.gson.annotations.SerializedName

data class SubCategoryXXXXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)