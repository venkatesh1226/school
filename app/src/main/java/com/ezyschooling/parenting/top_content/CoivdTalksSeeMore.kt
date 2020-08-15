package com.ezyschooling.parenting.top_content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.expert_articles
import kotlinx.android.synthetic.main.activity_parenting_home.*
import kotlinx.android.synthetic.main.covid_talks_see_more.*

class CoivdTalksSeeMore : AppCompatActivity() {
    private val dataListCOVID: MutableList<Result> = mutableListOf()
    private lateinit var myAdapter2: TopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.covid_talks_see_more)
        getCOVIDTalksSeeMore()
        val backbutton=findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
    }
    fun getCOVIDTalksSeeMore() {
        myAdapter2 = TopAdapter(dataListCOVID)



            AndroidNetworking.initialize(this)
            AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?tags=covid-19_1,covid-19,covid19&ordering=-timestamp")
                .build().getAsObject(
                    expert_articles::class.java,
                    object : ParsedRequestListener<expert_articles> {
                        override fun onResponse(response: expert_articles) {
                            dataListCOVID.addAll(response.results)
                            myAdapter2.notifyDataSetChanged()
                        }

                        override fun onError(anError: ANError?) {
                        }

                    }
                )

/// loading data in Latest Reads recyclerview

            recycler_covid_see_more.layoutManager =
                LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            recycler_covid_see_more.addItemDecoration(
                DividerItemDecoration(
                    this,
                    OrientationHelper.VERTICAL
                )
            )
            recycler_covid_see_more.adapter = myAdapter2
        }
    }

