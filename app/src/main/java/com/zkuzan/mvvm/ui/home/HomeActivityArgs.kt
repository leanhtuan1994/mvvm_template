package com.zkuzan.mvvm.ui.home

import android.content.Context
import android.content.Intent
import com.zkuzan.mvvm.base.ActivityArgs

private const val INTENT_EXTRA_USER_TOKEN = "INTENT_EXTRA_USER_TOKEN"

class HomeActivityArgs( val userToken: String? = null) : ActivityArgs {
    override fun intent(activity: Context): Intent = Intent(activity, HomeActivity::class.java).apply {
        putExtra(INTENT_EXTRA_USER_TOKEN, userToken)
    }

    companion object {
        fun deserializeFrom(intent: Intent): HomeActivityArgs  {
            return HomeActivityArgs(
                userToken = intent.extras?.getString(INTENT_EXTRA_USER_TOKEN)
            )
        }
    }
}