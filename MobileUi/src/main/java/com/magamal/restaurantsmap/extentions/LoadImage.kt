package com.magamal.restaurantsmap.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.magamal.restaurantsmap.R
import com.restaurantsmap.presentation.models.restaurants.PhotoItemDetailsView

/**
 * Created by Mahmoud Gamal on 2019-09-07.
 */

fun ImageView.loadImage(url: String?) {
    Glide
        .with(context)
        .load(url)
        .error(R.drawable.boarder)
        .placeholder(R.drawable.boarder)
        .into(this)
}

fun ImageView.loadImage(photoItemDetailsView: PhotoItemDetailsView?) {
    loadImage(photoItemDetailsView?.buildImgUrl())
}