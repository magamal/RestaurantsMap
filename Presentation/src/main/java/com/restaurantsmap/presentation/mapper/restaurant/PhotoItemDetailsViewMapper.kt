package com.restaurantsmap.presentation.mapper.restaurant

import com.restaurantsmap.data.models.restaurants.PhotoItemDetailsEntity
import com.restaurantsmap.domain.models.restaurants.PhotoItemDetails
import com.restaurantsmap.presentation.mapper.PresentationMapper
import com.restaurantsmap.presentation.models.restaurants.PhotoItemDetailsView

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class PhotoItemDetailsViewMapper : PresentationMapper<PhotoItemDetails, PhotoItemDetailsView> {
    override fun mapToPresentation(domain: PhotoItemDetails?): PhotoItemDetailsView =
        PhotoItemDetailsView(
            height = domain?.height,
            prefix = domain?.prefix,
            suffix = domain?.suffix,
            width = domain?.width
        )
}