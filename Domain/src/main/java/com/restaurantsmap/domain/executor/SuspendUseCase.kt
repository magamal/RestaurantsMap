package com.restaurantsmap.domain.executor

import com.restaurantsmap.domain.models.DomainModel

/**
 * @author Mahmoud Gamal on 28/07/2021.
 */
abstract class SuspendUseCase<T , in E> {

    protected abstract suspend fun buildUseCaseSuspend(params: E? = null): T

    suspend fun getData(params: E? = null): T = buildUseCaseSuspend(params)
}