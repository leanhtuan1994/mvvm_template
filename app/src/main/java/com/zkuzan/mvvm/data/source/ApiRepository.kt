package com.zkuzan.mvvm.data.source

import com.zkuzan.mvvm.data.source.remote.RemoteDataSource
import io.reactivex.Scheduler


class ApiRepository (private val remoteDataSource: RemoteDataSource, private val scheduler: Scheduler) : DataSource {

}