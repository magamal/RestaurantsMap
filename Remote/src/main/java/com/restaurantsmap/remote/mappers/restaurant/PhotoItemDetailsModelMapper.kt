package com.restaurantsmap.remote.mappers.restaurant

import com.restaurantsmap.data.models.restaurants.PhotoItemDetailsEntity
import com.restaurantsmap.remote.mappers.ModelMapper
import com.restaurantsmap.remote.models.restaurants.PhotoItemDetailsModel

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class PhotoItemDetailsModelMapper : ModelMapper<PhotoItemDetailsModel, PhotoItemDetailsEntity> {
    override fun mapFromModel(model: PhotoItemDetailsModel?): PhotoItemDetailsEntity =
        PhotoItemDetailsEntity(
            height = model?.height,
            prefix = model?.prefix,
            suffix = model?.suffix,
            width = model?.width
        )
}