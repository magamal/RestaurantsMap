package com.restaurantsmap.data.models.restaurants


import com.restaurantsmap.data.models.Entity

data class RestaurantEntity(
    val id: String?,
    val location: LocationEntity?,
    val name: String?,
    val photos: PhotosEntity?,
) : Entity