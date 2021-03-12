package com.softtek.citi.domain.models.requestObjects

import com.google.gson.annotations.SerializedName

data class AuthRequestObject(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
