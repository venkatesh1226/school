package com.ezyschooling.userfavourite

import com.google.gson.annotations.SerializedName
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.Result

data class resultVideos (@SerializedName("count")
                        val count: Int,
                         @SerializedName("results")
                        val results: Array<Result>)