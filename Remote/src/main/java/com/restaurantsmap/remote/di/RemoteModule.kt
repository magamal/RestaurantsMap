package com.restaurantsmap.remote.di

import com.restaurantsmap.data.repository.restaurants.RestaurantsRemote
import com.restaurantsmap.remote.RestaurantsRetrofitRemoteRepo
import com.restaurantsmap.remote.mappers.restaurant.*
import com.restaurantsmap.remote.network.RetrofitClient
import com.restaurantsmap.remote.service.RestaurantsService
import org.koin.dsl.module

/**
 * Created by Mahmoud Gamal on 5/25/19.
 */
val remoteModule = module {

    /**
     * RemoteModel mappers
     */
    single { RestaurantModelMapper(get(), get()) }
    single { LocationModelMapper() }
    single { PhotoItemDetailsModelMapper() }
    single { GroupModelMapper(get()) }
    single { PhotosModelMapper(get()) }


    /**
     * Remote repositories
     */
    single<RestaurantsRemote> { RestaurantsRetrofitRemoteRepo(get(), get()) }


    /**
     * Retrofit services
     */
    single { RetrofitClient.create(RestaurantsService::class.java) }

}
