package com.bkuc.hostelhub.model

import android.media.Image


data class FeaturedOffer(
    val image: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val offerPercent: String,
    val badgeText: String
)