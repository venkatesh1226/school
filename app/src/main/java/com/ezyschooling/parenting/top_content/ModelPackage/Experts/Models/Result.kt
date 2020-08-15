package com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models


import com.google.gson.annotations.SerializedName

data class Result(
    /*@SerializedName("board")
    val board: Board,

    @SerializedName("comment_counts")
    val commentCounts: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("like_counts")
    val likeCounts: Int,
    @SerializedName("mini_title")
    val miniTitle: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("sub_category")
    val subCategory: SubCategory,*/
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("created_by")
    val createdBy: CreatedBy,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("like_count")
    val likeCount: Int,
    /* @SerializedName("timestamp")*/
    // val timestamp: String,
    @SerializedName("mini_title")
    val miniTitle: String,
    @SerializedName("slug")
val slug: String
    /* @SerializedName("title")
    val title: String*/
    //  @SerializedName("views")*/
    // val views: Int


)