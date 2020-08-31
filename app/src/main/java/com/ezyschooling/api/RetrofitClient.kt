package com.ezyschooling.api


import android.content.Context
import com.ezyschooling.SharedprefManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {

      var BASE_URL = "https://api.parenting.ezyschooling.com/"
//   var  BASE_URL="https://api.dev.ezyschooling.com/"

    lateinit var context :Context

    fun getContext(c:Context){
        context=c
    }


  // private const val AUTH =

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }
    val instance1: Api by lazy {

      //  val AUTH:String =SharedprefManager.getInstance(context)?.token().toString()
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(object : Interceptor {

                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val requestbuilder = original.newBuilder()
                        .header("Authorization", SharedprefManager.getInstance(context)?.token().toString())
                        .method(original.method, original.body)
                    val request = requestbuilder.build()
                    return chain.proceed(request)
                }
            }


            ).build()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

        retrofit.create(Api::class.java)

    }
}


