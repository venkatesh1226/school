package com.ezyschooling.api


import com.ezyschooling.Child.Child
import com.ezyschooling.Child.ChildResponse
import com.ezyschooling.LogIn.models.Profile.ProfileResponse
import retrofit2.Call
import com.ezyschooling.api.models.DefaultResponse
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Comment
import retrofit2.http.*
import simplifiedcoding.net.kotlinretrofittutorial.models.LoginResponse
import com.ezyschooling.userfavourite.resultArticles
import com.ezyschooling.userfavourite.resultDiscussions
import com.ezyschooling.userfavourite.resultVideos
import com.ezyschooling.usertags.TagStatus
import com.ezyschooling.usertags.resultTags
import com.ezyschooling.utils.Parents

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


//      Post New Child
    @FormUrlEncoded
    @POST("/api/v1/childs/childs-add/")
    fun addChild(
    @Field("user") user: Int,
    @Field("name") name:String,
    @Field("photo") photo:String,
    @Field("date_of_birth") date_of_birth:String,
    @Field("gender") gender:String):Call<Child>

// get Parent
    @GET("/api/v1/parents/")
    fun getParent():Call<Parents>

// Update Parent Name
    @FormUrlEncoded
    @PATCH("/api/v1/parents/{slug}/")
    fun updateParent(@Path("slug") slug:String,@Field("name") name :String):Call<Parents>


//    Update parent photo
@FormUrlEncoded
@PATCH("/api/v1/parents/{slug}/")
fun updateParentProfile(@Path("slug") slug:String, @Field("photo") photo :String ):Call<Parents>


//    Get Liked Articles
@GET("/api/v1/parents/liked-articles/")
fun likedArticles():Call<resultArticles>


//    Get Liked Videos
@GET("/api/v1/parents/liked-videos/")
fun likedVideos():Call<resultVideos>


//    Get Liked Discussions
@GET("/api/v1/parents/liked-discussions/")
fun likedDiscussions():Call<resultDiscussions>


//    Get Bookmarked Articles
@GET("/api/v1/parents/bookmarked-articles/")
fun bookmarkedArticles():Call<resultArticles>


//    Get Bookmarked Videos
@GET("/api/v1/parents/bookmarked-videos/")
fun bookmarkedVideos():Call<resultVideos>


//    Get Bookmarked Discussions
@GET("/api/v1/parents/bookmarked-discussions/")
fun bookmarkedDiscussions():Call<resultDiscussions>


//    Get All Followed Tags
@GET("/api/v1/parents/followed-tags/")
fun followedTags():Call<resultTags>


//    Get All Tags
@GET("/api/v1/parents/all-tags-expert-parent-followed/")
fun allTags():Call<resultTags>

//    Following Tags

@POST("/api/v1/parents/{tag_slug}/follow/")
fun followTag(@Path("tag_slug") slug:String):Call<TagStatus>


//    Status of Tags
@GET("/api/v1/parents/{tag_name}/tag-status/")
fun tagStatus(@Path("tag_name") slug:String):Call<TagStatus>

    @FormUrlEncoded
    @POST("/api/v1/articles/275/comments/create/")
    fun createcomment(
        @Field("comment") comment:String
    ):Call<Comment>


}