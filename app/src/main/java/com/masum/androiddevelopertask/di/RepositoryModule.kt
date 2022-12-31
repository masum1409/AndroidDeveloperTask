package com.masum.androiddevelopertask.di

import com.masum.androiddevelopertask.data.repository.ShopRepositoryImpl
import com.masum.androiddevelopertask.data.repository.datasource.RemoteDataSource
import com.masum.androiddevelopertask.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideShopRepository(remoteDataSource: RemoteDataSource): ShopRepository =
        ShopRepositoryImpl(remoteDataSource)
}