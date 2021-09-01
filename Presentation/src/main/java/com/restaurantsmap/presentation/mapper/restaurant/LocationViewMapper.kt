package com.restaurantsmap.presentation.mapper.restaurant

import com.restaurantsmap.domain.models.restaurants.Location
import com.restaurantsmap.presentation.mapper.PresentationMapper
import com.restaurantsmap.presentation.models.restaurants.LocationView

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class LocationViewMapper : PresentationMapper<Location, LocationView> {
    override fun mapToPresentation(domain: Location?): LocationView =
        LocationView(
            address = domain?.address,
            city = domain?.city,
            country = domain?.country,
            crossStreet = domain?.crossStreet,
            lat = domain?.lat,
            lng = domain?.lng,
            state = domain?.state,
        )

}
