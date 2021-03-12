package com.softtek.citi.presentation.ui.home.cities

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.softtek.citi.domain.models.City
import com.softtek.citi.domain.useCases.GetCitiesUseCase
import com.softtek.citi.presentation.ui.viewmodels.base.ViewModelBase

class CitiesViewModel(private val getCitiesUseCase: GetCitiesUseCase) : ViewModelBase() {

    val citiesViewModelState = MutableLiveData<CitiesViewModelState>()

    fun getCities() {
        this.citiesViewModelState.value = CitiesViewModelState.ProgressVisibility(View.VISIBLE)
        this.getCitiesUseCase.execute(
            onSuccess = ::handleGetCities,
            onError = ::handleError
        )
    }

    private fun handleGetCities(list: List<City>) {
        this.citiesViewModelState.value = CitiesViewModelState.ProgressVisibility(View.GONE)
        this.citiesViewModelState.value = CitiesViewModelState.CitiesSucces(list)
    }


    override fun defaultError(error: Throwable) {
        this.citiesViewModelState.value = CitiesViewModelState.ProgressVisibility(View.GONE)
        this.citiesViewModelState.value = CitiesViewModelState.ErrorState(error.message ?: "")

    }

    override fun onCleared() {
        this.getCitiesUseCase.dispose()
        super.onCleared()
    }
}