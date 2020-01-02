package com.zkuzan.mvvm.di

import com.zkuzan.mvvm.data.source.remote.ZKuzanService
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module {
    factory { createZKuzanService(get()) }
}

private fun createZKuzanService(retrofit: Retrofit) = retrofit.create(ZKuzanService::class.java)