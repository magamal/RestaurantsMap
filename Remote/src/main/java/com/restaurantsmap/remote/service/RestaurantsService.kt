package com.restaurantsmap.remote.service

import com.restaurantsmap.remote.models.GenericResponse
import com.restaurantsmap.remote.models.restaurants.RestaurantDetailsResponse
import com.restaurantsmap.remote.models.restaurants.RestaurantsResponseList
import com.restaurantsmap.remote.network.RestApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
interface RestaurantsService {

    @GET(
        "${RestApiConstants.GET_RESTAURANTS_END_POINT}"
//                "?${RestApiConstants.LOCATION_PARAM}={p1},{p2}" +
//                "&${RestApiConstants.CATEGORY_ID_PARAM}={categoryId}"
    )
    suspend fun getRestaurants(
//        @Path("p1") lat: Double,
//        @Path("p2") lng: Double,
//        @Path("categoryId") categoryId: String
        @Query(RestApiConstants.LOCATION_PARAM, encoded = true) location: String,
        @Query(RestApiConstants.CATEGORY_ID_PARAM, encoded = true) categoryId: String
    ): GenericResponse<RestaurantsResponseList>

    @GET("{VENUE_ID}")
    suspend fun getRestaurantDetails(@Path("VENUE_ID") restaurantId: String): GenericResponse<RestaurantDetailsResponse>
}