package com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("board")
    val board: Board,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("expert")
    val expert: Expert,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("sub_category")
    val subCategory: SubCategory,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)