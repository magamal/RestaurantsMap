package com.restaurantsmap.data.mappers.restaurant

import com.restaurantsmap.data.mappers.EntityMapper
import com.restaurantsmap.data.models.restaurants.LocationEntity
import com.restaurantsmap.domain.models.restaurants.Location

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class LocationEntityMapper : EntityMapper<LocationEntity, Location> {
    override fun mapToDomain(entity: LocationEntity?): Location =
        Location(
            address = entity?.address,
            city = entity?.city,
            country = entity?.country,
            crossStreet = entity?.crossStreet,
            lat = entity?.lat,
            lng = entity?.lng,
            state = entity?.state,
        )

}
