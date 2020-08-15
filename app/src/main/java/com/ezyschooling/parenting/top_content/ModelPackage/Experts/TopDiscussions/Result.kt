package com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("anonymous_user")
    val anonymousUser: Any,
    @SerializedName("board")
    val board: Board,
    @SerializedName("comment_count")
    val commentCount: Int,
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
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("views")
    val views: Int
)