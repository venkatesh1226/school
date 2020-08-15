package com.ezyschooling.start

import android.animation.ObjectAnimator
import android.os.Handler
import android.util.DisplayMetrics
import androidx.databinding.DataBindingUtil
import com.ezyschooling.R
import com.ezyschooling.databinding.ActivitySplashBinding
import com.ezyschooling.utils.StaticKeysCode
import com.ezyschooling.home.HomeActivity
import com.dorna.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    private val handler: Handler = Handler()
    private lateinit var runnable: Runnable

    override fun initView() {
        splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    override fun initData() {

        // get screen resolution and save to static
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        StaticKeysCode.SCREEN_WIDTH = displayMetrics.widthPixels
        StaticKeysCode.SCREEN_HEIGHT = displayMetrics.heightPixels

        ObjectAnimator.ofFloat(
            textView, "translationY", StaticKeysCode.SCREEN_HEIGHT.toFloat(), 0f
        ).apply {
            duration = 1000
            start()
        }

        runnable = Runnable {

            moveToNext(HomeActivity::class.java, finishCurrent = true, clearStack = true)
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
        handler.postDelayed(runnable, 1000)
    }

    override fun onDestroy() {
        if (runnable != null)
            handler.removeCallbacks(runnable)
        super.onDestroy()
    }

    override fun initListener() {

    }

}