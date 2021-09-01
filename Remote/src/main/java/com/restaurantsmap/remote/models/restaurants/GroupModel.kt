package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class GroupModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<PhotoItemDetailsModel>,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
) : RemoteModel