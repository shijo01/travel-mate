package com.shijo.travelmate.di

import com.shijo.travelmate.di.travelmate.MainFragmentBuildersModule
import com.shijo.travelmate.di.travelmate.MainModule
import com.shijo.travelmate.di.travelmate.MainScope
import com.shijo.travelmate.di.travelmate.MainViewModelsModule
import com.shijo.travelmate.ui.travelmate.TravelMateActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contributeTravelMateActivity(): TravelMateActivity
}