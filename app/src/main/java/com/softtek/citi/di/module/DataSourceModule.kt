package com.softtek.citi.di.module

import com.softtek.citi.data.datasourceImplementations.api.AuthApiDataSourceImpl
import com.softtek.citi.data.datasourceImplementations.api.StoresApiDataSourceImpl
import com.softtek.citi.data.services.api.AuthService
import com.softtek.citi.data.services.api.StoresService
import com.softtek.citi.domain.dataSourceAbstractions.AuthDataSource
import com.softtek.citi.domain.dataSourceAbstractions.StoresDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideStoreService(retrofit: Retrofit): StoresService =
        retrofit.create(StoresService::class.java)

    @Provides
    @Singleton
    fun provideAuthDataSource(authApiDataSourceImpl: AuthApiDataSourceImpl): AuthDataSource =
        authApiDataSourceImpl

    @Provides
    @Singleton
    fun provideStoresDataSource(storesApiDataSourceImpl: StoresApiDataSourceImpl): StoresDataSource =
        storesApiDataSourceImpl
}