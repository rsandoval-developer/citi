package com.softtek.citi.di.module

import com.softtek.citi.presentation.ui.home.HomeCitiActivity
import com.softtek.citi.presentation.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindHomeCitiActivity(): HomeCitiActivity

}