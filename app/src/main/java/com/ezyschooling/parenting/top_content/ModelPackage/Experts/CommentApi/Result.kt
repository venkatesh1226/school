package com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("anonymous_user")
    val anonymousUser: Any,
    @SerializedName("article")
    val article: Int,
    @SerializedName("discussion")
    val discussion: Int,
   @SerializedName("children_comment_count")
    val childrenCommentCount: Int,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("expert")
    val expert: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("parent")
    val parent: Parent,
    @SerializedName("timestamp")
    val timestamp: String
)