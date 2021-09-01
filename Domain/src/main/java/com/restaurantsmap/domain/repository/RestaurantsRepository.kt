package com.restaurantsmap.domain.repository

import com.restaurantsmap.domain.models.restaurants.PhotoItemDetails
import com.restaurantsmap.domain.models.restaurants.Restaurant

/**
 * Created by Mahmoud Gamal on 2019-06-03.
 */
interface RestaurantsRepository {
    suspend fun getRestaurants(lat: Double, lng: Double): List<Restaurant>
    suspend fun getPhotosForItem(restaurantId: String?): List<PhotoItemDetails>?
}