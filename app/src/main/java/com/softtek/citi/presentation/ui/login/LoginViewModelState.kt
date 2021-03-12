package com.softtek.citi.presentation.ui.login


sealed class LoginViewModelState {

    data class ProgressVisibility(val visibility: Int) : LoginViewModelState()

    data class LoginSucces(val succes: Boolean) : LoginViewModelState()

    data class ErrorState(val error: String) : LoginViewModelState()

    data class EmailErrorState(val error: Int) : LoginViewModelState()

    data class PasswordErrorState(val error: Int) : LoginViewModelState()
}