package com.shijo.travelmate.di

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.shijo.travelmate.R
import com.shijo.travelmate.data.db.CustomerDao
import com.shijo.travelmate.data.db.LocationDao
import com.shijo.travelmate.data.db.TravelMateDatabase
import com.shijo.travelmate.utils.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


/**
 * All the application level dependencies are provided through this module
 */
@Module
class AppModule {
    @Module
    companion object {

        @Singleton
        @JvmStatic
        @Provides
        fun provideDatabase(application: Application): TravelMateDatabase {
            return Room.databaseBuilder(
                application,
                TravelMateDatabase::class.java,
                "travelmate-db"
            ).build()
        }

        @Singleton
        @JvmStatic
        @Provides
        @Named("base-url")
        fun provideBaseUrl(): String {
            return BASE_URL
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideCustomerDao(travelMateDatabase: TravelMateDatabase): CustomerDao {
            return travelMateDatabase.customerDao()
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideLocationDao(travelMateDatabase: TravelMateDatabase): LocationDao {
            return travelMateDatabase.locationDao()
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)


        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideRequestManager(
            application: Application,
            requestOptions: RequestOptions
        ): RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)

        }


        @Singleton
        @JvmStatic
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient, @Named("base-url") baseUrl: String): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            return httpClient.build()
        }

    }


}