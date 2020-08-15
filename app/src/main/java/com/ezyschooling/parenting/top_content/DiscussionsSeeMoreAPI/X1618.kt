package com.ezyschooling.parenting.top_content.DiscussionsSeeMoreAPI


import com.google.gson.annotations.SerializedName

data class X1618(
    @SerializedName("anonymous_user")
    val anonymousUser: String,
    @SerializedName("board")
    val board: BoardXXXX,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("expert")
    val expert: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("parent")
    val parent: Any,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("sub_category")
    val subCategory: SubCategoryXXXX,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("views")
    val views: Int
)