package com.dorna.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment

interface BaseActivityListener {
    fun toolbarPrimaryTitle(title: String)
    fun toolbarSecondaryTitle(title: String)
    fun backPressed()
    fun finishCurrent()
    fun addFragment(fragment: Fragment, tag: String, addReplace: Boolean, addToBackStack: Boolean)
    fun moveToNext(activityName: Class<*>, finishCurrent: Boolean, clearStack: Boolean)
    fun moveToNext(
        activityName: Class<*>,
        bundle: Bundle,
        finishCurrent: Boolean,
        clearStack: Boolean
    )

}