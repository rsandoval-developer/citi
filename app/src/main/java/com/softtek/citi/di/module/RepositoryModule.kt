package com.softtek.citi.di.module

import com.softtek.citi.data.reposotoryImplementations.AuthRepositoryImpl
import com.softtek.citi.data.reposotoryImplementations.StoresRepositoryImpl
import com.softtek.citi.domain.repositoryAbstractions.AuthRepository
import com.softtek.citi.domain.repositoryAbstractions.StoresRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository =
        authRepositoryImpl

    @Provides
    @Singleton
    fun provideStoresRepository(storesRepositoryImpl: StoresRepositoryImpl): StoresRepository =
        storesRepositoryImpl

}