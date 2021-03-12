package com.softtek.citi.domain.repositoryAbstractions

import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.domain.models.responseObjects.auth.AuthResponseObject
import io.reactivex.Observable

interface AuthRepository {

    fun auth(authRequestObject: AuthRequestObject): Observable<AuthResponseObject>

}