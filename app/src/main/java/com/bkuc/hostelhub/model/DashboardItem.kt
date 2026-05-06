package com.bkuc.hostelhub.model

data class DashboardItem(
    val id: Int,
    val title: String,
    val count: String,
    val trend:String,
    val iconRes: Int,
    val backgroundColor: Int? = null
)