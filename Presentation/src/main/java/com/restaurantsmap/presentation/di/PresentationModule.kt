package com.restaurantsmap.presentation.di


import com.magamal.common.Logger
import com.restaurantsmap.domain.executor.PostExecutionThread
import com.restaurantsmap.presentation.LoggerImpl
import com.restaurantsmap.presentation.PostExecutionThreadImp
import com.restaurantsmap.presentation.mapper.restaurant.LocationViewMapper
import com.restaurantsmap.presentation.mapper.restaurant.PhotoItemDetailsViewMapper
import com.restaurantsmap.presentation.mapper.restaurant.RestaurantViewMapper
import com.restaurantsmap.presentation.viewmodels.RestaurantsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Mahmoud Gamal on 5/25/19.
 */
val presentationModule = module {

    single<PostExecutionThread> { PostExecutionThreadImp() }

    single<Logger> { LoggerImpl() }


    single { RestaurantViewMapper(get(), get()) }
    single { LocationViewMapper() }
    single { PhotoItemDetailsViewMapper() }

    viewModel { RestaurantsViewModel(get(), get(), get(), get()) }
}