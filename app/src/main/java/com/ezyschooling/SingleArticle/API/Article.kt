package com.ezyschooling.SingleArticle.API


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("board")
    val board: Board,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("comment_counts")
    val commentCounts: Int,
    @SerializedName("created_by")
    val createdBy: CreatedBy,
    @SerializedName("description")
    val description: String,
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
    val subCategory: SubCategory,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("views")
    val views: Int
)