package com.softtek.citi.presentation.ui.home.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softtek.citi.domain.useCases.GetCitiesUseCase
import javax.inject.Inject

class CitiesViewModelFactory @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(
                GetCitiesUseCase::class.java
            )
            .newInstance(
                this.getCitiesUseCase
            )
    }
}