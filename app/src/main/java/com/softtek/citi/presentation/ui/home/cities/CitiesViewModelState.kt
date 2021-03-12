package com.softtek.citi.presentation.ui.home.cities

import com.softtek.citi.domain.models.City


sealed class CitiesViewModelState {

    data class ProgressVisibility(val visibility: Int) : CitiesViewModelState()

    data class CitiesSucces(val cities: List<City>) : CitiesViewModelState()

    data class ErrorState(val error: String) : CitiesViewModelState()
}