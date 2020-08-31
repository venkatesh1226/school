package com.ezyschooling.utils

import com.google.gson.annotations.SerializedName

data class Parents(
   @SerializedName("id") var id: Int =-1,
   @SerializedName("user") var user:Int=-1,
   @SerializedName("email") var email:String="",
   @SerializedName("name")var name:String="",
   @SerializedName("date_of_birth") var date_of_birth:String="",
   @SerializedName("gender") var gender:String="",
   @SerializedName("slug") var slug:String="",
   @SerializedName("photo")var photo:String="",
   @SerializedName("income")var income:Int=-1,
   @SerializedName("phone") var phone:String="",
   @SerializedName("bio") var bio:String="",
   @SerializedName("parent_type") var parent_type:String="",
   @SerializedName("education")var education:String="",
   @SerializedName("occupation") var occupation:String="",
   @SerializedName("office_address") var office_address:String="",
   @SerializedName("office_number") var office_number:String="",
   @SerializedName("alumni_school_name") var alumni_school_name:String="",
   @SerializedName("alumni_year_of_passing") var alumni_year_of_passing:String="",
   @SerializedName("passing_classs") var passing_classs:String="",
   @SerializedName("alumni_proof") var alumni_proof:String="",
   @SerializedName("timestamp") var timestamp:String=""
 ){
    constructor() : this(-1,-1,"","","","","","",-1,"","" +
            "","","","","","","","","" +
            "","","")
 }