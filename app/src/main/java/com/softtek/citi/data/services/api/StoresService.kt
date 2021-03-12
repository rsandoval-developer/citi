package com.softtek.citi.data.services.api

import com.softtek.citi.AppConstants
import com.softtek.citi.domain.models.responseObjects.cities.CitiesResponseObject
import com.softtek.citi.domain.models.responseObjects.stores.StoresResponseObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface StoresService {

    @GET(AppConstants.API_STORES)
    fun stores(
        @Header("authorization") token: String
    ): Observable<Response<StoresResponseObject>>

    @GET(AppConstants.API_CITIES)
    fun cities(
        @Header("authorization") token: String
    ): Observable<Response<CitiesResponseObject>>
}