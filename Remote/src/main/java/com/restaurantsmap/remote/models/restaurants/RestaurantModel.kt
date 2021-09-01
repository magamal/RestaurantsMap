package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class RestaurantModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("location")
    val location: LocationModel,
    @SerializedName("name")
    val name: String,
    @SerializedName("photos")
    val photos: PhotosModel,
) : RemoteModel