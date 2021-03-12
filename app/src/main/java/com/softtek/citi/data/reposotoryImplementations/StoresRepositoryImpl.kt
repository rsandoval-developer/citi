package com.softtek.citi.data.reposotoryImplementations

import com.softtek.citi.domain.dataSourceAbstractions.StoresDataSource
import com.softtek.citi.domain.models.City
import com.softtek.citi.domain.models.Store
import com.softtek.citi.domain.repositoryAbstractions.StoresRepository
import io.reactivex.Observable
import javax.inject.Inject

class StoresRepositoryImpl @Inject constructor(private val storesDataSource: StoresDataSource) :
    StoresRepository {

    override fun stores(token: String): Observable<List<Store>> =
        this.storesDataSource.stores(token)

    override fun cities(token: String): Observable<List<City>> =
        this.storesDataSource.cities(token)

}