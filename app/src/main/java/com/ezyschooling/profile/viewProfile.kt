package com.ezyschooling.profile

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.ezyschooling.*
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.userhome.HomeFragment
import com.ezyschooling.usertags.TagFragment
import com.ezyschooling.utils.Parents
import kotlinx.android.synthetic.main.activity_view_profile.*

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
        //        Select Permissions
        val permissions:Array<String?> = arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            Log.e("PERMISSIONS","TAKE PERMISION")
            ActivityCompat.requestPermissions(this,permissions,0)
        }


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
        RetrofitClient.context=this
        val parent: Parents = SharedprefManager.getInstance(this)?.getParents() ?:Parents()

        txtName.setText(parent.name)

        Glide.with(this).asBitmap().load(parent.photo).into(imageProfile)

    }
    fun setFragments(){
        val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
       transaction.replace(
           R.id.container,
           HomeFragment()
       )
        transaction.commit()
    }
    fun listeners(){
        btnEdit.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                var intent:Intent =Intent(this@viewProfile,
                    EditProfile::class.java)
                startActivity(intent)
            }
        })
        home_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                home_btn.setColorFilter(resources.getColor(R.color.colorBgGrey))
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    HomeFragment()
                )
                transaction.commit()
            }
        })
        notification_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    NotificationFragment()
                )
                transaction.commit()
            }
        })
        btnFavourite.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    FavouriteFragment()
                )
                transaction.commit()
            }
        })
        btnBookMark.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    BookMarkFragment()
                )
                transaction.commit()
            }
        })
        btnTag.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    TagFragment()
                )
                transaction.commit()
            }
        })
    }
}