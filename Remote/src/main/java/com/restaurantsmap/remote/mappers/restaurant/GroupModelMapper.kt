package com.restaurantsmap.remote.mappers.restaurant

import com.restaurantsmap.data.models.restaurants.GroupEntity
import com.restaurantsmap.remote.mappers.ModelMapper
import com.restaurantsmap.remote.models.restaurants.GroupModel

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class GroupModelMapper(
    private val photoItemDetailsMapper: PhotoItemDetailsModelMapper
) : ModelMapper<GroupModel, GroupEntity> {
    override fun mapFromModel(model: GroupModel?): GroupEntity =
        GroupEntity(
            count = model?.count,
            items = model?.items?.map(photoItemDetailsMapper::mapFromModel),
            name = model?.name,
            type = model?.type
        )
}