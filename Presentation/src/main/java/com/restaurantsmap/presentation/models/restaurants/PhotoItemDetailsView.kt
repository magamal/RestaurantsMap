package com.restaurantsmap.presentation.models.restaurants


import com.magamal.common.GlobalConstants
import com.restaurantsmap.presentation.models.PresentationModel

data class PhotoItemDetailsView(
    val height: Int?,
    val prefix: String?,
    val suffix: String?,
    val width: Int?
) : PresentationModel {
    fun buildImgUrl(): String =
        "$prefix${GlobalConstants.DEFAULT_IMAGE_SIZE}$suffix"
}