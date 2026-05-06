package com.bkuc.hostelhub.model

data class RoomModel(
    val id: String = "",
    val roomName: String,
    val roomDetails: String, // e.g., "4-seater · Pkr-7,800/mo"
    val imageUri: String? = null, // Path for the room image
    val isActive: Boolean = true
)