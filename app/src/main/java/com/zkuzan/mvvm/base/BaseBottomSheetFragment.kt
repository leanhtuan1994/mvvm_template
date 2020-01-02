package com.zkuzan.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zkuzan.mvvm.R
import com.zkuzan.mvvm.util.extension.inflateLayout
import kotlinx.android.synthetic.*
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseBottomSheetFragment<out M : ViewModel>(clazz: KClass<M>) :
    BottomSheetDialogFragment() {

    val viewModel: M by lazy { getViewModel(clazz) }

    var rootView: View? = null

    abstract val layoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.inflateLayout(layoutResId, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUIComponent()
        observerViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.sAppBottomSheetDialogTheme)
    }

    abstract fun initUIComponent()

    abstract fun observerViewModel()

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    override fun onDestroyView() {
        this.clearFindViewByIdCache()
        super.onDestroyView()
    }
}