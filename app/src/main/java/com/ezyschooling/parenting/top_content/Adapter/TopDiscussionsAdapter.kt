package com.ezyschooling.parenting.top_content.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.ezyschooling.Discussions.DiscussionsAcitvity
import com.ezyschooling.R
import com.ezyschooling.SingleArticle.singlearticleactivity
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result
import kotlinx.android.synthetic.main.top_discussion_item.view.*

class TopDiscussionsAdapter(private val dataList: MutableList<Result>) : RecyclerView.Adapter<TopDiscussionsAdapter.TopHolder>() {
    private lateinit var context: Context

    class TopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var topDiscuss : TextView
        var age: TextView
        var type: TextView

        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {

            topDiscuss = itemView.top_discussion_link
            age= itemView.min_max
            type=itemView.type
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHolder {
        context=parent.context
        return TopHolder(LayoutInflater.from(context).inflate(R.layout.top_discussion_item,parent,false))    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TopHolder, position: Int) {
        holder.topDiscuss.text=dataList[position].title
        holder.age.text=dataList[position].board.name
        holder.type.text=dataList[position].subCategory.name
        holder.itemView.setOnClickListener {
            //  Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()
            val intent = Intent(context, DiscussionsAcitvity::class.java)
            intent.putExtra("slug",dataList[position].slug)
            intent.putExtra("id",dataList[position].id.toString())
            intent.putExtra("likes",dataList[position].likesCount.toString())
            intent.putExtra("commentcount",dataList[position].commentCount.toString())
            ContextCompat.startActivity(context, intent, Bundle())


        }
    }

}