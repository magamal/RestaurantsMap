package com.restaurantsmap.domain.models.restaurants


import com.restaurantsmap.domain.models.DomainModel

data class Restaurant(
    val id: String?,
    val location: Location?,
    val name: String?,
    val photos: List<PhotoItemDetails>?,
) : DomainModel