package com.ezyschooling.parenting.top_content.DiscussionsSeeMoreAPI


import com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result
import com.google.gson.annotations.SerializedName

data class DiscussionSeeMore(
    @SerializedName("0-2")
    val x02: List<Result>,
    @SerializedName("10-12")
    val x1012: List<Result>,
    @SerializedName("12-14")
    val x1214: List<Result>,
    @SerializedName("14-16")
    val x1416: List<Result>,
    @SerializedName("16-18")
    val x1618: List<Result>,
    @SerializedName("2-4")
    val x24: List<Result>,
    @SerializedName("4-6")
    val x46: List<Result>,
    @SerializedName("6-8")
    val x68: List<Result>,
    @SerializedName("8-10")
    val x810: List<Result>
)