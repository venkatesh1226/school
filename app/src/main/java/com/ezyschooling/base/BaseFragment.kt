package com.dorna.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment :Fragment(){

    lateinit var baseActivityListener: BaseActivityListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivityListener = activity as BaseActivityListener
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = initView(inflater, container)
        initData(view)
        initListener(view)
        return view
    }

    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?): View

    abstract fun initData(view: View)

    abstract fun initListener(view: View)

}