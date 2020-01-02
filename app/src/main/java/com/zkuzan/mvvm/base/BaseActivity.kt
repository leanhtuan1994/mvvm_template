package com.zkuzan.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<out M : ViewModel>(clazz: KClass<M>) : AppCompatActivity() {

    val viewModel: M by lazy { getViewModel(clazz) }

    abstract val layoutResId: Int

    abstract fun observerViewModel()

    abstract fun initUIComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        observerViewModel()
        initUIComponent()
    }

}