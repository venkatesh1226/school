package com.ezyschooling.expert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.ImageButton
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.ezyschooling.Discussions.API.Discussion
import com.ezyschooling.R
import com.ezyschooling.SingleArticle.API.Article
import com.ezyschooling.expert.API.ExpertL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.discussions_layout.*
import kotlinx.android.synthetic.main.experts_layout.*

class expertactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.experts_layout)
        val backbutton=findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
        var slug: String
        slug= " "
        val intent: Intent
        intent=getIntent()
        val extras = intent.extras
        if (extras != null) {
            slug = extras.getString("slug").toString()
        }
        Log.i("slug",slug)
        val articleList: MutableList<Article> = mutableListOf()
        val url: String
        url="https://api.parenting.ezyschooling.com/api/v1/experts/"+slug+"/"

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(url)
            .build().getAsObject(
                ExpertL::class.java,
                object : ParsedRequestListener<ExpertL> {
                    override fun onResponse(response: ExpertL) {
                         expert_name_layout.text=response.name
expert_designation_l.text=response.designation
                        Picasso.get().load(response.profilePicture).into(expert_profile_photo)
                        val content=response.bio
                        expert_content.setText(Html.fromHtml(Html.fromHtml(content).toString()))

                    }
                    override fun onError(anError: ANError?) {
                    }

                }
            )
    }
    }

