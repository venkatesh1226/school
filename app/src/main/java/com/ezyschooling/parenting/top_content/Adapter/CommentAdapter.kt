package com.ezyschooling.parenting.top_content.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ezyschooling.R
import com.ezyschooling.parenting.ParentingHomeActivity
import com.ezyschooling.parenting.top_content.CommentReplyCreateActivity
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Result
import com.ezyschooling.parenting.top_content.comment_reply_display_activity
import kotlinx.android.synthetic.main.comment_layout.view.*

class CommentAdapter(private val commentList:MutableList<Result>): RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        context=parent.context
        return CommentAdapter.CommentHolder(
            LayoutInflater.from(context).inflate(R.layout.comment_layout,parent,false)
        )
    }

    override fun getItemCount(): Int {
return commentList.size   }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        if (commentList[position].parent!=null)
        {holder.comment_writer.text=commentList[position].parent.name}
        if (commentList[position].timestamp!=null)
        holder.comment_time.text=commentList[position].timestamp
        if(commentList[position].comment!=null)
        holder.comment_content.text=commentList[position].comment
        holder.comment_like_count.text=commentList[position].likesCount.toString()
        holder.comment_reply_count.text=commentList[position].childrenCommentCount.toString()

            holder.reply_count.setOnClickListener {
                val intent = Intent(context, comment_reply_display_activity::class.java)
                intent.putExtra("comment_id",commentList[position].id.toString())
                intent.putExtra("article_id",commentList[position].article.toString())
                intent.putExtra("discussion_id",commentList[position].discussion.toString())
                ContextCompat.startActivity(context, intent, Bundle())


            }
        holder.reply_button.setOnClickListener {
            val intent = Intent(context, CommentReplyCreateActivity::class.java)
            intent.putExtra("comment_id",commentList[position].id.toString())
            intent.putExtra("article_id",commentList[position].article.toString())
            intent.putExtra("discussion_id",commentList[position].discussion.toString())

            ContextCompat.startActivity(context, intent, Bundle())

        }

    }
    class CommentHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
        var comment_writer : TextView
        var comment_time: TextView
        var comment_content: TextView
        var reply_count: ImageButton
        var reply_button: Button
        var comment_like_count: TextView
        var comment_reply_count: TextView
        //   var txt_team : TextView
        //  var txt_createdby : TextView

        init {

            comment_writer = itemView.comment_writer
            comment_time= itemView.comment_time
           comment_content=itemView.comment_content
            reply_count=itemView.reply_count
            reply_button=itemView.reply_button
            comment_like_count=itemView.like_count
            comment_reply_count=itemView.reply_count_no
            //  txt_team = itemView.txt_team
            // txt_createdby = itemView.txt_createdby
        }

    }

}