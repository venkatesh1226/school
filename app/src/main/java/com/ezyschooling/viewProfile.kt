package com.ezyschooling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.ezyschooling.LogIn.models.Profile.ProfileResponse
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.api.RetrofitClient.instance1
import com.ezyschooling.userhome.HomeFragment
import kotlinx.android.synthetic.main.activity_view_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class viewProfile : AppCompatActivity() {
    lateinit var txtName:TextView
    lateinit var btnEdit:Button
    lateinit var imageProfile:ImageView
    lateinit var btnHome:ImageButton
    lateinit var btnNotification:ImageButton
    lateinit var btnFavourite:ImageButton
    lateinit var btnBookMark:ImageButton
    lateinit var btnTag:ImageButton
    lateinit var frameLayout: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)


        init()
        showName()
        setFragments()
        listeners()

    }
    fun init(){
        txtName=findViewById(R.id.name_txt)
        btnEdit=findViewById(R.id.btn_edit)
        imageProfile=findViewById(R.id.profile_image)
        btnHome=findViewById(R.id.home_btn)
        btnNotification=findViewById(R.id.notification_btn)
        btnFavourite=findViewById(R.id.favourite_btn)
        btnBookMark=findViewById(R.id.bookmark_btn)
        btnTag=findViewById(R.id.tag_btn)
        frameLayout=findViewById(R.id.container)
    }
    fun showName(){
        Log.d("DEBUG",SharedprefManager.getInstance(this)?.token().toString())
        RetrofitClient.context=this
        instance1.userprofile()
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {

                    Toast.makeText(applicationContext, t.message + "Failed", Toast.LENGTH_LONG)
                        .show()
                    Log.i("Fail", t.message + "Failed")
                }

                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    txtName.text=response.body()?.name
                    SharedprefManager.getInstance(this@viewProfile)?.saveId(response.body()?.id)
                    //Glide.with(this@viewProfile).asBitmap().load(response.body().)
                    Toast
                        .makeText(this@viewProfile,SharedprefManager.getInstance(this@viewProfile)?.id(),Toast.LENGTH_LONG).show();


                   // Log.i("response", response.body().toString() + " " + response.code())
                }
            })
    }
    fun setFragments(){
        val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
       transaction.replace(R.id.container,
           HomeFragment()
       )
        transaction.commit()
    }
    fun listeners(){
        home_btn.setOnClickListener(object :View.OnClickListener{

            override fun onClick(v: View?) {
                home_btn.setColorFilter(resources.getColor(R.color.colorBgGrey))
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container,
                    HomeFragment()
                )
                transaction.commit()
            }
        })
        notification_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container,NotificationFragment())
                transaction.commit()
            }
        })
        btnFavourite.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container,FavouriteFragment())
                transaction.commit()
            }
        })
        btnBookMark.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container,BookMarkFragment())
                transaction.commit()
            }
        })
        btnTag.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container,TagFragment())
                transaction.commit()
            }
        })
    }
}