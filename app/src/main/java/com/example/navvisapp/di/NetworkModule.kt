package com.example.navvisapp.di

import com.example.navvisapp.api.NavVisApi
import com.example.navvisapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() :Retrofit{
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(Constants.BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit):NavVisApi{
        return retrofit.create(NavVisApi::class.java)
    }
}