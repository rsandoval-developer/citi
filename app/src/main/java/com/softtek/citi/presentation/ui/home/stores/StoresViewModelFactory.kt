package com.softtek.citi.presentation.ui.home.stores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softtek.citi.domain.useCases.GetStoresUseCase
import javax.inject.Inject

class StoresViewModelFactory @Inject constructor(
    private val getStoresUseCase: GetStoresUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(
                GetStoresUseCase::class.java
            )
            .newInstance(
                this.getStoresUseCase
            )
    }
}