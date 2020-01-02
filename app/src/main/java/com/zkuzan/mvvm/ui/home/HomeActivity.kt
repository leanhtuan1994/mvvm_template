package com.zkuzan.mvvm.ui.home

import com.zkuzan.mvvm.R
import com.zkuzan.mvvm.base.BaseActivity

class HomeActivity : BaseActivity<HomeViewModel>(HomeViewModel::class) {

    override val layoutResId: Int get() = R.layout.activity_home

    private val args by lazy {
        HomeActivityArgs.deserializeFrom(intent)
    }

    override fun observerViewModel() {

    }

    override fun initUIComponent() {

    }

}