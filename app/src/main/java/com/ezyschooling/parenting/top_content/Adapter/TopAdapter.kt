package com.ezyschooling.parenting.top_content.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.SingleArticle.singlearticleactivity
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_top_reads.view.*

class TopAdapter(private val dataList: MutableList<Result>): RecyclerView.Adapter<TopAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context=parent.context

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_top_reads,parent,false))

    }

    override fun getItemCount(): Int {
return dataList.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data=dataList[position]

        holder.top_read_name.text = dataList[position].miniTitle


        holder.author_name.text=dataList[position].createdBy.name

        holder.txtlikes.text=data.likeCount.toString()
        holder.txtcomment.text=data.commentCount.toString()

        holder.itemView.setOnClickListener {
          //  Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()

            val intent = Intent(context, singlearticleactivity::class.java)

            intent.putExtra("slug",dataList[position].slug)

            intent.putExtra("id",dataList[position].id)

            intent.putExtra("likes",dataList[position].likeCount.toString())

            intent.putExtra("commentcount",dataList[position].commentCount.toString())

            startActivity(context,intent,Bundle())

            Log.i("likes intent",dataList[position].likeCount.toString())

        }
       val thumbnail=holder.itemView.top_read_thumbnail
        Picasso.get().load(data.thumbnail).into(thumbnail)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var top_read_name : TextView
        var thumbnail: ImageView
        var author_name: TextView
        var txtlikes:TextView
        var txtcomment:TextView
        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {

         top_read_name = itemView.top_read_name
            thumbnail= itemView.top_read_thumbnail
            author_name=itemView.author_name
            txtlikes=itemView.txtlikes
            txtcomment=itemView.txtcomment
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }

    }

}