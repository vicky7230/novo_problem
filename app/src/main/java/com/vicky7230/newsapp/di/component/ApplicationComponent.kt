package com.vicky7230.newsapp.di.component

import com.vicky7230.newsapp.MyApplication
import com.vicky7230.newsapp.di.module.ActivityBindingModule
import com.vicky7230.newsapp.di.module.ApplicationModule
import com.vicky7230.newsapp.di.module.NetworkModule
import com.vicky7230.newsapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(myApplication: MyApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(myApplication: MyApplication)
}