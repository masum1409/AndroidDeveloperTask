package com.masum.androiddevelopertask.di

import com.masum.androiddevelopertask.presentation.adapter.CartAdapter
import com.masum.androiddevelopertask.presentation.adapter.HomeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideHomeAdapter() =HomeAdapter()

    @Singleton
    @Provides
    fun provideCartAdapter() =CartAdapter()
}