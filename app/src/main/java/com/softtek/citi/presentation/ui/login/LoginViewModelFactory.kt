package com.softtek.citi.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softtek.citi.data.services.local.AppPreferences
import com.softtek.citi.domain.useCases.AuthUseCase
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val appPreferences: AppPreferences
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(
                AuthUseCase::class.java,
                AppPreferences::class.java
            )
            .newInstance(
                this.authUseCase,
                this.appPreferences
            )
    }
}