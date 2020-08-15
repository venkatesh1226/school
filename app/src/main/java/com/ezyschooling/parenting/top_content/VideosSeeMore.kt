package com.ezyschooling.parenting.top_content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.ezyschooling.R
import com.ezyschooling.parenting.top_content.Adapter.TopAdapter
import com.ezyschooling.parenting.top_content.Adapter.TopVideoAdapter
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.TopVideo
import kotlinx.android.synthetic.main.activity_parenting_home.*
import kotlinx.android.synthetic.main.videos_see_more.*

class VideosSeeMore : AppCompatActivity() {
    private val dataListTopVideos: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.Result> = mutableListOf()
    private lateinit var myAdapter5: TopVideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.videos_see_more)
        getVideos()
        val backbutton=findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
    }
    fun getVideos()
    {
        myAdapter5 = TopVideoAdapter(dataListTopVideos)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/")
            .build().getAsObject(
                TopVideo::class.java,
                object : ParsedRequestListener<TopVideo> {
                    override fun onResponse(response: TopVideo) {
                        dataListTopVideos.addAll(response.results)
                        myAdapter5.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        recycler_top_videos.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycler_top_videos.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_top_videos.adapter = myAdapter5
    }
}
