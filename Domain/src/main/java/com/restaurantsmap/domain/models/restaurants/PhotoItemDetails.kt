package com.restaurantsmap.domain.models.restaurants


import com.restaurantsmap.domain.models.DomainModel

data class PhotoItemDetails(
    val height: Int?,
    val prefix: String?,
    val suffix: String?,
    val width: Int?
) : DomainModel