package com.restaurantsmap.remote.mappers

import com.restaurantsmap.data.models.Entity
import com.restaurantsmap.remote.models.RemoteModel


/**
 * This interface is used when you want to create a new Mapper
 * @M : is for Entity model (in remote layer)
 * @E : is for request model (in clientInfo layer)
 */
interface ModelMapper<M : RemoteModel, E : Entity> {
    fun mapFromModel(model: M?): E
}