package com.dorna.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseFragmentBottomSheet:BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getContentView(), container, false)
        initData(view)
        initListener(view)
        return view
    }

    abstract fun getContentView(): Int

    abstract fun initData(view: View)

    abstract fun initListener(view: View)
}