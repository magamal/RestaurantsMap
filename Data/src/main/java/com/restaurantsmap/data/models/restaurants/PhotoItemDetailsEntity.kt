package com.restaurantsmap.data.models.restaurants


import com.restaurantsmap.data.models.Entity

data class PhotoItemDetailsEntity(
    val height: Int?,
    val prefix: String?,
    val suffix: String?,
    val width: Int?
) : Entity