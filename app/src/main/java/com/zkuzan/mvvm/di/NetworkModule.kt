package com.zkuzan.mvvm.di

import android.content.Context
import com.zkuzan.mvvm.BuildConfig
import com.zkuzan.mvvm.MainApplication
import com.zkuzan.mvvm.util.retrofit.NullOnEmptyConverterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


const val HOST = ""


val networkModule = module {
    single { createInterceptor() }
    single { createOkHttpClient(androidContext(), get()) }
    single { createNetworkClient(get(), HOST) }
}

private fun createNetworkClient(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit
    .Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(NullOnEmptyConverterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private fun createOkHttpClient(context: Context, requestInterceptor: Interceptor): OkHttpClient {
    val cacheDir = File(MainApplication.folder, "responses")
    val builder = OkHttpClient.Builder()
        .cache(Cache(cacheDir, 10 * 1024 * 1024)) //10Mb
        .addInterceptor(requestInterceptor)
        .addInterceptor(log(BuildConfig.DEBUG))
    return builder.build()
}

private fun createInterceptor() = Interceptor { chain ->
    val original = chain.request()
    val builder = original.newBuilder().method(original.method(), original.body())

    val cacheControl = original.header("Cache-Control")
    if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
    ) {
        builder.removeHeader("Pragma")
            .header("Cache-Control", "public, max-age=" + 5000)
    }

    val newRequest = builder.build()
    chain.proceed(newRequest)
}

private fun log(enabled: Boolean): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (enabled) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}