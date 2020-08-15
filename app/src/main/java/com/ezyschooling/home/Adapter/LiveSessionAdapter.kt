package com.ezyschooling.home.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.home.API.Result
import com.ezyschooling.parenting.top_content.Adapter.ImageAdapter
import com.ezyschooling.parenting.top_content.YouTubePlayerAPIActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.live_sessions_layout.view.*
import okhttp3.internal.wait
import org.w3c.dom.Text

class LiveSessionAdapter(private val dataList: MutableList<Result>): RecyclerView.Adapter<LiveSessionAdapter.DataHolder>() {
    private lateinit var context: Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        context=parent.context

        var itemView = LayoutInflater.from(context).inflate(R.layout.live_sessions_layout, parent, false)
        return LiveSessionAdapter.DataHolder(itemView)
    }

    override fun getItemCount(): Int {
return dataList.size   }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        Picasso.get().load(dataList[position].speakerPhoto).into(holder.photo)
        holder.description.text=dataList[position].description
        holder.name.text=dataList[position].speakerName
        val videourl=dataList[position].url
        videourl.substring(32,videourl.length)
        holder.watch.setOnClickListener {
            val intent = Intent(context, YouTubePlayerAPIActivity::class.java)
            intent.putExtra("id",videourl.substring(32,videourl.length) )
            ContextCompat.startActivity(context, intent, Bundle())
        }

    }
    class DataHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
var photo: ImageView
        var description: TextView
                var name: TextView
        var watch: Button


        init {
            photo=itemView.photo
            description=itemView.description
            name=itemView.name
            watch=itemView.watch_button
        }

    }
}