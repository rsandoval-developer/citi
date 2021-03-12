package com.softtek.citi.domain.models

data class City(
    val id: Int,
    val isActive: Boolean,
    val name: String,
    val state: String,
    val stateId: Int
)