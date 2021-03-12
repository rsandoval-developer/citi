package com.softtek.citi.di.component

import android.app.Application
import com.softtek.citi.CitiApplication
import com.softtek.citi.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ActivityModule::class,
        FragmentModule::class,
        NetworkModule::class,
        AppModule::class]
)
interface AppComponent {

    fun inject(app: CitiApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}