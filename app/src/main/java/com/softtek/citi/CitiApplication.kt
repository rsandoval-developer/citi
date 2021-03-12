package com.softtek.citi

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.softtek.citi.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class CitiApplication : MultiDexApplication(), HasSupportFragmentInjector,
    HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        this.initDagger()
    }

    override fun supportFragmentInjector() = supportFragmentInjector

    override fun activityInjector() = dispatchingActivityInjector

    private fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

}