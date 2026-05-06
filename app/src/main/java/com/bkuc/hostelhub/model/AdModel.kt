package com.bkuc.hostelhub.model

data class AdModel(
    val id: String = "",
    val title: String,
    val subtitle: String,
    val imageUrl: String? = null,
    var isActive: Boolean = true
)