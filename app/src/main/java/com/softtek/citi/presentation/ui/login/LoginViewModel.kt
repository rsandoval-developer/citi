package com.softtek.citi.presentation.ui.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.softtek.citi.AppConstants.Companion.PRE_TOKEN
import com.softtek.citi.data.services.local.AppPreferences
import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.domain.models.responseObjects.auth.AuthResponseObject
import com.softtek.citi.domain.useCases.AuthUseCase
import com.softtek.citi.presentation.ui.viewmodels.base.ViewModelBase

class LoginViewModel(
    private val authUseCase: AuthUseCase,
    private val appPreferences: AppPreferences
) : ViewModelBase() {

    val loginViewModelState = MutableLiveData<LoginViewModelState>()

    fun auth(authRequestObject: AuthRequestObject) {
        this.loginViewModelState.value =
            LoginViewModelState.ProgressVisibility(
                View.VISIBLE
            )
        this.authUseCase.setRequest(authRequestObject)
        this.authUseCase.execute(
            onSuccess = ::handleLogin,
            onError = ::handleError
        )

    }

    private fun handleLogin(authResponseObject: AuthResponseObject) {
        this.appPreferences.put(PRE_TOKEN, authResponseObject.token)
        this.loginViewModelState.value = LoginViewModelState.LoginSucces(authResponseObject.token.isNotEmpty())
    }


    override fun defaultError(error: Throwable) {
        this.loginViewModelState.value = LoginViewModelState.ProgressVisibility(View.GONE)
        this.loginViewModelState.value = LoginViewModelState.ErrorState(error.message ?: "")
    }

    override fun onCleared() {
        this.authUseCase.dispose()
        super.onCleared()
    }

}