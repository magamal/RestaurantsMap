package com.restaurantsmap.remote.models.restaurants


import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.RemoteModel

data class PhotosModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("groups")
    val groups: List<GroupModel>
) : RemoteModel