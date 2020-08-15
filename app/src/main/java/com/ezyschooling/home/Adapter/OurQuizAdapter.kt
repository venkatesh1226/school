package com.ezyschooling.home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.home.API.Quizzes.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.our_quizzes.view.*

class OurQuizAdapter(private val dataList: MutableList<Result>): RecyclerView.Adapter<OurQuizAdapter.QuizHolder>(){
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizHolder {
        context=parent.context

        var itemView = LayoutInflater.from(context).inflate(R.layout.our_quizzes, parent, false)
        return OurQuizAdapter.QuizHolder(itemView)
    }

    override fun getItemCount(): Int {
return dataList.size   }

    override fun onBindViewHolder(holder: QuizHolder, position: Int) {
        Picasso.get().load(dataList[position].image).into(holder.quizphoto)
    }
    class QuizHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
var quizphoto: ImageView
        init {
            quizphoto=itemView.quizphoto
        }
    }

}