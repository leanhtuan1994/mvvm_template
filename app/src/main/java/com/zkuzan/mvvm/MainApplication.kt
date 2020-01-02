package com.zkuzan.mvvm

import android.app.Application
import android.content.Context
import com.zkuzan.mvvm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.io.File

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initAppFolder(this)

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
    }
}