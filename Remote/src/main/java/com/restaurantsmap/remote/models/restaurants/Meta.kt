package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class Meta(
    @SerializedName("code")
    val code: Int,
    @SerializedName("requestId")
    val requestId: String,
    @SerializedName("errorDetail")
    val errorDetail: String?
) : RemoteModel