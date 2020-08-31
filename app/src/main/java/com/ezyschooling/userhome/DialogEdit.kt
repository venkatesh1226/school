package com.ezyschooling.userhome

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.ezyschooling.Child.Child
import com.ezyschooling.R
import com.ezyschooling.api.RetrofitClient
import kotlinx.android.synthetic.main.dialog_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.*

class DialogEdit(child: Child) : DialogFragment() {
    lateinit var picker:DatePickerDialog
    var children:Child=child
    lateinit var constImage:Button
    lateinit var child_name:EditText
    lateinit var DOB:EditText
    lateinit var spinner:Spinner
    lateinit var radioGroup:RadioGroup
    lateinit var close:Button
    lateinit var update:Button
    lateinit var image:ImageView
    lateinit var builder:AlertDialog.Builder


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view:View?=activity?.layoutInflater?.inflate(R.layout.dialog_edit,null)
        builder=AlertDialog.Builder(activity)
        builder.setView(view)
        if (view != null) {
            Toast.makeText(context,children.photo,Toast.LENGTH_SHORT).show()
            init(view)
            listeners(view)
        }



        return builder.show()
    }
   fun  init(v:View){
       image=v.findViewById(R.id.image_child)
       constImage=v.findViewById(R.id.browse_btn)
       child_name=v.findViewById(R.id.edt_child_name)
       DOB=v.findViewById(R.id.btn_DOB)
       spinner=v.findViewById(R.id.spinner_class)
       radioGroup=v.findViewById(R.id.radio_group)
       close=v.findViewById(R.id.btn_close)
       update=v.findViewById(R.id.btn_update)
      child_name.setText(children.name)
       DOB.setText(children.date_of_birth)
       if(children.gender=="male")
           radioGroup.check(R.id.radio_male)
       else
           radioGroup.check(R.id.radio_female)



   }
    fun listeners(v:View){

        constImage.setOnClickListener(View.OnClickListener {
            var builder: AlertDialog.Builder=AlertDialog.Builder(context)
                .setMessage("Select From Below")
                .setPositiveButton("Take Picture"){dialog, which ->
                    var capture: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
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
        })

        DOB.setOnClickListener(object : View.OnClickListener{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(v: View?) {
                val cldr=Calendar.getInstance()
                val day=cldr.get(Calendar.DAY_OF_MONTH)
                val month=cldr.get(Calendar.MONTH)
                val year=cldr.get(Calendar.YEAR)
                if(context!=null)
                picker=
                    DatePickerDialog(context!!,DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth
                        ->DOB.setText(""+year+"-"+month+"-"+dayOfMonth)},year,month,day)
                picker.show()

            }
        })
        close.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
            dismiss()
            }
        })
        update.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                children.name= child_name.text.toString()
                children.date_of_birth=DOB.text.toString()
                if(radioGroup.checkedRadioButtonId==R.id.radio_male)
                    children.gender="male"
                else
                    children.gender="female"

                if (context==null)
                    Toast.makeText(context,"No Context",Toast.LENGTH_SHORT).show()
                if(context!=null)
                RetrofitClient.context= context as Context

                RetrofitClient.instance1.
                updateChild(children.id,children.name,children.photo,children.date_of_birth,children.gender
                ).enqueue(object:Callback<Child>{
                    override fun onFailure(call: Call<Child>, t: Throwable) {
                        Log.d("Error",t.message)
                        Toast.makeText(context,"Connection Lost :(",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Child>, response: Response<Child>) {
                        if (response.isSuccessful)
                            Toast.makeText(context,"Updated Successfully"+response.body()?.photo,Toast.LENGTH_LONG).show()

                        else{
                            Log.e("ERROR",children.photo)
                            Log.d("DEBUG",response.errorBody().toString()+response.message())
                           Toast.makeText(context,"Something went wrong :("+response.code()+response.errorBody().toString()+response.message(),Toast.LENGTH_SHORT).show()
                       // dismiss()
                            }
                        dismiss()
                    }
                })


            }
        })


    }

    fun encode(imageUri:Uri?):String{
        val input= imageUri?.let { activity?.contentResolver?.openInputStream(it) }
        val image=BitmapFactory.decodeStream(input,null,null)
        val baos=ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG,100,baos)
        var imageBytes=baos.toByteArray()
        var imageString=Base64.encodeToString(imageBytes,Base64.NO_WRAP)

        return imageString

    }
    fun encode(bm:Bitmap):String{
        val baos=ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val b=baos.toByteArray()
        return Base64.encodeToString(b,Base64.NO_WRAP)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                val images: Bitmap = data?.extras?.get("data") as Bitmap
                children.photo= encode(images)
                image.visibility=View.VISIBLE
                image.setImageBitmap(images)

            }}
        else if(requestCode==2){
            if(resultCode== Activity.RESULT_OK){
                val images: Uri? =data?.data
                children.photo= encode(images)

                image.visibility=View.VISIBLE
                image.setImageURI(images)
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data)
    }

}