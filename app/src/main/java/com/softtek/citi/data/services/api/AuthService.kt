package com.softtek.citi.data.services.api

import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.domain.models.responseObjects.auth.AuthResponseObject
import com.softtek.citi.AppConstants
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(AppConstants.API_AUTH)
    fun auth(@Body authRequestObject: AuthRequestObject): Observable<Response<AuthResponseObject>>
}