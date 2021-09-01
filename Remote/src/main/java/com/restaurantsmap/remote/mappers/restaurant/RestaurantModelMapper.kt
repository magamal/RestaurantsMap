package com.restaurantsmap.remote.mappers.restaurant

import com.restaurantsmap.data.models.restaurants.RestaurantEntity
import com.restaurantsmap.remote.mappers.ModelMapper
import com.restaurantsmap.remote.models.restaurants.RestaurantModel

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class RestaurantModelMapper(
    private val locationMapper: LocationModelMapper,
    private val photosMapper: PhotosModelMapper
) : ModelMapper<RestaurantModel, RestaurantEntity> {
    override fun mapFromModel(model: RestaurantModel?): RestaurantEntity = RestaurantEntity(
        id = model?.id,
        name = model?.name,
        location = locationMapper.mapFromModel(model?.location),
        photos = photosMapper.mapFromModel(model?.photos)
    )
}