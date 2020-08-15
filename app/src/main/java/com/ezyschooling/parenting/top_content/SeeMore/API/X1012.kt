package com.ezyschooling.parenting.top_content.SeeMore.API


import com.google.gson.annotations.SerializedName

data class X1012(
    @SerializedName("board")
    val board: BoardX,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("comment_counts")
    val commentCounts: Int,
    @SerializedName("created_by")
    val createdBy: CreatedByX,
    @SerializedName("id")
    val id: Int,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("like_counts")
    val likeCounts: Int,
    @SerializedName("mini_title")
    val miniTitle: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("sub_category")
    val subCategory: SubCategoryX,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("views")
    val views: Int
)