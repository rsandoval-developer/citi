package com.softtek.citi.presentation.ui.home.stores

import com.softtek.citi.domain.models.Store


sealed class StoresViewModelState {

    data class ProgressVisibility(val visibility: Int) : StoresViewModelState()

    data class StoresSucces(val stores: List<Store>) : StoresViewModelState()

    data class ErrorState(val error: String) : StoresViewModelState()
}