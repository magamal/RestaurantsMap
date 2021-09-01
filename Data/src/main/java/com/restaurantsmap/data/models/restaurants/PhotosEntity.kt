package com.restaurantsmap.data.models.restaurants

import com.restaurantsmap.data.models.Entity


data class PhotosEntity(
    val count: Int?,
    val groups: List<GroupEntity>?
) : Entity