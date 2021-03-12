package com.softtek.citi.domain.useCases

import com.softtek.citi.AppConstants
import com.softtek.citi.data.services.local.AppPreferences
import com.softtek.citi.domain.models.City
import com.softtek.citi.domain.repositoryAbstractions.StoresRepository
import com.softtek.citi.domain.useCases.base.SingleUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val storesRepository: StoresRepository,
    private val appPreferences: AppPreferences
) :
    SingleUseCase<List<City>>() {

    override fun buildUseCaseObservable(): Observable<List<City>> =
        this.storesRepository.cities(this.appPreferences.get(AppConstants.PRE_TOKEN, ""))
}