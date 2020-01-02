package com.zkuzan.mvvm.di

import com.zkuzan.mvvm.data.source.ApiRepository
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val repositoryModule = module {
    factory { ApiRepository(get(), Schedulers.io()) }
}