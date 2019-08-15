package com.shijo.travelmate.di.travelmate

import androidx.lifecycle.ViewModel
import com.shijo.travelmate.di.ViewModelKey
import com.shijo.travelmate.ui.travelmate.locationdetail.LocationDetailViewModel
import com.shijo.travelmate.ui.travelmate.locationlist.LocationListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(LocationListViewModel::class)
    abstract fun bindLocationListViewModel(locationListViewModel: LocationListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationDetailViewModel::class)
    abstract fun bindLocationDetailViewModel(locationDetailViewModel: LocationDetailViewModel): ViewModel
}