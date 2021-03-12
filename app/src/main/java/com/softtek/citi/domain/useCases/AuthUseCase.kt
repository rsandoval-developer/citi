package com.softtek.citi.domain.useCases

import com.softtek.citi.R
import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.domain.models.responseObjects.auth.AuthResponseObject
import com.softtek.citi.domain.repositoryAbstractions.AuthRepository
import com.softtek.citi.domain.useCases.base.SingleUseCase
import com.softtek.citi.isEmailValid
import com.softtek.citi.presentation.utils.ResourceProvider
import io.reactivex.Observable
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceProvider: ResourceProvider
) :
    SingleUseCase<AuthResponseObject>() {

    private lateinit var authRequestObject: AuthRequestObject

    fun setRequest(authRequestObject: AuthRequestObject) {
        this.authRequestObject = authRequestObject
    }

    override fun buildUseCaseObservable(): Observable<AuthResponseObject> =
        when {
            this.authRequestObject.email.isBlank() && this.authRequestObject.password.isBlank() -> Observable.error(
                Throwable(
                    this.resourceProvider.getString(
                        R.string.login_text_invalid_email_and_password
                    )
                )
            )
            !this.authRequestObject.email.isEmailValid() -> Observable.error(
                Throwable(
                    resourceProvider.getString(
                        R.string.login_text_invalid_email
                    )
                )
            )
            this.authRequestObject.password.isBlank() -> Observable.error(
                Throwable(
                    this.resourceProvider.getString(
                        R.string.login_text_invalid_password
                    )
                )
            )
            else -> this.authRepository.auth(authRequestObject)
        }


}