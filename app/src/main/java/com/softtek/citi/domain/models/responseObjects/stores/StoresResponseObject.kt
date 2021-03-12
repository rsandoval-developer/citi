package com.softtek.citi.domain.models.responseObjects.stores

import com.google.gson.annotations.SerializedName

class StoresResponseObject : ArrayList<StoresResponseObjectItem>()

data class StoresResponseObjectItem(
    @SerializedName("address")
    val address: Address,
    @SerializedName("city")
    val city: String,
    @SerializedName("cityId")
    val cityId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("paymentMethod")
    val paymentMethod: List<Any>
)

data class Address(
    @SerializedName("colony")
    val colony: String,
    @SerializedName("exteriorNumber")
    val exteriorNumber: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("street")
    val street: String
)