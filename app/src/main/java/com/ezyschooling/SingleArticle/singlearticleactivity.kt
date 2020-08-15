package com.ezyschooling.SingleArticle

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.webkit.WebView
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.ezyschooling.LogIn.models.LoginAcitivity
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import com.ezyschooling.SingleArticle.API.Article
import com.ezyschooling.parenting.top_content.Adapter.CommentAdapter
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Comment
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.CommentApi.Result
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_article_layout.*
import org.json.JSONObject


class singlearticleactivity : AppCompatActivity() {


    private lateinit var adapter: CommentAdapter
    private var commentList: MutableList<Result> = mutableListOf()
    private lateinit var article_id: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_article_layout)
        val commentbox = findViewById<EditText>(R.id.comment_box)
        commentbox.setOnTouchListener(OnTouchListener { v, event ->
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

        val backbutton = findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
        var slug: String

        var likecount:String
        var commentcount: String
        likecount = " "
        commentcount = " "
        article_id = ""
        slug = " "
        val intent: Intent
        intent = getIntent()
        val extras = intent.extras
        if (extras != null) {
            slug = extras.getString("slug").toString()
            article_id = extras.getInt("id").toString()
            likecount=extras.getString("likes").toString()
            commentcount = extras.getString("commentcount").toString()
Log.i("likes i",likecount)
            Log.i("comments",commentcount)

        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val likebutton = bottomNavigationView.menu.findItem(R.id.like_button)
        likebutton.title = likecount
        Log.i("like", likecount)
        val comment_button = bottomNavigationView.menu.findItem(R.id.comment_button)
        comment_button.title = commentcount
        Log.i("comment", commentcount)

        Log.i("slug", slug)
        Log.i("id", article_id)

        val articleList: MutableList<Article> = mutableListOf()
        val url: String
        url = "https://api.parenting.ezyschooling.com/api/v1/articles/" + slug + "/"



        AndroidNetworking.initialize(this)
        AndroidNetworking.get(url)
            .build().getAsObject(
                Article::class.java,
                object : ParsedRequestListener<Article> {
                    override fun onResponse(response: Article) {
                        if (response.createdBy.profilePicture != null) {
                            Picasso.get().load(response.createdBy.profilePicture)
                                .into(profile_photo)
                        }
                        writer_name.text = response.createdBy.name
                        article_time.text = response.timestamp
                        article_title.text = response.title
                        Picasso.get().load(response.subCategory.thumbnail).into(sub_category_photo)
                        sub_category_name.text = response.subCategory.name
                        Picasso.get().load(response.board.thumbnail).into(board_photo)
                        board_name.text = response.board.name
                        Picasso.get().load(response.thumbnail).into(article_photo)
                        val content = response.description
                        val htmlAsString = (Html.fromHtml(Html.fromHtml(content).toString()))
                        val webView = findViewById(R.id.article_content) as WebView
                        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                    }


                    override fun onError(anError: ANError?) {

                    }

                }
            )

        getcomments()

        val commentContent = findViewById<EditText>(R.id.comment_box)
        val comment = commentContent.text


        val postbutton = findViewById<Button>(R.id.postcomment)
        val url2 = "https://api.parenting.ezyschooling.com/api/v1/articles/" + article_id + "/comments/create/"

        postbutton.setOnClickListener {

            if (SharedprefManager.getInstance(this)?.isLoggedin!!) {

                if(!comment.isEmpty()){
                    AndroidNetworking.post(url2)
                        .addBodyParameter("comment", comment.toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(object : JSONObjectRequestListener {
                            override fun onResponse(response: JSONObject) {
                                commentList.clear()
                                val url1: String
                                url1 =
                                    "https://api.parenting.ezyschooling.com/api/v1/articles/comments/?article_id=" + article_id

                                AndroidNetworking.initialize(this@singlearticleactivity)
                                AndroidNetworking.get(url1)
                                    .build().getAsObject(
                                        Comment::class.java,
                                        object : ParsedRequestListener<Comment> {
                                            override fun onResponse(response: Comment) {
                                                adapter.notifyItemRangeRemoved(0, commentList.size - 1)

                                                commentList.addAll(response.results)
                                                Log.i("commentListSIze", commentList.size.toString())
                                                adapter.notifyItemRangeInserted(0, commentList.size - 1)
                                                adapter.notifyDataSetChanged()

                                            }

                                            override fun onError(anError: ANError?) {
                                            }

                                        }
                                    )

                                Toast.makeText(
                                    this@singlearticleactivity,
                                    "comment has been posted",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            override fun onError(anError: ANError?) {
                                TODO("Not yet implemented")
                            }
                        }
                        )
                    commentList.clear()

                }else{
                    Toast.makeText(applicationContext, "enter a comment to post", Toast.LENGTH_SHORT)
                        .show()
                }


            } else {
                alert()
            }

        }
    }


    fun alert() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Login required")
        //set message for alert dialog
        builder.setMessage("you must log in to post comments")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Go to Login"){dialogInterface, which ->
            val intent=Intent(this, LoginAcitivity::class.java)
            startActivity(intent)
        }

        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }


    fun getcomments() {

                adapter = CommentAdapter(commentList)
                val url1: String
                url1 = "https://api.parenting.ezyschooling.com/api/v1/articles/comments/?article_id=" + article_id

                AndroidNetworking.initialize(this)
                AndroidNetworking.get(url1)
                    .build().getAsObject(
                        Comment::class.java,
                        object : ParsedRequestListener<Comment> {
                            override fun onResponse(response: Comment) {
                                commentList.addAll(response.results)
                            }


                            override fun onError(anError: ANError?) {
                            }

                        }
                    )
                comment_recyclerview.layoutManager =
                    LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                comment_recyclerview.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        OrientationHelper.HORIZONTAL
                    )
                )
                comment_recyclerview.adapter = adapter
            }


        }






