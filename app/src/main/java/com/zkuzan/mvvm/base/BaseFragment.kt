package com.zkuzan.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zkuzan.mvvm.util.extension.inflateLayout

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = context?.inflateLayout(layoutId(), container, false)
        return rootView
    }


    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

}