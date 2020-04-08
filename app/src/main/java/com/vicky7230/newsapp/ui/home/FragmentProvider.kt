package com.vicky7230.newsapp.ui.home

import com.vicky7230.newsapp.ui.news.NewsFragment
import com.vicky7230.newsapp.ui.news.NewsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [(NewsModule::class)])
    internal abstract fun provideNewsFragment(): NewsFragment
}