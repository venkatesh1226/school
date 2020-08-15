package com.ezyschooling.expert.API


import com.google.gson.annotations.SerializedName

data class ExpertL(
    @SerializedName("bio")
    val bio: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_picture")
    val profilePicture: String,
    @SerializedName("quote")
    val quote: String,
    @SerializedName("slug")
    val slug: String
)