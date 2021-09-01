package com.magamal.restaurantsmap.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magamal.restaurantsmap.R
import com.magamal.restaurantsmap.base.BaseFragment
import com.magamal.restaurantsmap.base.GenericRecyclerViewAdapter
import com.magamal.restaurantsmap.extentions.loadImage
import com.magamal.restaurantsmap.extentions.toGone
import com.magamal.restaurantsmap.extentions.toVisible
import com.restaurantsmap.presentation.models.restaurants.RestaurantView
import com.restaurantsmap.presentation.viewmodels.RestaurantsViewModel
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.android.synthetic.main.item_restaurant.view.*
import kotlinx.android.synthetic.main.progress.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import toast

/**
 * @author Mahmoud Gamal on 20/08/2021.
 */
class RestaurantsFragment : BaseFragment() {

    private val viewModel: RestaurantsViewModel by sharedViewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_restaurants

    override fun setupViews() {
        setupRecyclerView()
    }

    override fun setupObservers() {
        viewModel.observeOnRestaurants(
            owner = this,
            onLoading = {
                progressBar.toVisible()
            },
            onError = { throwable ->
                progressBar.toGone()
                toast(getMessageForThrowable(throwable))
            },
            onNewData = { newItems, allItems ->
                progressBar.toGone()
                adapter.addItems(
                    if (adapter.itemCount == 0)
                        allItems
                    else
                        newItems
                )
            }
        )
    }

    private fun setupRecyclerView() {
        recyclerViewRestaurants.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RestaurantsFragment.adapter
        }
    }

    private val adapter by lazy {
        object : GenericRecyclerViewAdapter<RestaurantView>() {
            override fun bindData(
                holder: RecyclerView.ViewHolder,
                position: Int,
                item: RestaurantView
            ) {
                holder.itemView.apply {
                    item.apply {
                        tvTitle.text = name
                        tvLocation.text =
                            String.format(
                                getString(R.string.location_format),
                                location?.address ?: "",
                                location?.crossStreet ?: "",
                                location?.city ?: ""
                            )
                        image.loadImage(photos?.first())
                    }
                    setOnClickListener { viewModel.selectRestaurantItem(item) }
                }
            }

            override fun getLayoutId(): Int = R.layout.item_restaurant

        }
    }


}