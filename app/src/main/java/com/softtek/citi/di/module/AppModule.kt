package com.softtek.citi.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import com.softtek.citi.data.services.local.AppPreferences
import com.softtek.citi.presentation.utils.ResourceProvider
import com.softtek.citi.presentation.utils.Utils
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppUtil(app: Application): Utils = Utils(app)


    @Provides
    @Singleton
    fun provideAppPreferences(app: Application): AppPreferences =
        AppPreferences(app)


    @Provides
    @Singleton
    fun provideResource(app: Application): ResourceProvider = ResourceProvider(app)

}
