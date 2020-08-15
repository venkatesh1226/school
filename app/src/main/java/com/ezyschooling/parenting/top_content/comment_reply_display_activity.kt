package com.ezyschooling.parenting.top_content

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.ezyschooling.R
import com.ezyschooling.parenting.top_content.Adapter.CommentAdapter
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Comment
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Result
import kotlinx.android.synthetic.main.activity_comment_reply_display_activity.*
import kotlinx.android.synthetic.main.single_article_layout.*

class comment_reply_display_activity : AppCompatActivity() {
lateinit var comment_id: String
    private var commentListReply:MutableList<Result> = mutableListOf()
    private lateinit var adapter1:CommentAdapter
    lateinit var article_id: String
    lateinit var discussion_id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_reply_display_activity)
        val intent:Intent
        intent=getIntent()
        val extras=intent.extras
        if(extras!=null)
        {
            comment_id=extras.getString("comment_id").toString()
            article_id=extras.getString("article_id").toString()
            discussion_id=extras.getString("discussion_id").toString()
            Log.i("comment id",comment_id)
            Log.i("article",article_id)
            Log.i("discussion id",discussion_id)
        }
        getCommentReplies()
        val backbutton = findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
    }




    fun getCommentReplies()
    {
        adapter1 = CommentAdapter(commentListReply)
        var url2: String
        url2=" "
        if(article_id!="0")
        {  url2 = "https://api.parenting.ezyschooling.com/api/v1/articles/comments/"+comment_id+"/thread/"
            Log.i("art url",url2)
        }
  else
      {    url2 = "https://api.parenting.ezyschooling.com/api/v1/discussions/comments/"+comment_id+"/thread/"
          Log.i("discussion url",url2)
      }

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(url2)
            .build().getAsObject(
                Comment::class.java,
                object : ParsedRequestListener<Comment> {
                    override fun onResponse(response: Comment) {
                        commentListReply.addAll(response.results)
                        Log.i("commentListsize",commentListReply.size.toString())
                        adapter1.notifyDataSetChanged()
                    }


                    override fun onError(anError: ANError?) {
                    }

                }
            )
        recyclerview_comments_reply.layoutManager =LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerview_comments_reply.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_comments_reply.adapter = adapter1
    }

}

