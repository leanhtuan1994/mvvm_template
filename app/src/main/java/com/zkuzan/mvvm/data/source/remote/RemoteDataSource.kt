package com.zkuzan.mvvm.data.source.remote

import com.zkuzan.mvvm.data.source.DataSource
import rx.Scheduler
import rx.schedulers.Schedulers


class RemoteDataSource (private val vervice: ZKuzanService, scheduler: Scheduler) : DataSource {

}