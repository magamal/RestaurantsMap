package com.restaurantsmap.presentation.models.restaurants


import com.restaurantsmap.presentation.models.PresentationModel

data class RestaurantView(
    val id: String?,
    val location: LocationView?,
    val name: String?,
    val photos: List<PhotoItemDetailsView>?,
) : PresentationModel