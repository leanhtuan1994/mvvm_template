package com.zkuzan.mvvm.base

import android.app.Activity
import android.content.Context
import android.content.Intent

interface ActivityArgs {
    fun intent(activity: Context): Intent

    fun launch(activity: Context) {
        val intent = intent(activity)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
    }

    fun launch(activity: Activity, requestCode: Int) {
        val intent = intent(activity)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivityForResult(intent, requestCode)
    }
}