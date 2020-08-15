package com.ezyschooling.home.API.Quizzes


import com.google.gson.annotations.SerializedName

data class OurQuizzes(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)