package com.softtek.citi.domain.repositoryAbstractions

import com.softtek.citi.domain.models.City
import com.softtek.citi.domain.models.Store
import io.reactivex.Observable

interface StoresRepository {

    fun stores(token: String): Observable<List<Store>>

    fun cities(token: String): Observable<List<City>>

}