package com.ezyschooling.profile

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.api.RetrofitClient.instance1
import com.ezyschooling.utils.Parents
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DialogEditDetails:DialogFragment() {
    lateinit var edtName:EditText
    lateinit var edtEmail:EditText
    lateinit var edtRel:EditText
    lateinit var update:Button
    lateinit var close:Button
    lateinit var updation:Updation
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            updation=activity as Updation

        }
        catch ( exception:Exception){
            exception.printStackTrace()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view: View?=activity?.layoutInflater?.inflate(R.layout.dialog_edit_details,null)
        var dialog: AlertDialog.Builder= AlertDialog.Builder(context).setView(view)
        if(view!=null){
            init(view)
        }
        context?.let { listeners(it) }

        return dialog.show()
    }
    fun listeners(context:Context) {
        close.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dismiss()
            }
        })
        update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val parent: Parents =
                    SharedprefManager.getInstance(context)?.getParents() ?: Parents()


                if (edtName.text.toString().trim().length == 0)
                    edtName.error = "Name Required"
                else {
                    Toast.makeText(context,edtName.text.toString(),Toast.LENGTH_SHORT).show()
                    RetrofitClient.context = context

                    instance1.updateParent(parent.slug,edtName.text.toString())
                        .enqueue(object : Callback<Parents> {
                            override fun onFailure(call: Call<Parents>, t: Throwable) {
                                Toast.makeText(context, "Connection Lost", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            override fun onResponse(
                                call: Call<Parents>,
                                response: Response<Parents>
                            ) {
                                if (response.isSuccessful&&response.code()==200) {
                                    response.body()?.let {
                                        SharedprefManager.getInstance(context)?.saveParents(
                                            it
                                        )

                                    }
                                    updation.updation(edtName.text.toString())
                                    dismiss()
                                    Toast.makeText(context,"Updated Successfully :)",Toast.LENGTH_SHORT).show()
                                } else
                                    Toast.makeText(
                                        context,
                                        "Something Went Wrong :(",
                                        Toast.LENGTH_SHORT
                                    ).show()

                            }
                        })


                }



            }
        })
    }


    fun init(v:View){

        edtName=v.findViewById(R.id.edt_name)
        edtEmail=v.findViewById(R.id.edt_email)
        edtRel=v.findViewById(R.id.edt_rel)
        update=v.findViewById(R.id.btn_update)
        close=v.findViewById(R.id.btn_close)

        val parent:Parents= context?.let { SharedprefManager.getInstance(it)?.getParents() } ?:Parents()
        edtName.setText( parent.name)
        edtEmail.setText(parent.email)
        if(parent.gender=="Male")
            edtRel.setText("Father")
        else
            edtRel.setText("Mother")
    }

    //CALLBACK INTERFACE FOR UPDATING VALUES IN AN INSTINCT WAY

    interface Updation{
        fun updation(Name:String)
    }


}