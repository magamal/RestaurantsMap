package com.restaurantsmap.domain.models.restaurants

import com.restaurantsmap.domain.models.DomainModel


data class Location(
    val address: String?,
    val city: String?,
    val country: String?,
    val crossStreet: String?,
    val lat: Double?,
    val lng: Double?,
    val state: String?
) : DomainModel