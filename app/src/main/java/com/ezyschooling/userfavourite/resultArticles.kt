package com.ezyschooling.userfavourite
import com.google.gson.annotations.SerializedName

data class resultArticles (@SerializedName("count")
                           val count: Int,
                           @SerializedName("results")
                           val results: Array<com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result>)
