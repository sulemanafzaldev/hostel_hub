package com.bkuc.hostelhub.model

data class SaveItemModel (
    val id: String,
    val imageUrl: String, // Image URL ya drawable resource ID ke liye
    val title: String,
    val location: String,
    val price: String,
    val rating: String,
    var isFavorite: Boolean = true

)