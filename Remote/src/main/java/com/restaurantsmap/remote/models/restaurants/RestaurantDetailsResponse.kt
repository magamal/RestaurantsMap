package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class RestaurantDetailsResponse(
    @SerializedName("venue")
    val venue: RestaurantModel
) : RemoteModel