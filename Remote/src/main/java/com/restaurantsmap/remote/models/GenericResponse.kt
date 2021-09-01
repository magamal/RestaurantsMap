package com.restaurantsmap.remote.models

import com.google.gson.annotations.SerializedName
import com.restaurantsmap.remote.models.restaurants.Meta

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
data class GenericResponse<T>(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("response")
    val response: T
) : RemoteModel