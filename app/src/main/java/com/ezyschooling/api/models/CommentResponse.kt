package com.ezyschooling.api.models


import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Result
import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("anonymous_user")
    val anonymousUser: String,
    @SerializedName("article")
    val article: Int,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("expert")
    val expert: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("parent")
    val parent: Any,
    @SerializedName("timestamp")
    val timestamp: String
)

