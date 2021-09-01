package com.restaurantsmap.data.repository.restaurants

import com.restaurantsmap.data.models.restaurants.RestaurantEntity

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
interface RestaurantsRemote {
    suspend fun getRestaurants(lat: Double, lng: Double): List<RestaurantEntity>

    suspend fun getRestaurantDetails(restaurantId: String): RestaurantEntity
}