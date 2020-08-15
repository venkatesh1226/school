package com.ezyschooling.api


import com.ezyschooling.Child.Child
import com.ezyschooling.Child.ChildResponse
import com.ezyschooling.LogIn.models.Profile.ProfileResponse
import com.ezyschooling.SingleArticle.API.Article
import com.ezyschooling.api.models.CommentResponse
import retrofit2.Call
import com.ezyschooling.api.models.DefaultResponse
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Comment
import retrofit2.http.*
import simplifiedcoding.net.kotlinretrofittutorial.models.LoginResponse

interface Api {

    @FormUrlEncoded
    @POST("api/v1/parents/registration/")
    fun createUser(
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("name") name:String,
        @Field("gender") gender:String,
        @Field("password1") password1:String,
        @Field("parent_type") parent_type:String
    ):Call<DefaultResponse>
    @FormUrlEncoded
    @POST("api/v1/accounts/login/")
    fun userLogin(
        //@Field("username") username: String,
        @Field("email") email:String,
        @Field("password") password: String
    ):Call<LoginResponse>


    @GET("api/v1/accounts/")
    fun userprofile(

//      @Header("Authorization:") Token: String

    ):Call<ProfileResponse>

//Calling all Children of User
    @GET("api/v1/childs/{user_id}/parent-childs/")
    fun userChilds(@Path("user_id") user_id:String):Call<ChildResponse>

//    Deleting Children of user
    @DELETE("api/v1/childs/{id}/")
    fun deleteChild(@Path("id") id:String):Call<Void>

//    Updating Values Of Children
    @FormUrlEncoded
    @PATCH("/api/v1/childs/{id}/")
    fun updateChild(
    @Path("id") id:Integer,
    @Field("name") name:String,
    @Field("photo") photo:String,
    @Field("date_of_birth") date_of_birth:String,
    @Field("gender") gender:String):Call<Child>



    @FormUrlEncoded
    @POST("/api/v1/articles/275/comments/create/")
    fun createcomment(
        @Field("comment") comment:String
    ):Call<Comment>


}