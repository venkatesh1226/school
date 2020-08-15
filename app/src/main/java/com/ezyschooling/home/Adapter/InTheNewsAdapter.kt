package com.ezyschooling.home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.home.API.InTheNews.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.in_the_news_layout.view.*

class InTheNewsAdapter(private val dataList: MutableList<Result>) : RecyclerView.Adapter<InTheNewsAdapter.NewsHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        context=parent.context

        var itemView = LayoutInflater.from(context).inflate(R.layout.in_the_news_layout, parent, false)
        return InTheNewsAdapter.NewsHolder(itemView)

    }

    override fun getItemCount(): Int {
return dataList.size   }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        Picasso.get().load(dataList[position].image).into(holder.newsphoto)
    }
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
var newsphoto: ImageView
        init {
            newsphoto=itemView.news_logo
        }
    }

}