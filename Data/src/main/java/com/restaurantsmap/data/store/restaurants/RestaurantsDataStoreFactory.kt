package com.restaurantsmap.data.store.restaurants

import com.restaurantsmap.data.repository.restaurants.RestaurantsDataStore

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
class RestaurantsDataStoreFactory(private val restaurantsRemoteDataStore: RestaurantsRemoteDataStore) {

    fun getRemote(): RestaurantsDataStore = restaurantsRemoteDataStore

}