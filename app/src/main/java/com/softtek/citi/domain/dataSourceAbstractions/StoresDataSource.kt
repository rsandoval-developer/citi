package com.softtek.citi.domain.dataSourceAbstractions

import com.softtek.citi.domain.models.City
import com.softtek.citi.domain.models.Store
import io.reactivex.Observable

interface StoresDataSource {

    fun stores(token: String): Observable<List<Store>>

    fun cities(token: String): Observable<List<City>>

}