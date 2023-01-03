package com.masum.androiddevelopertask.di

import com.masum.androiddevelopertask.data.api.FakeShopApiService
import com.masum.androiddevelopertask.data.db.ShopDao
import com.masum.androiddevelopertask.data.repository.datasource.LocalDataSource
import com.masum.androiddevelopertask.data.repository.datasource.RemoteDataSource
import com.masum.androiddevelopertask.data.repository.datasourceImpl.LocalDataSourceImpl
import com.masum.androiddevelopertask.data.repository.datasourceImpl.RemoteDataSourceImpl
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
        RemoteDataSourceImpl (api)

    @Singleton
    @Provides
    fun provideLocalDataSource(shopDao: ShopDao) : LocalDataSource=
        LocalDataSourceImpl(shopDao)
}