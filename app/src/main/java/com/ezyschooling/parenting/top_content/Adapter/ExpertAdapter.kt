package com.ezyschooling.parenting.top_content.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.Discussions.DiscussionsAcitvity
import com.ezyschooling.R
import com.ezyschooling.expert.expertactivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.our_experts_item.view.*

class ExpertAdapter(private val expertList: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.Result>) : RecyclerView.Adapter<ExpertAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView
        var profilephoto: ImageView
        var designation: TextView
        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {

            name = itemView.expert_name
            profilephoto= itemView.expert_photo
            designation=itemView.expert_designation
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.our_experts_item,parent,false))
    }


    override fun getItemCount(): Int {
return expertList.size   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=expertList[position]
        holder.name.text=data.name
        holder.designation.text=data.designation
        val profile=holder.profilephoto
        Picasso.get().load(data.profilePicture).into(profile)
        holder.itemView.setOnClickListener {
            //  Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()
            val intent = Intent(context, expertactivity::class.java)
            intent.putExtra("slug",expertList[position].slug)
            ContextCompat.startActivity(context, intent, Bundle())


        }


    }
    }



