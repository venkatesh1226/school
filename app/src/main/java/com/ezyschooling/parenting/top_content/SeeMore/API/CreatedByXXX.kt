package com.ezyschooling.parenting.top_content.SeeMore.API


import com.google.gson.annotations.SerializedName

data class CreatedByXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_expert_panel")
    val isExpertPanel: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_picture")
    val profilePicture: Any,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("user")
    val user: Int
)