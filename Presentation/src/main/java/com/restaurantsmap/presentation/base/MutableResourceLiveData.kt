package com.restaurantsmap.presentation.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

/**
 * @author Mahmoud Gamal on 1/1/21.
 */
open class MutableResourceLiveData<T> : MutableLiveData<Resource<T>>() {

    fun success(t: T) {
        value = Resource.success(t)
    }

    fun postSuccess(t: T) {
        postValue(Resource.success(t))
    }

    fun error(throwable: Throwable?) {
        value = Resource.error(throwable)
    }

    fun postError(throwable: Throwable?) {
        postValue(Resource.error(throwable))
    }

    fun loading() {
        value = Resource.loading()
    }

    fun postLoading() {
        postValue(Resource.loading())
    }

    fun isLoadingData(): Boolean {
        return !(value == null || value?.state != Resource.State.LOADING)
    }


    fun observe(
        owner: LifecycleOwner,
        onLoading: () -> Unit,
        onError: (Throwable?) -> Unit,
        onNewData: (T) -> Unit
    ) {
        super.observe(owner, object : ResourceLiveDataObserver<T>() {
            override fun onLoading() = onLoading()

            override fun onError(throwable: Throwable?) = onError(throwable)

            override fun onNewData(t: T) = onNewData(t)
        })
    }

}