package com.ezyschooling.Discussions.API


import com.google.gson.annotations.SerializedName

data class Discussion(
    @SerializedName("anonymous_user")
    val anonymousUser: Any,
    @SerializedName("board")
    val board: Board,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("expert")
    val expert: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("parent")
    val parent: Parent,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("sub_category")
    val subCategory: SubCategory,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("views")
    val views: Int
)