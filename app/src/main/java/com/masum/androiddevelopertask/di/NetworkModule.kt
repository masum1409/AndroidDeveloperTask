package com.masum.androiddevelopertask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.masum.androiddevelopertask.BuildConfig
import com.masum.androiddevelopertask.data.api.FakeShopApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    fun providesFakeShopApiService(retrofit : Retrofit) : FakeShopApiService {
        return retrofit.create(FakeShopApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
}
