package com.ezyschooling.Child
import com.google.gson.annotations.SerializedName
data class ChildResponse (@SerializedName("count") val count: Integer,@SerializedName("results") val results:Array<Child>)