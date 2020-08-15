package com.ezyschooling.userhome

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezyschooling.Child.Child
import com.ezyschooling.R
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.api.RetrofitClient.instance1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildAdapter(ctx:FragmentActivity?): RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    lateinit var list: MutableList<Child>
     var cTx=ctx


    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context=parent.context
       val view:View=LayoutInflater.from(context).inflate(R.layout.child_adapter,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text=list.get(position).name
        holder.txtAge.text=list.get(position).date_of_birth
        holder.txtGender.text=list.get(position).gender
      if(null!=list.get(position).photo)
        Glide.with(context).asBitmap().load(list.get(position).photo).into(holder.image)

//       Deleting Child After DialogBox
        deleteChild(holder,position)
//        Editing Child Info
        holder.edit.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                editChild(position)
            }
        })

    }

    fun deleteChild(holder: ViewHolder, position: Int){
        holder.delete.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                val builder:AlertDialog.Builder=AlertDialog.Builder(context).setMessage("Are You Sure To Delete ")
                    .setPositiveButton("Yes") { dialogInterface, which ->
                        RetrofitClient.context = context
                        instance1.deleteChild(list.get(position).id.toString())
                            .enqueue(object : Callback<Void> {
                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT)
                                        .show()
                                }

                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(
                                            context,
                                            "deleted Succesfully" + response.code(),
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        list.removeAt(position)
                                        notifyDataSetChanged()
                                    } else
                                        Toast.makeText(context, "Went wrong", Toast.LENGTH_SHORT)
                                            .show()
                                }
                            })
                    }
                    .setNegativeButton("No"){dialog, which ->  }
                builder.create().show()
            }
        })
    }

//    Editing Child Information after Custom Dialog Box
    fun editChild(position:Int){


        var dialogEdit=DialogEdit(list.elementAt(position))
         dialogEdit.show(cTx?.supportFragmentManager,"CustomDialog");


      }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         var txtName:TextView=itemView.findViewById(R.id.txt_childname)
        var txtAge:TextView=itemView.findViewById(R.id.txt_childage)
         var txtGender:TextView=itemView.findViewById(R.id.txt_childgender)
         var edit:Button=itemView.findViewById(R.id.btn_edit)
         var delete:Button=itemView.findViewById(R.id.btn_delete)
         var image:ImageView=itemView.findViewById(R.id.image_child)
    }

}