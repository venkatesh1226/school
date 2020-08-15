package com.ezyschooling.Child

import com.google.gson.annotations.SerializedName

data class Child(@SerializedName("id") val id: Integer, @SerializedName("user") val user: Integer, @SerializedName("name") var name:String, @SerializedName("photo")val photo: String, @SerializedName("date_of_birth") var date_of_birth: String, @SerializedName("gender") var gender: String)
