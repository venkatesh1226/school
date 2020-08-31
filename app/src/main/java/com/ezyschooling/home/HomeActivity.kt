package com.ezyschooling.home

import android.Manifest.permission.CAMERA
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.dorna.ui.base.BaseActivity
import com.ezyschooling.LogIn.models.LoginAcitivity
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import com.ezyschooling.aboutUs.aboutUsActivity
import com.ezyschooling.contactUs.contactUsActivity
import com.ezyschooling.databinding.ActivityHomeBinding
import com.ezyschooling.home.API.InTheNews.InTheNews
import com.ezyschooling.home.API.LiveSessions
import com.ezyschooling.home.API.Quizzes.OurQuizzes
import com.ezyschooling.home.API.Result
import com.ezyschooling.home.Adapter.InTheNewsAdapter
import com.ezyschooling.home.Adapter.LiveSessionAdapter
import com.ezyschooling.home.Adapter.OurQuizAdapter
import com.ezyschooling.parenting.ParentingHomeActivity
import com.ezyschooling.profile.EditProfile
import com.ezyschooling.signup.SignUpActivity
import com.ezyschooling.profile.viewProfile
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*
import java.lang.reflect.Method



class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var dashboardBinding: ActivityHomeBinding
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    private lateinit var adapter: LiveSessionAdapter
    private lateinit var quizAdapter: OurQuizAdapter
    private lateinit var NewsAdapter: InTheNewsAdapter
    private val dataList: MutableList<Result> = mutableListOf()
    private val quizlist: MutableList<com.ezyschooling.home.API.Quizzes.Result> = mutableListOf()
    private val newslist: MutableList<com.ezyschooling.home.API.InTheNews.Result> = mutableListOf()


    fun changecolor(s: String, start: Int, end: Int , tView: TextView)
    {
        val mText=s
        val mSpannableString= SpannableString(mText)
        val mTop= ForegroundColorSpan(Color.RED)
        mSpannableString.setSpan(mTop,start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tView.text=mSpannableString
    }

    fun getLiveSessions()
    {
        adapter = LiveSessionAdapter(dataList)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/miscs/online-events/")
            .build().getAsObject(
                LiveSessions::class.java,
                object : ParsedRequestListener<LiveSessions> {
                    override fun onResponse(response: LiveSessions) {
                        dataList.addAll(response.results)
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )

        live_sessionsview.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        live_sessionsview.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        live_sessionsview.adapter = adapter
    }

    fun getNews()
    {
        NewsAdapter = InTheNewsAdapter(newslist)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/miscs/ezyschooling-news-articles/")
            .build().getAsObject(
               InTheNews::class.java,
                object : ParsedRequestListener<InTheNews> {
                    override fun onResponse(response: InTheNews) {
                        newslist.addAll(response.results)
                        NewsAdapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        in_news__view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        in_news__view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        in_news__view.adapter = NewsAdapter
    }
    fun getQuizzes()
    {
        quizAdapter = OurQuizAdapter(quizlist)

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://api.parenting.ezyschooling.com/api/v1/quiz/quiz-category/")
            .build().getAsObject(
                OurQuizzes::class.java,
                object : ParsedRequestListener<OurQuizzes> {
                    override fun onResponse(response: OurQuizzes) {
                        quizlist.addAll(response.results)
                        quizAdapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                    }

                }
            )
        //   Log.i("Age Filter",dataListAgeFilter[0].name)
/// loading data in Top Reads recyclerview
        quiz_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        quiz_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.HORIZONTAL))
        quiz_view.adapter = quizAdapter
    }
    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
        super.onBackPressed()
    }
    override fun initView() {


        dashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val parentingbutton=findViewById<CardView>(R.id.cardviewparenting)
        parentingbutton.setOnClickListener {
            val intent = Intent(this, ParentingHomeActivity::class.java)
            ContextCompat.startActivity(this, intent, Bundle())
        }

        getLiveSessions()
        getQuizzes()
        getNews()

        changecolor(our_services.text.toString(),0,3,our_services)
        changecolor(online_initiatives.text.toString(),0,7,online_initiatives)
        changecolor(live_sessions.text.toString(),0,5,live_sessions)
        changecolor(our_quizzes.text.toString(),0,3,our_quizzes)
        changecolor(in_the_news.text.toString(),0,7,in_the_news)
        changecolor(our_journey.text.toString(),0,3,our_journey)
        changecolor(ezy.text.toString(),3,12,ezy)


        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        if(SharedprefManager.getInstance(this)?.isLoggedin!!){
            val logout = findViewById<Button>(R.id.logout)
            logout.setVisibility(View.VISIBLE)

            val profile = findViewById<Button>(R.id.profile)
            profile.setVisibility(View.VISIBLE)


            val button = findViewById<Button>(R.id.login)
            button.setVisibility(View.GONE)

            val button1 = findViewById<Button>(R.id.signup)
            button1.setVisibility(View.GONE)
        }


        val button = findViewById<Button>(R.id.login)
        button.setOnClickListener {

            drawerLayout.closeDrawer(GravityCompat.START)

            val intent = Intent(this, LoginAcitivity::class.java)
            startActivity(intent)

        }
        val button1 = findViewById<Button>(R.id.signup)
        button1.setOnClickListener {

            drawerLayout.closeDrawer(GravityCompat.START)

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }

        val button2 = findViewById<Button>(R.id.admissions)
        button2.setOnClickListener {
            val popupMenu2: PopupMenu = PopupMenu(this, button2)
            popupMenu2.menuInflater.inflate(R.menu.popup_admissions_menu, popupMenu2.menu)

            val method: Method = popupMenu2.getMenu().javaClass.getDeclaredMethod(
                "setOptionalIconsVisible",
                Boolean::class.javaPrimitiveType
            )
            method.setAccessible(true)
            method.invoke(popupMenu2.getMenu(), true)

            popupMenu2.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->


                when (item.itemId) {
                    R.id.action_admissions1 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    R.id.action_admissions2 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    R.id.action_admissions3 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    R.id.action_admissions4 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()

                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            })
            popupMenu2.show()
        }

        val button3 = findViewById<Button>(R.id.parenting)
        button3.setOnClickListener {
            val popupMenu3: PopupMenu = PopupMenu(this, button3)
            popupMenu3.menuInflater.inflate(R.menu.popup_parenting_menu, popupMenu3.menu)

            val method: Method = popupMenu3.getMenu().javaClass.getDeclaredMethod(
                "setOptionalIconsVisible",
                Boolean::class.javaPrimitiveType
            )
            method.setAccessible(true)
            method.invoke(popupMenu3.getMenu(), true)

            popupMenu3.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

                when (item.itemId) {
                    R.id.action_parenting1 -> {

                        val intent = Intent(this, ParentingHomeActivity::class.java)
                        startActivity(intent)

                    }
                    R.id.action_parenting2 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    R.id.action_parenting3 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    R.id.action_parenting4 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    R.id.action_parenting5 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            })
            popupMenu3.show()
        }

        val button4 = findViewById<Button>(R.id.see_more)
        button4.setOnClickListener {
            val popupMenu4: PopupMenu = PopupMenu(this, button4)
            popupMenu4.menuInflater.inflate(R.menu.popup_see_more_menu, popupMenu4.menu)

            val method: Method = popupMenu4.getMenu().javaClass.getDeclaredMethod(
                "setOptionalIconsVisible",
                Boolean::class.javaPrimitiveType
            )
            method.setAccessible(true)
            method.invoke(popupMenu4.getMenu(), true)

            popupMenu4.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_see_more1 ->
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    R.id.action_see_more2 -> {
                        val intent = Intent(this, contactUsActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    R.id.action_see_more3 -> {
                        val intent = Intent(this, aboutUsActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            this@HomeActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            })
            popupMenu4.show()
        }

        val profile = findViewById<Button>(R.id.profile)
        profile.setOnClickListener {

            val popupMenu: PopupMenu = PopupMenu(this, button)
            popupMenu.menuInflater.inflate(R.menu.popup_profile, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.view_profile ->{
                        val intent = Intent(this, viewProfile::class.java)

                        startActivity(intent)
                    }

                    R.id.edit_profile ->{
                        val intent = Intent(this, EditProfile::class.java)

                        startActivity(intent)

                    Toast.makeText(this@HomeActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                     }

                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            })
            popupMenu.show()
        }


        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {

            if(SharedprefManager.getInstance(this)?.isLoggedin!!){
                SharedprefManager.getInstance(this)?.clear()
                Toast.makeText(applicationContext, "successfully logged out", Toast.LENGTH_SHORT)
                    .show()

                val button = findViewById<Button>(R.id.login)
                button.setVisibility(View.VISIBLE)

                val button2 = findViewById<Button>(R.id.profile)
                button2.setVisibility(View.GONE)


                val button1 = findViewById<Button>(R.id.signup)
                button1.setVisibility(View.VISIBLE)

                val logout = findViewById<Button>(R.id.logout)
                logout.setVisibility(View.GONE)

                /*  intent = Intent(applicationContext, HomeActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent) */


                val intent = intent
                finish()
                startActivity(intent)


            }


        }

    }

    override fun initData() {

    }

    override fun initListener() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_admission -> {
                Toast.makeText(this, "Admission clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_parenting -> {
                Toast.makeText(this, "Parenting clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_news -> {
                Toast.makeText(this, "News clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_see_more -> {
                Toast.makeText(this, "See More clicked", Toast.LENGTH_SHORT).show()
            }
            /* R.id.nav_signup -> addFragment(
                                  SignupFragment.newInstance(),
                                  SignupFragment.TAG,
                                  addReplace = false,
                                  addToBackStack = false
                                )
             R.id.nav_signup -> addFragment(
                                  LoginFragment.newInstance(),
                                  LoginFragment.TAG,
                                  addReplace = false,
                                  addToBackStack = false
                                )*/
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
