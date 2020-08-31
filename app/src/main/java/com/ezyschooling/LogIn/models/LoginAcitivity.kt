package com.ezyschooling.LogIn.models

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezyschooling.LogIn.models.Profile.ProfileResponse
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import com.ezyschooling.api.Api
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.signup.SignUpActivity
import com.ezyschooling.utils.Parents
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import simplifiedcoding.net.kotlinretrofittutorial.models.LoginResponse
import java.io.IOException


class LoginAcitivity : AppCompatActivity() {

    lateinit var sharedPreferencesLogin: SharedPreferences
    var AUTH=""

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    val BASE_URL = "https://api.parenting.ezyschooling.com/"
//   val  BASE_URL="https://api.dev.ezyschooling.com/"


    val instance1: Api by lazy {
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original = chain.request()
                    val requestbuilder = original.newBuilder()
                        .header(
                            "Authorization", AUTH)
                        .method(original.method, original.body)
                    val request = requestbuilder.build()
                    return chain.proceed(request)
                }
            }
            ).build()
        Log.i("token inst",sharedPreferencesLogin.getString("token"," ").toString())
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

        retrofit.create(Api::class.java)
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //   sessionManager = SessionManager(this)
        sharedPreferencesLogin =
            this.getSharedPreferences("package com.ezyschooling.LogIn.models", Context.MODE_PRIVATE)


        buttonLogin.setOnClickListener {
            //  val username= editUsername.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            fun isEmailValid(email: String): Boolean {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
            if (email.isEmpty()) {
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()

                return@setOnClickListener
            }
            if (!isEmailValid(email)) {
                editTextEmail.error = "Invalid Email"
                editTextEmail.requestFocus()

                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            Toast.makeText(this@LoginAcitivity,"Loading...",Toast.LENGTH_SHORT).show()

            RetrofitClient.instance.userLogin(email, password).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "something went wrong", Toast.LENGTH_LONG).show()
                }

                @SuppressLint("ShowToast")
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()
                    if (response.code() == 200) {

                        sharedPreferencesLogin.edit().putString("token", loginResponse?.key.toString()).apply()

                        //using a common class for saving,clearing,login,logout,getting token
                        SharedprefManager.getInstance(this@LoginAcitivity)?.saveToken(loginResponse?.key.toString())
                        //ends
                        AUTH = "Token " +sharedPreferencesLogin.getString("token"," ").toString()


                        Toast.makeText(applicationContext,"login successful", Toast.LENGTH_SHORT).show()
                        fetchprofile()
                        fetchParent()
                        finish()
                        /*
                         intent = Intent(applicationContext, HomeActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        */


                    }else{
                        Toast.makeText(applicationContext,"invalid credentials", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    fun fetchParent(){
        instance1.getParent().enqueue(object:Callback<Parents>{
            override fun onFailure(call: Call<Parents>, t: Throwable) {
            Toast.makeText(this@LoginAcitivity,"Connection Lost",Toast.LENGTH_SHORT).show()
                Log.e("ERROR",t.localizedMessage)
            }

            override fun onResponse(call: Call<Parents>, response: Response<Parents>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        SharedprefManager.getInstance(this@LoginAcitivity)?.saveParents(
                            it
                        )
                    }
                Toast.makeText(this@LoginAcitivity,response.body().toString(),Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(this@LoginAcitivity,"Authentication failed",Toast.LENGTH_SHORT).show()

            }
        })
    }
    fun fetchprofile() {
        instance1.userprofile()
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message + "Failed", Toast.LENGTH_LONG)
                        .show()
                    Log.i("Fail", t.message + "Failed")
                }

                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    Toast.makeText(applicationContext, "Welcome "+response.body(), Toast.LENGTH_LONG).show()
                    Log.i("response", response.body().toString() + " " + response.code())
                }
            })

    }

    fun register_as_parent(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun register_as_school(view: View) {
        Toast.makeText(applicationContext, "yet to code ", Toast.LENGTH_LONG).show()
    }


}






/* override fun onStart() {
     super.onStart()

     if(SharedPrefManager.getInstance(this).isLoggedIn){
         val intent = Intent(applicationContext, ProfileActivity::class.java)
         intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

         startActivity(intent)
     }*/




