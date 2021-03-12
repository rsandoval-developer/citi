package com.softtek.citi.domain.dataSourceAbstractions

import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.domain.models.responseObjects.auth.AuthResponseObject
import io.reactivex.Observable

interface AuthDataSource {

    fun auth(authRequestObject: AuthRequestObject): Observable<AuthResponseObject>

}