package com.softtek.citi.domain.models

data class Store(
    val address: String,
    val city: String,
    val cityId: Int,
    val description: String,
    val id: Int,
    val isActive: Boolean,
    val name: String,
    val latitude: String,
    val longitude: String
)