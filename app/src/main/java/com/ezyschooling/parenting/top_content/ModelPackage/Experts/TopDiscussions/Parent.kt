package com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions


import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: Any,
    @SerializedName("slug")
    val slug: String
)