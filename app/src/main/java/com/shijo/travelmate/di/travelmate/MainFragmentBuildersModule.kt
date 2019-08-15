package com.shijo.travelmate.di.travelmate

import com.shijo.travelmate.ui.travelmate.locationdetail.LocationDetailFragment
import com.shijo.travelmate.ui.travelmate.locationlist.LocationListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLocationListFragment(): LocationListFragment

    @ContributesAndroidInjector
    abstract fun contributeLoactionDetailFragment(): LocationDetailFragment
}