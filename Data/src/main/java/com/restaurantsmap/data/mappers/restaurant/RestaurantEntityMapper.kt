package com.restaurantsmap.data.mappers.restaurant

import com.restaurantsmap.data.mappers.EntityMapper
import com.restaurantsmap.data.models.restaurants.RestaurantEntity
import com.restaurantsmap.domain.models.restaurants.Restaurant

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class RestaurantEntityMapper(
    private val locationMapper: LocationEntityMapper,
    private val photosMapper: PhotoItemDetailsEntityMapper
) : EntityMapper<RestaurantEntity, Restaurant> {

    override fun mapToDomain(entity: RestaurantEntity?): Restaurant =
        Restaurant(
            id = entity?.id,
            name = entity?.name,
            location = locationMapper.mapToDomain(entity?.location),
            photos = entity?.photos?.groups?.flatMap {
                it.items?.map(photosMapper::mapToDomain) ?: emptyList()
            }
        )

}