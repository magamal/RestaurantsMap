package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class RestaurantsResponseList(
    @SerializedName("venues")
    val venues: List<RestaurantModel>
) : RemoteModel