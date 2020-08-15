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
import com.ezyschooling.parenting.top_content.Adapter.TopDiscussionsAdapter
import com.ezyschooling.parenting.top_content.DiscussionsSeeMoreAPI.DiscussionSeeMore
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.ezyschooling.parenting.top_content.SeeMore.API.SeeMore
import kotlinx.android.synthetic.main.activity_discussions_see_more.*
import kotlinx.android.synthetic.main.activity_see_more.*

class DiscussionsSeeMoreActivity : AppCompatActivity() {
    private lateinit var adapter_0_2: TopDiscussionsAdapter
    private lateinit var adapter_2_4: TopDiscussionsAdapter
    private lateinit var adapter_4_6: TopDiscussionsAdapter
    private lateinit var adapter_6_8: TopDiscussionsAdapter
    private lateinit var adapter_8_10: TopDiscussionsAdapter
    private lateinit var adapter_10_12: TopDiscussionsAdapter
    private lateinit var adapter_12_14: TopDiscussionsAdapter
    private lateinit var adapter_14_16: TopDiscussionsAdapter
    private lateinit var adapter_16_18: TopDiscussionsAdapter

    private var list_0_2: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_2_4: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_4_6: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_6_8: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_8_10: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_10_12: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_12_14: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_14_16: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    private var list_16_18: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discussions_see_more)
        val backbutton=findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
        get_0_2()
        get_2_4()
        get_4_6()
        get_6_8()
        get_8_10()
        get_10_12()
        get_12_14()
        get_14_16()
        get_16_18()
    }
    fun get_0_2()
    {
        adapter_0_2 = TopDiscussionsAdapter(list_0_2)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_0_2.addAll(response.x02)
                        adapter_0_2.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_0_2_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_0_2_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_0_2_discuss.adapter = adapter_0_2
    }
    fun get_2_4()
    {
        adapter_2_4 = TopDiscussionsAdapter(list_2_4)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_2_4.addAll(response.x24)
                        adapter_2_4.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_2_4_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_2_4_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_2_4_discuss.adapter = adapter_2_4
    }  fun get_4_6()
    {
        adapter_4_6 = TopDiscussionsAdapter(list_4_6)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_4_6.addAll(response.x46)
                        adapter_4_6.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_4_6_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_4_6_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_4_6_discuss.adapter = adapter_4_6
    }  fun get_6_8()
    {
        adapter_6_8 = TopDiscussionsAdapter(list_6_8)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_6_8.addAll(response.x68)
                        adapter_6_8.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_6_8_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_6_8_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_6_8_discuss.adapter = adapter_6_8
    }  fun get_8_10()
    {
        adapter_8_10 = TopDiscussionsAdapter(list_8_10)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_8_10.addAll(response.x810)
                        adapter_8_10.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_8_10_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_8_10_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_8_10_discuss.adapter = adapter_8_10
    }
    fun get_10_12()
    {
        adapter_10_12 = TopDiscussionsAdapter(list_10_12)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_10_12.addAll(response.x1012)
                        adapter_10_12.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_10_12_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_10_12_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_10_12_discuss.adapter = adapter_10_12
    }  fun get_12_14()
    {
        adapter_12_14= TopDiscussionsAdapter(list_12_14)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_12_14.addAll(response.x1214)
                        adapter_12_14.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_12_14_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_12_14_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_12_14_discuss.adapter = adapter_12_14
    }  fun get_14_16()
    {
        adapter_14_16 = TopDiscussionsAdapter(list_14_16)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_14_16.addAll(response.x1416)
                        adapter_14_16.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_14_16_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_14_16_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_14_16_discuss.adapter = adapter_14_16
    }  fun get_16_18()
    {
        adapter_16_18 = TopDiscussionsAdapter(list_16_18)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/discussions/all-discussions/")
            .build().getAsObject(
                DiscussionSeeMore::class.java,
                object : ParsedRequestListener<DiscussionSeeMore> {
                    override fun onResponse(response: DiscussionSeeMore) {
                        list_16_18.addAll(response.x1618)
                        adapter_16_18.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_16_18_discuss.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_16_18_discuss.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_16_18_discuss.adapter = adapter_16_18
    }
}
