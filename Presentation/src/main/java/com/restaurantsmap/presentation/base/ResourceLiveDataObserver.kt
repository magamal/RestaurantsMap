package com.restaurantsmap.presentation.base

import androidx.lifecycle.Observer

/**
 * @author Mahmoud Gamal on 1/7/21.
 */
abstract class ResourceLiveDataObserver<T> : Observer<Resource<T>> {
    override fun onChanged(resource: Resource<T>?) {
        when (resource?.state) {
            Resource.State.LOADING -> {
                onLoading()
            }
            Resource.State.ERROR -> {
                onError(resource.throwable)
            }
            Resource.State.SUCCESS -> {
                resource.values?.let { values ->
                    onNewData(values)
                }
            }
        }
    }

    abstract fun onLoading()
    abstract fun onError(throwable: Throwable?)
    abstract fun onNewData(t: T)
}