package com.zkuzan.mvvm.domain

import com.zkuzan.mvvm.data.source.ApiRepository


interface MainUseCase {

}

class MainUseCaseImpl(private val apiRepository: ApiRepository) : MainUseCase {

}