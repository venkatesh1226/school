package com.ezyschooling.Discussions

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.ezyschooling.Discussions.API.Discussion
import com.ezyschooling.R
import com.ezyschooling.SingleArticle.API.Article
import com.ezyschooling.parenting.top_content.Adapter.CommentAdapter
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Comment
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Result
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.discussions_layout.*
import kotlinx.android.synthetic.main.single_article_layout.*
import org.json.JSONObject

class DiscussionsAcitvity : AppCompatActivity() {

     private  var commentListDiscussion: MutableList<Result> = mutableListOf()
    lateinit var adapterDiscussion: CommentAdapter
    lateinit var id: String
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discussions_layout)
        val commentbox=findViewById<EditText>(R.id.discussion_comment_box)
        commentbox.setOnTouchListener(View.OnTouchListener { v, event ->
            if (commentbox.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_SCROLL -> {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        return@OnTouchListener true
                    }
                }
            }
            false
        })
        val backbutton=findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
        var likecount:String
        var commentcount: String
        likecount = " "
        commentcount = " "
        var slug: String
        slug= " "
        val intent: Intent
        intent=getIntent()
        val extras = intent.extras
        if (extras != null) {
            slug = extras.getString("slug").toString()
            id= extras.getString("id").toString()
            likecount=extras.getString("likes").toString()
            commentcount = extras.getString("commentcount").toString()
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val likebutton = bottomNavigationView.menu.findItem(R.id.like_button)
        likebutton.title = likecount
        Log.i("like", likecount)
        val comment_button = bottomNavigationView.menu.findItem(R.id.comment_button)
        comment_button.title = commentcount
        Log.i("comment", commentcount)
        Log.i("slug",slug)
        val url: String
        url="https://api.parenting.ezyschooling.com/api/v1/discussions/"+slug+"/"
Log.i("url",url)
        AndroidNetworking.initialize(this)
        AndroidNetworking.get(url)
            .build().getAsObject(
                Discussion::class.java,
                object : ParsedRequestListener<Discussion> {
                    override fun onResponse(response: Discussion) {
                        if (response.parent!=null && response.parent.name!=null)
                        { discussion_writer_name.text=response.parent.name}
                        else
                        {
                            discussion_writer_name.text="Ezyschooling Correspondent"
                        }
                        discussion_time.text=response.timestamp
                        discusion_title.text=response.title
                        Picasso.get().load(response.subCategory.thumbnail).into(discussions_sub_category_photo)
                        Picasso.get().load(response.board.thumbnail).into(discussion_board_photo)
                        val content=response.description
                        discusion_title_mini.setText(Html.fromHtml(Html.fromHtml(content).toString()))

                    }
                    override fun onError(anError: ANError?) {
                    }

                }
            )


        val commentBoxDiscussion=findViewById<EditText>(R.id.discussion_comment_box)
        val comment=commentBoxDiscussion.text
        getcommentsDiscussion()
        val postbutton = findViewById<Button>(R.id.postdiscussion)
        val url2 =
            "https://api.parenting.ezyschooling.com/api/v1/discussions/" + id+ "/comments/create/"
        postbutton.setOnClickListener {


            AndroidNetworking.post(url2)
                .addBodyParameter("comment",comment.toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        commentListDiscussion.clear()
                        val url1: String
                        url1 =
                            "https://api.parenting.ezyschooling.com/api/v1/discussions/comments/?discussion_id="+id

                        AndroidNetworking.initialize(this@DiscussionsAcitvity)
                        AndroidNetworking.get(url1)
                            .build().getAsObject(
                                Comment::class.java,
                                object : ParsedRequestListener<Comment> {
                                    override fun onResponse(response: Comment) {
                                        adapterDiscussion.notifyItemRangeRemoved(0,commentListDiscussion.size-1)

                                        commentListDiscussion.addAll(response.results)
                                        Log.i("commentListSIze",commentListDiscussion.size.toString())
                                        adapterDiscussion.notifyItemRangeInserted(0,commentListDiscussion.size-1)
                                        adapterDiscussion.notifyDataSetChanged()

                                    }


                                    override fun onError(anError: ANError?) {
                                    }

                                }
                            )

                        Toast.makeText(this@DiscussionsAcitvity,"comment created", Toast.LENGTH_LONG).show()

                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                }
                )

        }
    }


    fun getcommentsDiscussion() {

        adapterDiscussion = CommentAdapter(commentListDiscussion)
       val url1: String
        url1 = "https://api.parenting.ezyschooling.com/api/v1/discussions/comments/?discussion_id="+id
        Log.i("disc url",url1)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(url1)
            .build().getAsObject(
                Comment::class.java,
                object : ParsedRequestListener<Comment> {
                    override fun onResponse(response: Comment) {
                        commentListDiscussion.addAll(response.results)
                        adapterDiscussion.notifyDataSetChanged()
                    }


                    override fun onError(anError: ANError?) {
                    }

                }
            )
        discussions_comments_display.layoutManager =
            LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        discussions_comments_display.addItemDecoration(
            DividerItemDecoration(
                this,
                OrientationHelper.HORIZONTAL
            )
        )
        discussions_comments_display.adapter = adapterDiscussion
    }
}
