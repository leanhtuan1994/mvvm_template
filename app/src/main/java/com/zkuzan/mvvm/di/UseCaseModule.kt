package com.zkuzan.mvvm.di

import com.zkuzan.mvvm.domain.MainUseCase
import com.zkuzan.mvvm.domain.MainUseCaseImpl
import org.koin.dsl.module

val userCaseModule = module {
    factory<MainUseCase> { MainUseCaseImpl(get()) }
}