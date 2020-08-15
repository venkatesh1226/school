package com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos


import com.google.gson.annotations.SerializedName

data class SubCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)