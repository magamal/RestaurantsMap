package com.restaurantsmap.remote

import com.restaurantsmap.data.models.restaurants.RestaurantEntity
import com.restaurantsmap.data.repository.restaurants.RestaurantsRemote
import com.restaurantsmap.remote.mappers.restaurant.RestaurantModelMapper
import com.restaurantsmap.remote.network.RestApiConstants
import com.restaurantsmap.remote.network.errorhandler.adaptExceptionError
import com.restaurantsmap.remote.service.RestaurantsService

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
class RestaurantsRetrofitRemoteRepo(
    private val restaurantsService: RestaurantsService,
    private val restaurantModelMapper: RestaurantModelMapper
) : RestaurantsRemote {

    override suspend fun getRestaurants(lat: Double, lng: Double): List<RestaurantEntity> =
        restaurantsService
            .getRestaurants(
                location = "$lat,$lng", categoryId = RestApiConstants.FOOD_CATEGORY_ID_PARAM
            )
            .adaptExceptionError()
            .run {
                return@run response.venues.map(restaurantModelMapper::mapFromModel)
            }


    override suspend fun getRestaurantDetails(restaurantId: String): RestaurantEntity =
        restaurantsService
            .getRestaurantDetails(restaurantId = restaurantId)
            .adaptExceptionError()
            .run {
                return@run restaurantModelMapper.mapFromModel(response.venue)
            }
}