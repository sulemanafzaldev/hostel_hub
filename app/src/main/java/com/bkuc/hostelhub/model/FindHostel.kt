package com.bkuc.hostelhub.model

data class FindHostel(
    val id: Int,
    val imageUrl: Int,
    val rating: String,
    val seaterTag: String,
    val title: String,
    val price: String,
    val location: String,
    val hasWifi: Boolean = true,
    val hasAc: Boolean = true,
    val hasParking: Boolean = true,
    var isFavorite: Boolean = false
)