package com.ezyschooling.profile

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.ezyschooling.Child.Child
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.api.RetrofitClient.instance1
import kotlinx.android.synthetic.main.dialog_add_child.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DialogAddChild : DialogFragment (){
    lateinit var name:EditText
    lateinit var DOB:EditText
    lateinit var radioBoy:RadioButton
    lateinit var radioGirl:RadioButton
    lateinit var spinnerClass:Spinner
    lateinit var isClass:CheckBox
    lateinit var browseBtn:Button
    lateinit var browseEdit:EditText
    lateinit var cancel:Button
    lateinit var ok:Button
    lateinit var picker:DatePickerDialog
    lateinit var image:ImageView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view:View?=activity?.layoutInflater?.inflate(R.layout.dialog_add_child,null)
        var dialog:AlertDialog.Builder=AlertDialog.Builder(context).setView(view)
        if(view!=null){
        init(view)
        }
        context?.let { listeners(it) }
        return dialog.show()
    }
    fun listeners(ctx:Context){
        browseBtn.setOnClickListener(View.OnClickListener {
            var builder: AlertDialog.Builder=AlertDialog.Builder(context)
                .setMessage("Select From Below")
                .setPositiveButton("Take Picture"){dialog, which ->
                    var capture: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(capture,1)
                }
                .setNegativeButton("Select from Gallery"){dialog, which ->
                    var take: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(take,2)
                }
                .setNeutralButton("Cancel"){dialog, which ->

                }
            builder.create().show()
        })


        cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dismiss()
            }
        })

        DOB.setOnClickListener(object : View.OnClickListener{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(v: View?) {
                val cldr= Calendar.getInstance()
                val day=cldr.get(Calendar.DAY_OF_MONTH)
                val month=cldr.get(Calendar.MONTH)
                val year=cldr.get(Calendar.YEAR)
                if(context!=null)
                    picker=
                        DatePickerDialog(context!!,
                            DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth
                            ->DOB.setText(""+year+"-"+month+"-"+dayOfMonth)},year,month,day)
                picker.show()

            }
        })
        if (isClass.isChecked)
            spinnerClass.visibility=View.INVISIBLE
        else
            spinnerClass.visibility=View.VISIBLE
        ok.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if(name.text.trim().length==0)
                    name.error="Name Required"
               else if(DOB.text.trim().length==0)
                    DOB.error="Date Of Birth Required"
               else if ((radioBoy.isChecked||radioGirl.isChecked)==false){
                    radioBoy.error="Gender Required"
                    radioGirl.error="Gender Required"
                }
                else{
                    var gender:String=""
                    if(radioGirl.isChecked)
                        gender="female"
                    else
                        gender="male"
                    RetrofitClient.context= ctx
                    val id:Int= SharedprefManager.getInstance(ctx)?.getParents()?.id ?: -1

                    instance1.addChild(id,name.text.toString(),"",DOB.text.toString(),gender)
                        .enqueue(object : Callback<Child> {
                            override fun onFailure(call: Call<Child>, t: Throwable) {
                                Toast.makeText(ctx,"Connection Lost :(",Toast.LENGTH_SHORT).show()
                                dismiss()
                            }

                            override fun onResponse(call: Call<Child>, response: Response<Child>) {
                                if(response.isSuccessful){

                                    Toast.makeText(ctx,"Child Added Successfully",Toast.LENGTH_SHORT).show()}
                                 else
                                    Toast.makeText(ctx,"Something Went Wrong Try Again:(",Toast.LENGTH_SHORT).show()
                                dismiss()

                            }
                        } )
                }


            }
        })

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                val images: Bitmap = data?.extras?.get("data") as Bitmap
                image.setImageBitmap(images)
            }}
        else if(requestCode==2){
            if(resultCode== Activity.RESULT_OK){
                val images: Uri? =data?.data
                image.setImageURI(images)
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data)
    }


    fun init(v:View){
        name=v.findViewById(R.id.edt_name)
        DOB=v.findViewById(R.id.edt_DOB)
        radioBoy=v.findViewById(R.id.radio_boy)
        radioGirl=v.findViewById(R.id.radio_girl)
        spinnerClass=v.findViewById(R.id.spinner_class)
        isClass=v.findViewById(R.id.check_class)
        browseBtn=v.findViewById(R.id.btn_browse)
        browseEdit=v.findViewById(R.id.edt_upload_photo)
        cancel=v.findViewById(R.id.btn_cancel)
        ok=v.findViewById(R.id.btn_Ok)
        image=v.findViewById(R.id.image)
    }
}