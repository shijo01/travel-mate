package com.shijo.travelmate.di

import androidx.lifecycle.ViewModelProvider
import com.shijo.travelmate.utils.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract fun bindViewModelFactory(
        viewModelProviderFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory
}