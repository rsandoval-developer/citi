package com.softtek.citi.data.reposotoryImplementations

import com.softtek.citi.domain.dataSourceAbstractions.AuthDataSource
import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.domain.models.responseObjects.auth.AuthResponseObject
import com.softtek.citi.domain.repositoryAbstractions.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authDataSource: AuthDataSource) :
    AuthRepository {

    override fun auth(authRequestObject: AuthRequestObject): Observable<AuthResponseObject> =
        this.authDataSource.auth(authRequestObject)
}