package com.restaurantsmap.domain.usecase.restaurants

import com.restaurantsmap.domain.executor.SuspendUseCase
import com.restaurantsmap.domain.models.restaurants.Restaurant
import com.restaurantsmap.domain.repository.RestaurantsRepository

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
class GetRestaurantsUseCase(
    private val restaurantsRepository: RestaurantsRepository
) : SuspendUseCase<List<Restaurant>, GetRestaurantsUseCase.Params>() {

    override suspend fun buildUseCaseSuspend(params: Params?) =
        if (params == null)
            throw IllegalAccessException("Params can't be null")
        else restaurantsRepository
            .getRestaurants(
                lat = params.lat, lng = params.lng
            )


    data class Params private constructor(val lat: Double, val lng: Double) {
        companion object {
            fun forLocation(lat: Double, lng: Double) =
                Params(lat = lat, lng = lng)
        }
    }
}