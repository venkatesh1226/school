package com.ezyschooling.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.api.RetrofitClient.instance
import com.ezyschooling.api.RetrofitClient.instance1
import com.ezyschooling.utils.Parents
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class EditProfile : AppCompatActivity(),DialogEditDetails.Updation {
    lateinit var userImage: ImageView
    lateinit var editImage:Button
    lateinit var addChild:  Button
    lateinit var txtName:TextView
    lateinit var txtRel:TextView
    lateinit var editProfile:Button
    lateinit var profileButton:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        init()
        setView()
        listeners()
    }
    fun init(){
        userImage=findViewById(R.id.imageView2)
        editImage=findViewById(R.id.edit_photo)
        addChild=findViewById(R.id.add_child)
        txtName=findViewById(R.id.txt_name)
        txtRel=findViewById(R.id.txt_relation)
        editProfile=findViewById(R.id.btn_edit)
        profileButton=findViewById(R.id.user_profile)

    }
    fun setView(){
        RetrofitClient.context=this
        val parent: Parents = SharedprefManager.getInstance(this)?.getParents() ?:Parents()
        txtName.setText(parent.name)
        if(parent.gender=="Male")
        txtRel.setText("Father")
        else txtRel.setText("Mother")
    }
    fun listeners(){
        profileButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent =Intent(this@EditProfile,viewProfile::class.java)

                startActivity(intent)
            }
        })
        editProfile.setOnClickListener(object : View.OnClickListener  {
            override fun onClick(v: View?) {
//                TODO:CREATE DIALOG FOR EDITING USER NAME
                val dialogEditDetails:DialogEditDetails= DialogEditDetails()
                dialogEditDetails.show(this@EditProfile.supportFragmentManager,"Dialog edit details")
            }
        })
        addChild.setOnClickListener(object:View.OnClickListener {
            override fun onClick(v: View?) {

                val dialogAddChild:DialogAddChild= DialogAddChild()
                dialogAddChild.show(this@EditProfile.supportFragmentManager,"Dialog Add Child")
            }
        })
        editImage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//               CREATE DIALOG FOR CHANGING USER PROFILE PHOTO
                var builder: AlertDialog.Builder=AlertDialog.Builder(this@EditProfile)
                    .setMessage("Select From Below")
                    .setPositiveButton("Take Picture"){dialog, which ->
                        var capture:Intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(capture,1)
                    }
                    .setNegativeButton("Select from Gallery"){dialog, which ->
                        val intent = Intent()
                        intent.type = "image/*"
                        intent.action = Intent.ACTION_GET_CONTENT
                        startActivityForResult(Intent.createChooser(intent, "pick an image"), 2)

                    }
                    .setNeutralButton("Cancel"){dialog, which ->

                    }
                builder.create().show()
            }
        })
        }



    fun encode():String{
        val drawable:BitmapDrawable=userImage.drawable as BitmapDrawable
        val bitmap:Bitmap=drawable.bitmap
        val bos:ByteArrayOutputStream= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos)
        val bb:ByteArray=bos.toByteArray()
        return Base64.encodeToString(bb,Base64.DEFAULT)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                val image: Bitmap= data?.extras?.get("data") as Bitmap
                userImage.setImageBitmap(image)
//                Glide.with(this).asBitmap().load(image).into(userImage)
                upload(encode())


            }}
            else if(requestCode==2){
            if(resultCode== Activity.RESULT_OK){
                val image: Uri? =data?.data
                userImage.setImageURI(image)
                upload(encode())





            }
        }
        else
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun upload(img:String){
        RetrofitClient.context=this

//        RetrofitClient.BASE_URL="https://api.dev.ezyschooling.com/"
        Log.e("ERROR",img)
        val parent:Parents= SharedprefManager.getInstance(this)?.getParents() ?: Parents()
        instance1.updateParentProfile(parent.slug,img).enqueue(object:Callback<Parents>{
            override fun onFailure(call: Call<Parents>, t: Throwable) {
                Log.e("ERROR",t.message)
                Toast.makeText(this@EditProfile,"Connection Lost",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Parents>, response: Response<Parents>) {

                if(response.isSuccessful){
                    Toast.makeText(this@EditProfile,response.body()?.photo,Toast.LENGTH_SHORT).show()
                }
                else{
//                    Toast.makeText(this@EditProfile,"Something Went Wrong"+response.errorBody(),Toast.LENGTH_SHORT).show()
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Toast.makeText(
                            this@EditProfile,
                            jObjError.getJSONObject("error").getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e("ERROR",jObjError.getJSONObject("error").getString("message"))
                    } catch (e: Exception) {
                        Toast.makeText(this@EditProfile, e.message, Toast.LENGTH_LONG).show()
                        Log.e("ERROR",e.message)

                    }

                Log.e("ERROR",response.message()+response.code()+response.errorBody()?.charStream()+response.body()+response.raw())}
            }
        })
    }

    override fun updation(Name: String) {
        txtName.setText(Name)
    }
}
