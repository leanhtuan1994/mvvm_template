package com.zkuzan.mvvm.di

import android.content.Context
import com.zkuzan.mvvm.data.cache.AppCache
import com.zkuzan.mvvm.data.cache.ICache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<ICache> { createCache(androidContext()) }
}

private fun createCache(context: Context) = AppCache(context)