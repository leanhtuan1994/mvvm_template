package com.zkuzan.mvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zkuzan.mvvm.data.entity.response.BaseResponse
import com.zkuzan.mvvm.util.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventError = MutableLiveData<Event<BaseResponse>>()
    val eventFailure = MutableLiveData<Event<Throwable>>()
    val eventErrorShowToast = MutableLiveData<String>()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showError(baseResponse: BaseResponse) {
        eventError.value = Event(baseResponse)
    }

    fun showFailure(throwable: Throwable) {
        eventFailure.value = Event(throwable)
    }

    fun showToastError(string: String) {
        eventErrorShowToast.value = string
    }
}