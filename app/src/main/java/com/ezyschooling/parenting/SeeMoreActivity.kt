package com.ezyschooling.parenting

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
import com.ezyschooling.parenting.top_content.Adapter.ImageAdapter
import com.ezyschooling.parenting.top_content.Adapter.TopAdapter
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.AgeFilter.AgeFilters
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.ezyschooling.parenting.top_content.SeeMore.API.SeeMore
import com.ezyschooling.parenting.top_content.SeeMore.API.X02
import kotlinx.android.synthetic.main.activity_parenting_home.*
import kotlinx.android.synthetic.main.activity_see_more.*

class SeeMoreActivity : AppCompatActivity() {
    private lateinit var adapter_0_2: TopAdapter
    private lateinit var adapter_2_4: TopAdapter
    private lateinit var adapter_4_6: TopAdapter
    private lateinit var adapter_6_8: TopAdapter
    private lateinit var adapter_8_10: TopAdapter
    private lateinit var adapter_10_12: TopAdapter
    private lateinit var adapter_12_14: TopAdapter
    private lateinit var adapter_14_16: TopAdapter
    private lateinit var adapter_16_18: TopAdapter

    private var list_0_2: MutableList<Result> = mutableListOf()
    private var list_2_4: MutableList<Result> = mutableListOf()
    private var list_4_6: MutableList<Result> = mutableListOf()
    private var list_6_8: MutableList<Result> = mutableListOf()
    private var list_8_10: MutableList<Result> = mutableListOf()
    private var list_10_12: MutableList<Result> = mutableListOf()
    private var list_12_14: MutableList<Result> = mutableListOf()
    private var list_14_16: MutableList<Result> = mutableListOf()
    private var list_16_18: MutableList<Result> = mutableListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_more)
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
        adapter_0_2 = TopAdapter(list_0_2)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
               SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
list_0_2.addAll(response.x02)
                        adapter_0_2.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_0_2.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_0_2.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_0_2.adapter = adapter_0_2
    }
    fun get_2_4()
    {
        adapter_2_4 = TopAdapter(list_2_4)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_2_4.addAll(response.x24)
                        adapter_2_4.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_2_4.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_2_4.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_2_4.adapter = adapter_2_4
    }  fun get_4_6()
    {
        adapter_4_6 = TopAdapter(list_4_6)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_4_6.addAll(response.x46)
                        adapter_4_6.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_4_6.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_4_6.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_4_6.adapter = adapter_4_6
    }  fun get_6_8()
    {
        adapter_6_8 = TopAdapter(list_6_8)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_6_8.addAll(response.x68)
                        adapter_6_8.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_6_8.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_6_8.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_6_8.adapter = adapter_6_8
    }  fun get_8_10()
    {
        adapter_8_10 = TopAdapter(list_8_10)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_8_10.addAll(response.x810)
                        adapter_8_10.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_8_10.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_8_10.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_8_10.adapter = adapter_8_10
    }
    fun get_10_12()
    {
        adapter_10_12 = TopAdapter(list_10_12)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_10_12.addAll(response.x1012)
                        adapter_10_12.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_10_12.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_10_12.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_10_12.adapter = adapter_10_12
    }  fun get_12_14()
    {
        adapter_12_14= TopAdapter(list_12_14)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_12_14.addAll(response.x1214)
                        adapter_12_14.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_12_14.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_12_14.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_12_14.adapter = adapter_12_14
    }  fun get_14_16()
    {
        adapter_14_16 = TopAdapter(list_14_16)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_14_16.addAll(response.x1416)
                        adapter_14_16.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_14_16.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_14_16.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_14_16.adapter = adapter_14_16
    }  fun get_16_18()
    {
        adapter_16_18 = TopAdapter(list_16_18)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/articles/all-articles/")
            .build().getAsObject(
                SeeMore::class.java,
                object : ParsedRequestListener<SeeMore> {
                    override fun onResponse(response: SeeMore) {
                        list_16_18.addAll(response.x1618)
                        adapter_16_18.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        recyclerview_16_18.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerview_16_18.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview_16_18.adapter = adapter_16_18
    }
}
