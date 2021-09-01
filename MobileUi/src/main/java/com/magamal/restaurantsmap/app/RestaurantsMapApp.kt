package com.magamal.restaurantsmap.app

import android.app.Application
import com.restaurantsmap.data.di.dataModule
import com.restaurantsmap.domain.di.domainModule
import com.restaurantsmap.presentation.di.presentationModule
import com.restaurantsmap.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Mahmoud Gamal on 2019-09-06.
 */
class RestaurantsMapApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RestaurantsMapApp)
            modules(listOf(presentationModule, domainModule, dataModule, remoteModule))
        }
    }

    companion object {
        lateinit var instance: RestaurantsMapApp
            private set
    }
}