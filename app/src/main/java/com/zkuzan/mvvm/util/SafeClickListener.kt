package com.zkuzan.mvvm.util

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private var defaultInterval: Int?= 500,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval ?: 0) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}