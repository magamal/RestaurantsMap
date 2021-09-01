package com.restaurantsmap.domain.di

import com.restaurantsmap.domain.usecase.restaurants.GetPhotoForRestaurantUseCase
import com.restaurantsmap.domain.usecase.restaurants.GetRestaurantsUseCase
import org.koin.dsl.module

/**
 * Created by Mahmoud Gamal on 5/25/19.
 */
val domainModule = module {

    factory { GetRestaurantsUseCase(get()) }
    factory { GetPhotoForRestaurantUseCase(get()) }
}