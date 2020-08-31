package com.ezyschooling.profile

import com.google.gson.annotations.SerializedName


data class Parent
    (@SerializedName("id") var id: Integer, @SerializedName("name") val name: String, @SerializedName("email") val email: String, @SerializedName("photo") val photo:String, @SerializedName("parent_type")val parent_type: String,@SerializedName("slug")val slug: String,@SerializedName("bio")val bio: String,@SerializedName("gender")val gender: String,@SerializedName("user")val user: String)