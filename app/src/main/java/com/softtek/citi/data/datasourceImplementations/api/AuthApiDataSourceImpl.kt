package com.softtek.citi.data.datasourceImplementations.api


import com.softtek.citi.data.exceptions.ApiResponseHandler
import com.softtek.citi.data.services.api.AuthService
import com.softtek.citi.domain.dataSourceAbstractions.AuthDataSource
import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.domain.models.responseObjects.auth.AuthResponseObject
import io.reactivex.Observable
import javax.inject.Inject

class AuthApiDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val apiResponseHandler: ApiResponseHandler
) : AuthDataSource {

    override fun auth(authRequestObject: AuthRequestObject): Observable<AuthResponseObject> =
        this.authService.auth(
            authRequestObject
        ).flatMap { response ->
            this.apiResponseHandler.handle(response)
        }

}