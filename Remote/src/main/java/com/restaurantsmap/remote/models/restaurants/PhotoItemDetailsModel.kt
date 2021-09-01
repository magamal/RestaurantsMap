package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class PhotoItemDetailsModel(
    @SerializedName("height")
    val height: Int,
    @SerializedName("prefix")
    val prefix: String,
    @SerializedName("suffix")
    val suffix: String,
    @SerializedName("width")
    val width: Int
) : RemoteModel