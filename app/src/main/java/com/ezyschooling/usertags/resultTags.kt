package com.ezyschooling.usertags

import com.google.gson.annotations.SerializedName

data class resultTags(@SerializedName("count") val count: Integer,@SerializedName("results") val results: MutableList<Tag>) {
}