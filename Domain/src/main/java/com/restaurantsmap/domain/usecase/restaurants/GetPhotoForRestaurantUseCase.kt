package com.restaurantsmap.domain.usecase.restaurants

import com.restaurantsmap.domain.executor.SuspendUseCase
import com.restaurantsmap.domain.models.restaurants.PhotoItemDetails
import com.restaurantsmap.domain.repository.RestaurantsRepository

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
class GetPhotoForRestaurantUseCase(
    private val restaurantsRepository: RestaurantsRepository
) : SuspendUseCase<List<PhotoItemDetails>?, GetPhotoForRestaurantUseCase.Params>() {

    override suspend fun buildUseCaseSuspend(params: Params?) =
        if (params == null)
            throw IllegalAccessException("Params can't be null")
        else restaurantsRepository
            .getPhotosForItem(restaurantId = params.restaurantId)


    data class Params private constructor(val restaurantId: String) {
        companion object {
            fun forRestaurant(restaurantId: String) =
                Params(restaurantId)
        }
    }
}