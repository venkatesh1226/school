package com.ezyschooling.parenting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.ezyschooling.Agefilterinterface
import com.ezyschooling.R
import com.ezyschooling.home.HomeActivity
import com.ezyschooling.parenting.top_content.Adapter.*
import com.ezyschooling.parenting.top_content.CoivdTalksSeeMore
import com.ezyschooling.parenting.top_content.DiscussionsSeeMoreActivity
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.AgeFilter.AgeFilters
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.AgeFilter.AgeFiltersItem
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Experts
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.Result
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.Models.expert_articles
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.TopDiscuss
import com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.TopVideo
import com.ezyschooling.parenting.top_content.VideosSeeMore
import kotlinx.android.synthetic.main.activity_parenting_home.*
import kotlinx.android.synthetic.main.layout_movie_item.*
import org.w3c.dom.Text
import java.lang.Character.toUpperCase


class ParentingHomeActivity : AppCompatActivity() {


    /// arraylists to store data obtained from APIs
    private val dataListAgeFilter: MutableList<AgeFiltersItem> = mutableListOf()
    private val dataListFilter: MutableList<AgeFiltersItem> = mutableListOf()
    private val dataList: MutableList<Result> = mutableListOf()
    private val dataListLatest: MutableList<Result> = mutableListOf()
    private val dataListCOVID: MutableList<Result> = mutableListOf()
    private val dataListExpert: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.Result> =
        mutableListOf()
    private val dataListTopDiscussion: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopDiscussions.Result> =
        mutableListOf()
    private val dataListTopVideos: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.Result> =
        mutableListOf()
    private val dataListLatestVideos: MutableList<com.ezyschooling.parenting.top_content.ModelPackage.Experts.TopVideos.Result> =
        mutableListOf()

    /// Adapters
    private lateinit var adapter: ImageAdapter
    private lateinit var adapter1: SubCategoryAdapter
    private lateinit var myAdapter: TopAdapter
    private lateinit var myAdapter1: TopAdapter
    private lateinit var myAdapter2: TopAdapter
    private lateinit var myAdapter3: ExpertAdapter
    private lateinit var myAdapter4: TopDiscussionsAdapter
    private lateinit var myAdapter5: TopVideoAdapter
    private lateinit var myAdapter6: TopVideoAdapter

    lateinit var TopReadsUrl: String
    lateinit var LatestReadsUrl: String
    lateinit var TopVideosUrl: String
    lateinit var LatestVideosUrl: String
    lateinit var TopDiscussionUrl: String
    var slugMain = ""
    var subCategorySlugMain = ""

    lateinit var agefilterinterface: Agefilterinterface

lateinit var sharedPreferences:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parenting_home)

        sharedPreferences = this.getSharedPreferences("com.ezyschooling.parenting", Context.MODE_PRIVATE)

        // changes

        agefilterinterface=object:Agefilterinterface{

            override fun onclick(str: String,mode:Int) {

                if(mode==1){
                    slugMain=str
                }else{

                    subCategorySlugMain=str
                  }
                if(str!=""){
                    Toast.makeText(this@ParentingHomeActivity,
                        "$str filter is applied",Toast.LENGTH_SHORT).show()
                }

                allapi(slugMain,subCategorySlugMain)
            }
        }

        /// function to change color of part of textview
        changecolor(textView3.text as String, 0, 3, textView3)
        changecolor(textView4.text as String, 0, 7, textView4)
  //      changecolor(textView.text as String, 0, 3, textView)
        changecolor(textView5.text as String, 0, 6, textView5)
        changecolor(textView6.text as String, 0, 3, textView6)
        changecolor(textView7.text as String, 0, 3, textView7)
        changecolor(textView8.text as String, 0, 3, textView8)
        changecolor(textView9.text as String, 0, 7, textView9)


        /*
        val slug: String
        val color: Int

        val subCategorySlug: String

        var ifClicked: Int

        ifClicked = 1

        val intent: Intent
        intent = getIntent()

        val extras = intent.extras
        if (extras==null)

        {
            slugMain=="null"
            subCategorySlugMain =="null"
            sharedPreferences.edit().putString("board slug", slugMain).apply()
            sharedPreferences.edit().putString("sub category slug",subCategorySlugMain).apply()
        }

        slugMain = sharedPreferences.getString("board slug", "").toString()

        Log.i("shared pref", slugMain)

        subCategorySlugMain=sharedPreferences.getString("sub category slug","").toString()

        Log.i("sub cat, shared pref",subCategorySlugMain)

        if (extras != null) {

            slug = extras.getString("board slug").toString()

            color = extras.getInt("color")

            if (slug.equals(slugMain)) {

                slugMain = "null"

                Toast.makeText(this, slug + " Filter Removed", Toast.LENGTH_LONG).show()

                Log.i("clicked again", "clicked again")

                sharedPreferences.edit().putString("board slug", slugMain).apply()

            } else if (slug == "null") {

                Log.i("board slug null", slugMain)

            } else {
                Log.i("board slug", slugMain)
                slugMain = slug


            }
            subCategorySlug = extras.getString("subCategory slug").toString()

            if (subCategorySlug.equals(subCategorySlugMain)) {

                Toast.makeText(this,subCategorySlugMain.toUpperCase()+" Filter Removed",Toast.LENGTH_LONG).show()

                subCategorySlugMain = "null"

                sharedPreferences.edit().putString("sub category slug", subCategorySlugMain).apply()

                Log.i("sub clicked", "sub category slug clicked again")

            } else if (subCategorySlug == "null") {

                subCategorySlugMain = subCategorySlug


            } else {
                subCategorySlugMain = subCategorySlug
            }

            if (slugMain != "null") {

                sharedPreferences.edit().putString("board slug", slugMain).apply()

            }
            if (subCategorySlugMain != "null") {

                sharedPreferences.edit().putString("sub category slug", subCategorySlugMain).apply()

            }
        }
        Log.i("board slug", slugMain)
        if (slugMain != "null" && subCategorySlugMain != "null" && subCategorySlugMain!="") {

            TopReadsUrl =
                "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board=" + slugMain + "&sub_category=" + subCategorySlugMain

            Log.i("sub category url", TopReadsUrl)

            Log.i("sub category slug", slugMain)

            Log.i("both not null","bith nit null")

            Toast.makeText(this,slugMain+" "+subCategorySlugMain.toUpperCase()+" Filter Applied",Toast.LENGTH_LONG).show()


            var textDisplay=findViewById<TextView>(R.id.ageDisplay)

            textDisplay.text=slugMain

            var subCategoryDisplay=findViewById<TextView>(R.id.subCategoryFilter)

            subCategoryDisplay.text=subCategorySlugMain.toUpperCase()

            LatestReadsUrl =
                "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board=" + slugMain + "&sub_category=" + subCategorySlugMain

            TopVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?board="+slugMain+"&sub_category="+subCategorySlugMain

            LatestVideosUrl=TopVideosUrl

            TopDiscussionUrl="https://api.parenting.ezyschooling.com/api/v1/discussions/?board="+slugMain+"&sub_category="+subCategorySlugMain

            Log.i("topvideosurl",TopVideosUrl)

            getTopVideos()
            getLatestVideos()
            getTopDiscussions()
            dataList.clear()
            dataListLatest.clear()
            dataListTopVideos.clear()
            dataListLatestVideos.clear()
            Log.i("dataList", dataList.size.toString())
            getTOPReads()
            Log.i("dataListnew", dataList.size.toString())
            getLatestReads()
            myAdapter.notifyDataSetChanged()
            myAdapter1.notifyDataSetChanged()

        } else if (slugMain != "null" && subCategorySlugMain == "null" && slugMain!="") {
            TopReadsUrl =
                "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board=" + slugMain + "&sub_category="

            Log.i("board url age filter", TopReadsUrl)

            Log.i("slug not null,subc null","slug not null,subc null")

            Toast.makeText(this,slugMain+" Filter Applied",Toast.LENGTH_LONG).show()

            var textDisplay=findViewById<TextView>(R.id.ageDisplay)

            textDisplay.text=slugMain

            LatestReadsUrl =
                "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board=" + slugMain + "&sub_category="

            TopVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?board="+slugMain+"&sub_category="

            LatestVideosUrl=TopVideosUrl

            TopDiscussionUrl="https://api.parenting.ezyschooling.com/api/v1/discussions/?board="+slugMain+"&sub_category="

            Log.i("topvideosurl",TopVideosUrl)

            getTopDiscussions()

            dataList.clear()

            dataListLatest.clear()

            dataListTopVideos.clear()

            dataListLatestVideos.clear()

            getTOPReads()
            getLatestReads()
            getTopVideos()
            getLatestVideos()

        } else if (slugMain == "null" && subCategorySlugMain != "null" && subCategorySlugMain!="") {

            TopReadsUrl =
                "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board=&sub_category=" + subCategorySlugMain

            Log.i("subcate,slug main null", TopReadsUrl)

            LatestReadsUrl = TopReadsUrl

            Toast.makeText(this,subCategorySlugMain+" Filter Applied",Toast.LENGTH_LONG).show()

            TopVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?board=&sub_category="+subCategorySlugMain

            Log.i("topvideosurl",TopVideosUrl)

            TopDiscussionUrl="https://api.parenting.ezyschooling.com/api/v1/discussions/?board=&sub_category="+subCategorySlugMain

            var subCategoryDisplay=findViewById<TextView>(R.id.subCategoryFilter)

            subCategoryDisplay.text=subCategorySlugMain.toUpperCase()

            LatestVideosUrl=TopVideosUrl

            getTOPReads()
            getLatestReads()
            getTOPReads()
            getLatestReads()
            getTopDiscussions()

            slugMain = "null"
            subCategorySlugMain = "null"
            sharedPreferences.edit().putString("board slug", slugMain).apply()


        }
        else {

            TopReadsUrl = "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?ordering=-views_count,-likes_count,-comment_count&limit=7"

            LatestReadsUrl = "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?limit=6&ordering=-timestamp"

            Log.i("both null", TopReadsUrl)

            TopVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?limit=7&ordering=-views,-likes_count,-comment_count"

            LatestVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?limit=6&ordering=-timestamp"

            TopDiscussionUrl="https://api.parenting.ezyschooling.com/api/v1/discussions/?limit=6&ordering=-views,-likes_count,-comment_count"

            getTOPReads()
            getLatestReads()
            getTopVideos()
            getLatestVideos()
            getTopDiscussions()

        }
*/
        /// calling functions to fetch data from APIS and load recylerviews

        TopReadsUrl = "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board="+slugMain+"&sub_category="+subCategorySlugMain+"&limit=7&ordering=" +
        "-views_count,-likes_count,-comment_count"

        LatestReadsUrl = "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board="+slugMain+"&sub_category="+subCategorySlugMain+"&limit=7&ordering=" +
                "-timestamp"

        TopVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?board="+slugMain+"&sub_category="+subCategorySlugMain+"&limit=7"

        LatestVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?board="+slugMain+"&sub_category="+subCategorySlugMain+"&limit=7"

        TopDiscussionUrl="https://api.parenting.ezyschooling.com/api/v1/discussions/?board="+slugMain+"&sub_category="+subCategorySlugMain+"&limit=7"

        getTOPReads()
        getLatestReads()
        getTopVideos()
        getLatestVideos()
        getTopDiscussions()
        getAgeFilters()
        getFilters()
        getCOVIDConversations()
        getExperts()

        val backbutton=findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            slugMain = "null"
            subCategorySlugMain = "null"
            sharedPreferences.edit().putString("board slug", slugMain).apply()
            sharedPreferences.edit().putString("sub category slug",subCategorySlugMain)
            val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)}

        val topReadsSeeMoreButton=findViewById<Button>(R.id.TopReadsSeeMore)

        topReadsSeeMoreButton.setOnClickListener {

            val intent=Intent(this,SeeMoreActivity::class.java)
            startActivity(intent)
        }

        val latestReadsSeeMoreButton=findViewById<Button>(R.id.LatestReadsSeeMore)

        latestReadsSeeMoreButton.setOnClickListener {
            val intent=Intent(this,SeeMoreActivity::class.java)
           startActivity(intent)
       }

        val CovidTalksSeeMoreButton = findViewById<Button>(R.id.Covid_talks_SeeMore_button)

        CovidTalksSeeMoreButton.setOnClickListener {

            val intent=Intent(this,CoivdTalksSeeMore::class.java)
            startActivity(intent)

        }

        val TopVideoSeeMoreButton = findViewById<Button>(R.id.Top_videos_SeeMore_button)

        TopVideoSeeMoreButton.setOnClickListener {

            val intent=Intent(this,VideosSeeMore::class.java)
            startActivity(intent)

        }
        val LatestVideosSeeMoreButton = findViewById<Button>(R.id.Latest_videos_SeeMore_button)

        LatestVideosSeeMoreButton.setOnClickListener {

            val intent=Intent(this,VideosSeeMore::class.java)
            startActivity(intent)
        }

        val DiscussionsSeeMoreButton = findViewById<Button>(R.id.Discussions_SeeMore_button)

        DiscussionsSeeMoreButton.setOnClickListener {

            val intent=Intent(this,DiscussionsSeeMoreActivity::class.java)
            startActivity(intent)
        }

    }

    fun allapi(age:String,category:String){

        TopReadsUrl = "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board="+age+"&sub_category="+category+"&limit=7&ordering=" +
                "-views_count,-likes_count,-comment_count"

        LatestReadsUrl = "https://api.parenting.ezyschooling.com/api/v1/articles/expert-articles/?board="+age+"&sub_category="+category+"&limit=7&ordering=" +
                "-timestamp"

        TopVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?board="+age+"&sub_category="+category+"&limit=7"

        LatestVideosUrl="https://api.parenting.ezyschooling.com/api/v1/videos/expert-videos/?board="+age+"&sub_category="+category+"&limit=7"

        TopDiscussionUrl="https://api.parenting.ezyschooling.com/api/v1/discussions/?board="+age+"&sub_category="+category+"&limit=7"

        dataList.clear()

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(TopReadsUrl)
            .build().getAsObject(
                expert_articles::class.java,
                object : ParsedRequestListener<expert_articles> {
                    override fun onResponse(response: expert_articles) {
                        dataList.addAll(response.results)
                        myAdapter.notifyDataSetChanged()
                        println("new datalist is $dataList")
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

        dataListLatest.clear()

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(LatestReadsUrl)
            .build().getAsObject(
                expert_articles::class.java,
                object : ParsedRequestListener<expert_articles> {
                    override fun onResponse(response: expert_articles) {
                        dataListLatest.addAll(response.results)
                        myAdapter1.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

        dataListTopVideos.clear()

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(TopVideosUrl)
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

        dataListLatestVideos.clear()

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(LatestVideosUrl)
            .build().getAsObject(
                TopVideo::class.java,
                object : ParsedRequestListener<TopVideo> {
                    override fun onResponse(response: TopVideo) {
                        dataListLatestVideos.addAll(response.results)
                        myAdapter6.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

        dataListTopDiscussion.clear()

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(TopDiscussionUrl)
            .build().getAsObject(
                TopDiscuss::class.java,
                object : ParsedRequestListener<TopDiscuss> {
                    override fun onResponse(response: TopDiscuss) {
                        dataListTopDiscussion.addAll(response.results)
                        myAdapter4.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
    }


   /* override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("slug main", slugMain)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(applicationContext, "onRestoreInstanceState", Toast.LENGTH_SHORT).show()
        slugMain = savedInstanceState.getString("slug main").toString()
        Log.i("restore",slugMain)
    }*/
    /// genrating function to change color of part of textview

    fun changecolor(s: String, start: Int, end: Int , tView:TextView)
    {
        val mText=s
        val mSpannableString=SpannableString(mText)
        val mTop= ForegroundColorSpan(Color.RED)
        mSpannableString.setSpan(mTop,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tView.text=mSpannableString
    }
    /// function to get age filter and filter photos and text

    fun getAgeFilters()
    {
        adapter = ImageAdapter(dataListAgeFilter,agefilterinterface)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/categories/boards/")
            .build().getAsObject(
               AgeFilters::class.java,
                object : ParsedRequestListener<AgeFilters> {
                    override fun onResponse(response: AgeFilters) {
                        dataListAgeFilter.addAll(response)
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

     //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview

        recyclerMovieList.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
 //       recyclerMovieList.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerMovieList.adapter = adapter
    }
    fun getFilters()
    {
        adapter1 = SubCategoryAdapter(dataListFilter,agefilterinterface)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/categories/sub-category/")
            .build().getAsObject(
                AgeFilters::class.java,
                object : ParsedRequestListener<AgeFilters> {
                    override fun onResponse(response: AgeFilters) {
                        dataListFilter.addAll(response)
                        adapter1.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

        //   Log.i("Age Filter",dataListAgeFilter[0].name)
        /// loading data in Top Reads recyclerview

        recyclerview1.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
   //     recyclerview1.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview1.adapter = adapter1
    }
    /// function to get files of Top Reads Section and load in recycler view
    fun getTOPReads() {

        myAdapter = TopAdapter(dataList)


        AndroidNetworking.initialize(this)
        AndroidNetworking.get(TopReadsUrl)
            .build().getAsObject(
                expert_articles::class.java,
                object : ParsedRequestListener<expert_articles> {
                    override fun onResponse(response: expert_articles) {
                        println("response  is $response")
                        dataList.addAll(response.results)
                        myAdapter.notifyDataSetChanged()
                        println("datalist is $dataList")
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

        /// loading data in Top Reads recyclerview

        recyclerview2.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
     //   recyclerview2.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview2.adapter = myAdapter
    }



    /// function to get files of Latest Reads and load in recycler view
    fun getLatestReads()
    {        myAdapter1 = TopAdapter(dataListLatest)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(LatestReadsUrl)
            .build().getAsObject(
                expert_articles::class.java,
                object : ParsedRequestListener<expert_articles> {
                    override fun onResponse(response: expert_articles) {
                        dataListLatest.addAll(response.results)
                        myAdapter1.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

        /// loading data in Latest Reads recyclerview

        recyclerview3.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
       // recyclerview3.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview3.adapter = myAdapter1
    }
    fun getCOVIDConversations(){

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

        recyclerview4.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
       // recyclerview4.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview4.adapter = myAdapter2

    }
    fun getTopVideos()
    {
        myAdapter5 = TopVideoAdapter(dataListTopVideos)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(TopVideosUrl)
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
        recyclerview7.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
      //  recyclerview7.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview7.adapter = myAdapter5
    }

    fun getLatestVideos()
    {
        myAdapter6 = TopVideoAdapter(dataListLatestVideos)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(LatestVideosUrl)
            .build().getAsObject(
                TopVideo::class.java,
                object : ParsedRequestListener<TopVideo> {
                    override fun onResponse(response: TopVideo) {
                        dataListLatestVideos.addAll(response.results)
                        myAdapter6.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        recyclerview8.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
      //  recyclerview8.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview8.adapter = myAdapter6
    }
    fun getTopDiscussions() {
        myAdapter4 = TopDiscussionsAdapter(dataListTopDiscussion)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(TopDiscussionUrl)
            .build().getAsObject(
                TopDiscuss::class.java,
                object : ParsedRequestListener<TopDiscuss> {
                    override fun onResponse(response: TopDiscuss) {
                        dataListTopDiscussion.addAll(response.results)
                        myAdapter4.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        recyclerview6.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
       // recyclerview6.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview6.adapter = myAdapter4
    }
    fun getExperts()
    {
        myAdapter3= ExpertAdapter(dataListExpert)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/experts/")
            .build().getAsObject(
                Experts::class.java,
                object : ParsedRequestListener<Experts> {
                    override fun onResponse(response: Experts) {
                        dataListExpert.addAll(response.results)
                        myAdapter3.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        recyclerview5.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
       // recyclerview5.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        recyclerview5.adapter = myAdapter3
    }

    override fun onBackPressed() {
        slugMain = "null"
        subCategorySlugMain = ""
        sharedPreferences.edit().putString("board slug", slugMain).apply()
        val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }

    /// on item click listenter fucntion

}

/// fetching json
       /* internal inner class downloadtask : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void?): String {
                val client = OkHttpClient()
                val url = "https://api.parenting.ezyschooling.com/api/v1/categories/boards/"
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                val result = response.body?.string().toString()
                val arr = JSONArray(result)
                for (i in 0..arr.length() - 1) {
                    val jsonpart: JSONObject = arr.getJSONObject(i)

                    val name = jsonpart.getString("name")
                    val thumbnail = jsonpart.getString("thumbnail")
                    Log.i("namess", name)
                    Log.i("thumbnnai", thumbnail)
                    imageslist.add(thumbnail)

                }
                return result
            }

        }

/// function to load list of images in recycler views
        private fun showImages(images: List<String>) {
            val recyclerView = findViewById<RecyclerView>(R.id.imagesRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL,false)
            recyclerView.adapter = ImageAdapter(imageslist)
            val recyclerView1 = findViewById<RecyclerView>(R.id.imagesRecyclerView1)
            recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
            recyclerView1.adapter = ImageAdapter(imageslist)

            val recyclerView2 = findViewById<RecyclerView>(R.id.imagesRecyclerView2)

            recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

            recyclerView2.adapter = ImageAdapter1(this, imageslist)

        }

    }*/
// next 4 lines populate the top read item data fields
/* val topReadsList:List<topReadsItem> =generateReadsitem(20)
rvTopReadItem.adapter =topReadsItemAdapter(topReadsList)
rvTopReadItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
rvTopReadItem.setHasFixedSize(true)


//Declaring boolean vars for age
var boolAge0to2: Boolean
var boolAge2to4: Boolean
var boolAge4to6: Boolean
var boolAge6to8: Boolean
var boolAge8to10: Boolean
var boolAge10to12: Boolean
var boolAge12to14: Boolean
var boolAge14to16: Boolean
var boolAge16to18: Boolean

//Declaring boolean vars for Filters
var boolBody: Boolean
var boolMind: Boolean
var boolSociety: Boolean
var boolEducation: Boolean
var boolFamily: Boolean

//Declaring LinearLayouts
val llAge0to2: LinearLayout = findViewById(R.id.age0to2)
val llAge2to4: LinearLayout = findViewById(R.id.age2to4)
val llAge4to6: LinearLayout = findViewById(R.id.age4to6)
val llAge6to8: LinearLayout = findViewById(R.id.age6to8)
val llAge8to10: LinearLayout = findViewById(R.id.age8to10)
val llAge10to12: LinearLayout = findViewById(R.id.age10to12)
val llAge12to14: LinearLayout = findViewById(R.id.age12to14)
val llAge14to16: LinearLayout = findViewById(R.id.age14to16)
val llAge16to18: LinearLayout = findViewById(R.id.age16to18)
val llBody: LinearLayout = findViewById(R.id.LLBody)
val llMind: LinearLayout = findViewById(R.id.LLMind)
val llSociety: LinearLayout = findViewById(R.id.LLSociety)
val llEducation: LinearLayout = findViewById(R.id.LLEducation)
val llFamily: LinearLayout = findViewById(R.id.LLFamily)


llAge0to2.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked : 0-2", Toast.LENGTH_SHORT)
        .show()
    boolAge0to2 = true
})

llAge2to4.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 2-4", Toast.LENGTH_SHORT)
        .show()
    boolAge2to4 = true
})
llAge4to6.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 4-6", Toast.LENGTH_SHORT)
        .show()
    boolAge4to6 = true
})
llAge6to8.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 6-8", Toast.LENGTH_SHORT)
        .show()
    boolAge6to8 = true
})

llAge8to10.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 8-10", Toast.LENGTH_SHORT)
        .show()
    boolAge8to10 = true
})

llAge10to12.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 10-12", Toast.LENGTH_SHORT)
        .show()
    boolAge10to12 = true
})

llAge12to14.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 12-14", Toast.LENGTH_SHORT)
        .show()
    boolAge12to14 = true
})

llAge14to16.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 14-16", Toast.LENGTH_SHORT)
        .show()
    boolAge14to16 = true
})

llAge16to18.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked: 16-18", Toast.LENGTH_SHORT)
        .show()
    boolAge16to18 = true
})

llBody.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked : Body", Toast.LENGTH_SHORT)
        .show()
    boolBody = true
})

llMind.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked : Mind", Toast.LENGTH_SHORT)
        .show()
    boolMind = true
})

llSociety.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked : Society", Toast.LENGTH_SHORT)
        .show()
    boolSociety = true
})

llEducation.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(
        this@ParentingHomeActivity,
        "You clicked : Education",
        Toast.LENGTH_SHORT
    ).show()
    boolEducation = true
})

llFamily.setOnClickListener(View.OnClickListener { view ->
    Toast.makeText(this@ParentingHomeActivity, "You clicked : Family", Toast.LENGTH_SHORT)
        .show()
    boolFamily = true
})
}

// This function generates the sample data for the Reads Item Recycler View

private fun generateReadsitem(size: Int): List<topReadsItem> {
val list = ArrayList<topReadsItem>()
for (i in 0 until size) {
    val drawable: Int = when (i % 3) {
        0 -> R.drawable.body
        1 -> R.drawable.family
        else -> R.drawable.mind
    }
    val item = topReadsItem(drawable, "This is Item $i")
    list += item
}
return list
}*/
