package com.restaurantsmap.remote.mappers.restaurant

import com.restaurantsmap.data.models.restaurants.PhotosEntity
import com.restaurantsmap.remote.mappers.ModelMapper
import com.restaurantsmap.remote.models.restaurants.PhotosModel

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class PhotosModelMapper(
    private val groupMapper: GroupModelMapper
) : ModelMapper<PhotosModel, PhotosEntity> {
    override fun mapFromModel(model: PhotosModel?): PhotosEntity =
        PhotosEntity(
            count = model?.count,
            groups = model?.groups?.map(groupMapper::mapFromModel)
        )
}