package com.restaurantsmap.data.mappers.restaurant

import com.restaurantsmap.data.mappers.EntityMapper
import com.restaurantsmap.data.models.restaurants.PhotoItemDetailsEntity
import com.restaurantsmap.domain.models.restaurants.PhotoItemDetails

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class PhotoItemDetailsEntityMapper : EntityMapper<PhotoItemDetailsEntity, PhotoItemDetails> {
    override fun mapToDomain(entity: PhotoItemDetailsEntity?): PhotoItemDetails =
        PhotoItemDetails(
            height = entity?.height,
            prefix = entity?.prefix,
            suffix = entity?.suffix,
            width = entity?.width
        )
}