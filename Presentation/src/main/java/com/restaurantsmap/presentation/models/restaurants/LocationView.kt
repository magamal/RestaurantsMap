package com.restaurantsmap.presentation.models.restaurants

import com.restaurantsmap.presentation.models.PresentationModel


data class LocationView(
    val address: String?,
    val city: String?,
    val country: String?,
    val crossStreet: String?,
    val lat: Double?,
    val lng: Double?,
    val state: String?
) : PresentationModel