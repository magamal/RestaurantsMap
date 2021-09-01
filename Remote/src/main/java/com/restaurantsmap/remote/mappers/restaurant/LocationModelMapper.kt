package com.restaurantsmap.remote.mappers.restaurant

import com.restaurantsmap.data.models.restaurants.LocationEntity
import com.restaurantsmap.remote.mappers.ModelMapper
import com.restaurantsmap.remote.models.restaurants.LocationModel

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class LocationModelMapper : ModelMapper<LocationModel, LocationEntity> {
    override fun mapFromModel(model: LocationModel?): LocationEntity =
        LocationEntity(
            address = model?.address,
            city = model?.city,
            country = model?.country,
            crossStreet = model?.crossStreet,
            lat = model?.lat,
            lng = model?.lng,
            state = model?.state,
        )
}