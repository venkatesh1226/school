package com.ezyschooling.home.API


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("description")
    val description: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("event_date")
    val eventDate: String,
    @SerializedName("speaker_name")
    val speakerName: String,
    @SerializedName("speaker_photo")
    val speakerPhoto: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)