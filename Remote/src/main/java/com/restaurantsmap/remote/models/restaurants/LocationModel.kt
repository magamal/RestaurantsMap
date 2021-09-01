package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class LocationModel(
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("crossStreet")
    val crossStreet: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?,
    @SerializedName("state")
    val state: String?
) : RemoteModel