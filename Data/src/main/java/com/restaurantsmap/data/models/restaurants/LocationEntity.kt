package com.restaurantsmap.data.models.restaurants

import com.restaurantsmap.data.models.Entity


data class LocationEntity(
    val address: String?,
    val city: String?,
    val country: String?,
    val crossStreet: String?,
    val lat: Double?,
    val lng: Double?,
    val state: String?
) : Entity