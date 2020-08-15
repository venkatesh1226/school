package com.ezyschooling.parenting.top_content.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.Discussions.DiscussionsAcitvity
import com.ezyschooling.R
import com.ezyschooling.home.HomeActivity
import com.ezyschooling.parenting.ParentingHomeActivity
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.Result
import com.ezyschooling.parenting.top_content.YouTubePlayerAPIActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_video_item.view.*


class TopVideoAdapter(private val dataList: MutableList<Result>): RecyclerView.Adapter<TopVideoAdapter.VideoHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        context=parent.context
        return VideoHolder(LayoutInflater.from(context).inflate(R.layout.top_video_item,parent,false))
    }

    override fun getItemCount(): Int {
return dataList.size   }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        var videourl=dataList[position].url
        var imageurl="https://img.youtube.com/vi/"+ videourl.substring(32,videourl.length) +"/mqdefault.jpg"
        Log.i("imageurl",imageurl)
      Picasso.get().load(imageurl).into(holder.videoimage)
        holder.title.text=dataList[position].title
        holder.itemView.setOnClickListener {
            holder.itemView.setOnClickListener {
                //  Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()
                val intent = Intent(context, YouTubePlayerAPIActivity::class.java)
                intent.putExtra("id",videourl.substring(32,videourl.length) )
                ContextCompat.startActivity(context, intent, Bundle())


            }

        }
    }
    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoimage : ImageView
        var title: TextView

        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {

            videoimage = itemView.top_video
            title= itemView.top_video_title
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }
    }

}


