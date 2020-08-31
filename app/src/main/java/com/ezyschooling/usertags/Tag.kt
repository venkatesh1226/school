package com.ezyschooling.usertags

import com.google.gson.annotations.SerializedName

data class Tag (@SerializedName("id") val id: Integer,@SerializedName("name") val name: String,@SerializedName("slug") val slug: String,@SerializedName("parent_following_count") val parent_following_count: Integer){
}