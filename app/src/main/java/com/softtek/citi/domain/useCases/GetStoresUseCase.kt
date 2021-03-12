package com.softtek.citi.domain.useCases

import com.softtek.citi.AppConstants
import com.softtek.citi.data.services.local.AppPreferences
import com.softtek.citi.domain.models.Store
import com.softtek.citi.domain.repositoryAbstractions.StoresRepository
import com.softtek.citi.domain.useCases.base.SingleUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetStoresUseCase @Inject constructor(
    private val storesRepository: StoresRepository,
    private val appPreferences: AppPreferences
) :
    SingleUseCase<List<Store>>() {

    override fun buildUseCaseObservable(): Observable<List<Store>> =
        this.storesRepository.stores(this.appPreferences.get(AppConstants.PRE_TOKEN, ""))
}