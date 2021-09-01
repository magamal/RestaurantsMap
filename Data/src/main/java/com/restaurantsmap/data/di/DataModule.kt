package com.restaurantsmap.data.di

import com.restaurantsmap.data.RestaurantsDataRepoImpl
import com.restaurantsmap.data.mappers.restaurant.LocationEntityMapper
import com.restaurantsmap.data.mappers.restaurant.PhotoItemDetailsEntityMapper
import com.restaurantsmap.data.mappers.restaurant.RestaurantEntityMapper
import com.restaurantsmap.data.store.restaurants.RestaurantsDataStoreFactory
import com.restaurantsmap.data.store.restaurants.RestaurantsRemoteDataStore
import com.restaurantsmap.domain.repository.RestaurantsRepository
import org.koin.dsl.module

/**
 * Created by Mahmoud Gamal on 5/25/19.
 */
val dataModule = module {

    factory { RestaurantEntityMapper(get(), get()) }
    factory { LocationEntityMapper() }
    factory { PhotoItemDetailsEntityMapper() }



    single { RestaurantsRemoteDataStore(get()) }
    single { RestaurantsDataStoreFactory(get()) }
    single<RestaurantsRepository> { RestaurantsDataRepoImpl(get(), get(), get()) }

}