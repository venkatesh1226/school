package com.dorna.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ezyschooling.R

abstract class BaseActivity : AppCompatActivity(), BaseActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        initListener()
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initListener()

    override fun moveToNext(activityName: Class<*>, finishCurrent: Boolean, clearStack: Boolean) {
        val intent = Intent(this, activityName)
        if (finishCurrent)
            finish()
        if (clearStack)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun moveToNext(
        activityName: Class<*>,
        bundle: Bundle,
        finishCurrent: Boolean,
        clearStack: Boolean
    ) {
        val intent = Intent(this, activityName)
        intent.putExtras(bundle)
        if (finishCurrent)
            finish()
        if (clearStack)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun addFragment(
        fragment: Fragment,
        tag: String,
        addReplace: Boolean,
        addToBackStack: Boolean
    ) {
        val manager = supportFragmentManager

        // Begin the fragment transition using support fragment manager
        val transaction = manager.beginTransaction()

        //add animation
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        // Add/Replace the fragment on container
        if (addReplace)
            transaction.add(R.id.container, fragment, tag)
        else transaction.replace(R.id.container, fragment, tag)
        //Add to back stack
        if (addToBackStack)
            transaction.addToBackStack(null)

        // Finishing the transition
        transaction.commit()
    }

    override fun backPressed() {
        onBackPressed()
    }

    override fun toolbarPrimaryTitle(title: String) {

    }

    override fun toolbarSecondaryTitle(title: String) {

    }

    override fun finishCurrent() {
        finish()
    }
}