package com.restaurantsmap.data.models.restaurants

import com.restaurantsmap.data.models.Entity


data class GroupEntity(
    val count: Int?,
    val items: List<PhotoItemDetailsEntity>?,
    val name: String?,
    val type: String?
) : Entity