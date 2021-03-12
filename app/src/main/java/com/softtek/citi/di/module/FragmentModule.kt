package com.softtek.citi.di.module

import com.softtek.citi.presentation.ui.home.cities.CitiesFragment
import com.softtek.citi.presentation.ui.home.stores.StoresFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindStoresFragment(): StoresFragment

    @ContributesAndroidInjector
    abstract fun bindCitiesFragment(): CitiesFragment

}
