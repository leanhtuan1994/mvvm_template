package com.zkuzan.mvvm

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.zkuzan.mvvm.di.*
import com.zkuzan.mvvm.util.MyDebugTree
import com.zkuzan.mvvm.util.Utils
import com.zkuzan.mvvm.util.image.GlideImageHelper
import com.zkuzan.mvvm.util.image.ImageHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import java.io.File

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initAppFolder(this)

        Utils.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }

        imageHelper = GlideImageHelper(this)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(
                appModule + apiModule + networkModule + dataSourceModule + repositoryModule + userCaseModule + viewModelModule
            )
        }
    }

    private fun initAppFolder(context: Context) {

        val fileTemp = context.externalCacheDir
        folder = if (fileTemp != null && fileTemp.exists()) {
            fileTemp.absolutePath
        } else {
            context.cacheDir.absolutePath
        }

        folder += File.separator

    }


    companion object {
        var folder = ""
        var imageHelper: ImageHelper? = null
    }
}