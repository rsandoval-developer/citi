package com.softtek.citi.presentation.ui.home.stores

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.softtek.citi.domain.models.Store
import com.softtek.citi.domain.useCases.GetStoresUseCase
import com.softtek.citi.presentation.ui.viewmodels.base.ViewModelBase

class StoresViewModel(private val getStoresUseCase: GetStoresUseCase) : ViewModelBase() {

    val storesViewModelState = MutableLiveData<StoresViewModelState>()

    fun getStores() {
        this.storesViewModelState.value = StoresViewModelState.ProgressVisibility(View.VISIBLE)
        this.getStoresUseCase.execute(
            onSuccess = ::handleGetStores,
            onError = ::handleError
        )
    }

    private fun handleGetStores(list: List<Store>) {
        this.storesViewModelState.value = StoresViewModelState.ProgressVisibility(View.GONE)
        this.storesViewModelState.value = StoresViewModelState.StoresSucces(list)
    }


    override fun defaultError(error: Throwable) {
        this.storesViewModelState.value = StoresViewModelState.ProgressVisibility(View.GONE)
        this.storesViewModelState.value = StoresViewModelState.ErrorState(error.message ?: "")

    }

    override fun onCleared() {
        this.getStoresUseCase.dispose()
        super.onCleared()
    }
}