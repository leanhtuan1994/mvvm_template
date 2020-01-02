package com.zkuzan.mvvm.di

import com.zkuzan.mvvm.data.source.remote.RemoteDataSource
import org.koin.dsl.module
import rx.schedulers.Schedulers


val dataSourceModule = module {
    factory { RemoteDataSource(get(), Schedulers.io() ) }
}