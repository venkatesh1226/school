package com.ezyschooling.userfavourite

import com.google.gson.annotations.SerializedName

data class resultDiscussions (@SerializedName("count")
                           val count: Int,
                              @SerializedName("results")
                           val results: Array<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result>)
