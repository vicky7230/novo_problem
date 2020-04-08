package com.vicky7230.newsapp.di.module

import android.app.Application
import android.content.Context
import com.vicky7230.newsapp.MyApplication
import com.vicky7230.newsapp.data.AppDataManager
import com.vicky7230.newsapp.data.Config
import com.vicky7230.newsapp.data.DataManager
import com.vicky7230.newsapp.data.network.ApiHelper
import com.vicky7230.newsapp.data.network.AppApiHelper
import com.vicky7230.newsapp.di.ApplicationContext
import com.vicky7230.newsapp.di.BaseUrl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(myApplication: MyApplication): Context {
        return myApplication.applicationContext
    }

    @Provides
    internal fun provideApplication(myApplication: MyApplication): Application {
        return myApplication
    }


    @Provides
    @BaseUrl
    internal fun provideBaseUrl(): String {
        return Config.BASE_URL
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }
}