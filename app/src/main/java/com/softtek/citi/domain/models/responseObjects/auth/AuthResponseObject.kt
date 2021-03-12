package com.softtek.citi.domain.models.responseObjects.auth

import com.google.gson.annotations.SerializedName

data class AuthResponseObject(

    @SerializedName("token")
    private val _token: String? = null
) {

    val token: String
        get() = this._token ?: ""
}


