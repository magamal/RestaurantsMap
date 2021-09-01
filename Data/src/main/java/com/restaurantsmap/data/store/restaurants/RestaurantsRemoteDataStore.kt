package com.restaurantsmap.data.store.restaurants

import com.restaurantsmap.data.repository.restaurants.RestaurantsDataStore
import com.restaurantsmap.data.repository.restaurants.RestaurantsRemote

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
class RestaurantsRemoteDataStore(private val restaurantsRemote: RestaurantsRemote) :
    RestaurantsDataStore {

    override suspend fun getRestaurants(lat: Double, lng: Double) =
        restaurantsRemote
            .getRestaurants(lat = lat, lng = lng)

    override suspend fun getRestaurantDetails(restaurantId: String) =
        restaurantsRemote
            .getRestaurantDetails(restaurantId = restaurantId)

}