package com.softtek.citi.domain.models.responseObjects.cities


import com.google.gson.annotations.SerializedName

class CitiesResponseObject : ArrayList<CitiesResponseObjectItem>()

data class CitiesResponseObjectItem(
    @SerializedName("id")
    val _id: Int?,
    @SerializedName("isActive")
    val _isActive: Boolean?,
    @SerializedName("name")
    val _name: String?,
    @SerializedName("state")
    val _state: String?,
    @SerializedName("stateId")
    val _stateId: Int?
) {

    val id: Int
        get() = this._id ?: 0

    val isActive: Boolean
        get() = this._isActive ?: false

    val name: String
        get() = this._name ?: ""

    val state: String
        get() = this._state ?: ""

    val stateId: Int
        get() = this._stateId ?: 0

}