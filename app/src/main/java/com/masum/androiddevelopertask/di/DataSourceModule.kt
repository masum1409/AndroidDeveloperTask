package com.masum.androiddevelopertask.di

import com.masum.androiddevelopertask.data.api.FakeShopApiService
import com.masum.androiddevelopertask.data.repository.datasource.RemoteDataSource
import com.masum.androiddevelopertask.data.repository.datasourceImpl.ShopRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(api :FakeShopApiService) : RemoteDataSource =
        ShopRemoteDataSourceImpl (api)
}