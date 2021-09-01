package com.restaurantsmap.presentation.mapper.restaurant

import com.restaurantsmap.domain.models.restaurants.Restaurant
import com.restaurantsmap.presentation.mapper.PresentationMapper
import com.restaurantsmap.presentation.models.restaurants.RestaurantView

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class RestaurantViewMapper(
    private val locationMapper: LocationViewMapper,
    private val photosMapper: PhotoItemDetailsViewMapper
) : PresentationMapper<Restaurant, RestaurantView> {

    override fun mapToPresentation(domain: Restaurant?): RestaurantView =
        RestaurantView(
            id = domain?.id,
            name = domain?.name,
            location = locationMapper.mapToPresentation(domain?.location),
            photos = domain?.photos?.map(photosMapper::mapToPresentation)
        )

}