package com.ezyschooling.usertags

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.R.color.colorRed
import com.ezyschooling.api.RetrofitClient.instance1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class TagAdapter(ctx: FragmentActivity?,status :String ) : RecyclerView.Adapter<TagAdapter.ViewHolder>(){
    lateinit var list: MutableList<Tag>
    var cTx=ctx
    lateinit var context: Context
    var status:String=status


    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var tagName:TextView=itemView.findViewById(R.id.txt_tagname)
        var followNo:TextView=itemView.findViewById(R.id.txt_no_follow)
        var follow:TextView=itemView.findViewById(R.id.txt_follow)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view:View= LayoutInflater.from(context).inflate(R.layout.tag_adapter,parent,false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tagName.text=list.get(position).name

        if(status=="Unfollow"){
            holder.follow.text="Unfollow"
            cTx?.resources?.getColor(R.color.colorRed)?.let { holder.follow.setTextColor(it) }

        }
        holder.followNo.text=""+(list.get(position).parent_following_count)+" People Following"
        holder.follow.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {try {

                api(position,holder)
            }
            catch (e:Exception){
                Log.e("ERROR",e.message)
            }



            }
        })

    }

    override fun getItemCount(): Int {
        return list.size

    }


    fun api(pos:Int,h:ViewHolder){
        instance1.followTag(list.get(pos).slug).enqueue(object : Callback<TagStatus> {
            override fun onFailure(call: Call<TagStatus>, t: Throwable) {
                Log.e("ERROR",t.message)
                Toast.makeText(context,"Connection Lost :(",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<TagStatus>, response: Response<TagStatus>) {
                if (response.isSuccessful){
                    Toast.makeText(context, status+"ing "+list.get(pos).name, Toast.LENGTH_SHORT).show()
                    list.removeAt(pos)
                        notifyDataSetChanged()
                }
                else {
//                    Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
                    Log.e("ERROR",""+response.errorBody().toString()+response.code().toString())



                }
            }
        })
    }
}