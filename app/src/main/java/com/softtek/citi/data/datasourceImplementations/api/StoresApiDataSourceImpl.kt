package com.softtek.citi.data.datasourceImplementations.api


import com.softtek.citi.data.exceptions.ApiResponseHandler
import com.softtek.citi.data.services.api.StoresService
import com.softtek.citi.domain.dataSourceAbstractions.StoresDataSource
import com.softtek.citi.domain.mappers.CitiesMapper
import com.softtek.citi.domain.mappers.StoreMapper
import com.softtek.citi.domain.models.City
import com.softtek.citi.domain.models.Store
import io.reactivex.Observable
import javax.inject.Inject

class StoresApiDataSourceImpl @Inject constructor(
    private val storesService: StoresService,
    private val apiResponseHandler: ApiResponseHandler,
    private val storeMapper: StoreMapper,
    private val citiesMapper: CitiesMapper
) : StoresDataSource {

    override fun stores(token: String): Observable<List<Store>> =
        this.storesService.stores(
            "Bearer $token"
        ).flatMap { response ->
            this.apiResponseHandler.handle(response)
        }.flatMapIterable { it }
            .map { store ->
                this.storeMapper.mapFromApi(store)
            }.toList()
            .toObservable()

    override fun cities(token: String): Observable<List<City>> =
        this.storesService.cities(
            "Bearer $token"
        ).flatMap { response ->
            this.apiResponseHandler.handle(response)
        }.flatMapIterable { it }
            .map { city ->
                this.citiesMapper.mapFromApi(city)
            }.toList()
            .toObservable()
}