package com.shijo.travelmate.di.travelmate

import com.bumptech.glide.RequestManager
import com.shijo.travelmate.data.network.TravelMateApi
import com.shijo.travelmate.ui.travelmate.locationlist.LocationListAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    @Module
    companion object {
        @MainScope
        @Provides
        @JvmStatic
        fun provideApi(retrofit: Retrofit): TravelMateApi {
            return retrofit.create(TravelMateApi::class.java)
        }

        @MainScope
        @Provides
        @JvmStatic
        fun provideLocationListAdapter(requestManager: RequestManager): LocationListAdapter {
            return LocationListAdapter(requestManager)
        }
    }
}