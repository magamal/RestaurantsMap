package com.restaurantsmap.data

import com.restaurantsmap.data.mappers.restaurant.PhotoItemDetailsEntityMapper
import com.restaurantsmap.data.mappers.restaurant.RestaurantEntityMapper
import com.restaurantsmap.data.store.restaurants.RestaurantsDataStoreFactory
import com.restaurantsmap.domain.models.restaurants.PhotoItemDetails
import com.restaurantsmap.domain.models.restaurants.Restaurant
import com.restaurantsmap.domain.repository.RestaurantsRepository


/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
class RestaurantsDataRepoImpl(
    private val storeFactory: RestaurantsDataStoreFactory,
    private val restaurantMapper: RestaurantEntityMapper,
    private val photoItemDetailsMapper: PhotoItemDetailsEntityMapper
) : RestaurantsRepository {

    override suspend fun getRestaurants(lat: Double, lng: Double): List<Restaurant> =
        storeFactory
            .getRemote()
            .getRestaurants(lat = lat, lng = lng)
//            .map { restaurant ->
//                restaurant.copy(photos = getPhotosForItem(restaurantId = restaurant.id))
//            }
            .map(restaurantMapper::mapToDomain)


    override suspend fun getPhotosForItem(restaurantId: String?): List<PhotoItemDetails>? =
        if (restaurantId.isNullOrEmpty())
            null
        else
            try {
                storeFactory
                    .getRemote()
                    .getRestaurantDetails(restaurantId)
                    .run(restaurantMapper::mapToDomain)
                    .photos
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

    private fun testFlowAndroid() {

    }

}