package com.restaurantsmap.presentation.base

/**
 * @author Mahmoud Gamal on 1/7/21.
 */
abstract class ListLiveDataObserver<T> : ResourceLiveDataObserver<Pair<List<T>, List<T>>>() {
    override fun onChanged(resource: Resource<Pair<List<T>, List<T>>>?) {
        when (resource?.state) {
            Resource.State.LOADING -> {
                onLoading()
            }
            Resource.State.ERROR -> {
                onError(resource.throwable)
            }
            Resource.State.SUCCESS -> {
                resource.values?.let { values ->
                    val newItems = values.first
                    val allItems = values.second
                    onNewData(newItems = newItems, allItems = allItems)
                }
            }
        }
    }

    override fun onNewData(t: Pair<List<T>, List<T>>) {}

    abstract fun onNewData(newItems: List<T>, allItems: List<T>)
}