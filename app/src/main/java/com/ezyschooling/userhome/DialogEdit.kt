package com.ezyschooling.userhome

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.ezyschooling.Child.Child
import com.ezyschooling.R
import com.ezyschooling.api.RetrofitClient
import kotlinx.android.synthetic.main.dialog_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DialogEdit(child: Child) : DialogFragment() {
    lateinit var picker:DatePickerDialog
    var children:Child=child
    lateinit var constImage:ConstraintLayout
    lateinit var child_name:EditText
    lateinit var DOB:EditText
    lateinit var spinner:Spinner
    lateinit var radioGroup:RadioGroup
    lateinit var close:Button
    lateinit var update:Button
    lateinit var builder:AlertDialog.Builder


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view:View?=activity?.layoutInflater?.inflate(R.layout.dialog_edit,null)
        builder=AlertDialog.Builder(activity)
        builder.setView(view)
        if (view != null) {

            init(view)
            listeners(view)
        }



        return builder.show()
    }
   fun  init(v:View){
       constImage=v.findViewById(R.id.const_photo)
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
                if(context!=null)
                RetrofitClient.context= context as Context
                RetrofitClient.instance1.
                updateChild(children.id,children.name,"",children.date_of_birth,children.gender
                ).enqueue(object:Callback<Child>{
                    override fun onFailure(call: Call<Child>, t: Throwable) {
                        Log.d("Error",t.message)
                        Toast.makeText(context,"Connection Lost :(",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Child>, response: Response<Child>) {
                        if (response.isSuccessful)
                            if(response.code()==200)
                            Toast.makeText(context,"Updated Successfully",Toast.LENGTH_SHORT).show()

                        else

                           Toast.makeText(context,"Something Went Wrong Try Again :(",Toast.LENGTH_SHORT).show()
                        dismiss()
                    }
                })


            }
        })


    }
}