package com.ezyschooling.LogIn.models.Profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(  @SerializedName("detail") var message: String,@SerializedName("id") val id: String, @SerializedName("email") val email: String,  @SerializedName("name") val name:String, @SerializedName("current_parent")val current_parent: String)
