package com.restaurantsmap.presentation.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.restaurantsmap.domain.usecase.restaurants.GetPhotoForRestaurantUseCase
import com.restaurantsmap.domain.usecase.restaurants.GetRestaurantsUseCase
import com.restaurantsmap.presentation.base.BaseViewModel
import com.restaurantsmap.presentation.base.MutableListLiveData
import com.restaurantsmap.presentation.base.MutableResourceLiveData
import com.restaurantsmap.presentation.mapper.restaurant.PhotoItemDetailsViewMapper
import com.restaurantsmap.presentation.mapper.restaurant.RestaurantViewMapper
import com.restaurantsmap.presentation.models.restaurants.RestaurantView
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.streams.toList

/**
 * @author Mahmoud Gamal on 20/08/2021.
 */
class RestaurantsViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getPhotoForRestaurantUseCase: GetPhotoForRestaurantUseCase,
    private val restaurantMapper: RestaurantViewMapper,
    private val photoItemDetailsViewMapper: PhotoItemDetailsViewMapper
) : BaseViewModel() {

    private val restaurantsLiveData = MutableListLiveData<RestaurantView>()
    private val selectedRestaurantLiveData = MutableResourceLiveData<RestaurantView>()

    private fun isLoading(): Boolean = restaurantsLiveData.isLoadingData()


    fun observeOnSelectedRestaurants(
        owner: LifecycleOwner,
        onLoading: () -> Unit,
        onError: (Throwable?) -> Unit,
        onNewData: (RestaurantView) -> Unit
    ) =
        selectedRestaurantLiveData.observe(
            owner = owner,
            onLoading = onLoading,
            onError = onError,
            onNewData = onNewData
        )

    fun observeOnRestaurants(
        owner: LifecycleOwner,
        onLoading: () -> Unit,
        onError: (Throwable?) -> Unit,
        onNewData: (List<RestaurantView>, List<RestaurantView>) -> Unit
    ) =
        restaurantsLiveData.observe(
            owner = owner,
            onLoading = onLoading,
            onError = onError,
            onNewData = onNewData
        )

    fun clearToNewFetchLocation(latitude: Double, longitude: Double) {
        fetchData(latitude = latitude, longitude = longitude)
    }

    private fun fetchData(latitude: Double, longitude: Double) {
        if (isLoading())
            return

        restaurantsLiveData.loading()
        viewModelScope.launch {
            try {
                val items = getRestaurantsUseCase.getData(
                    GetRestaurantsUseCase
                        .Params.forLocation(latitude, longitude)
                ).map(restaurantMapper::mapToPresentation)
                restaurantsLiveData.postAddItems(items)
                selectRestaurantItem(items.first())
                getItemsPhotos(items);
            } catch (e: Exception) {
                e.printStackTrace()
                restaurantsLiveData.postError(throwable = e)
            }
        }
    }

    private fun getItemsPhotos(items: List<RestaurantView>) {
        viewModelScope.launch {
            items.parallelStream()
                .map { restaurant ->
                    return@map async {
                        restaurant?.id?.let { id ->
                            return@async restaurant.copy(
                                photos = getRestaurantPhoto(id)
                            )
                        }
                        return@async restaurant
                    }
                }
                .toList()
                .awaitAll()
                .let { newItems ->
                    restaurantsLiveData.postAddItems(newItems)
                }
        }
    }

    private suspend fun getRestaurantPhoto(restaurantId: String) =
        getPhotoForRestaurantUseCase.getData(
            GetPhotoForRestaurantUseCase.Params.forRestaurant(restaurantId)
        )?.map(photoItemDetailsViewMapper::mapToPresentation)


    fun selectRestaurantItem(restaurant: RestaurantView) =
        selectedRestaurantLiveData.postSuccess(restaurant)


}