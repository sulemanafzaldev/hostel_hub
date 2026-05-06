package com.bkuc.hostelhub.model

data class TopHostel(
    val id: String,
    val imageRes: Int,
    val title: String,
    val rating: String,
    val location: String,
    val price: String,
    val seaterType: String
)