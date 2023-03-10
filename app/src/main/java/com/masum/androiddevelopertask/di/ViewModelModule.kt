package com.masum.androiddevelopertask.di

import android.app.Application
import com.masum.androiddevelopertask.domain.repository.ShopRepository
import com.masum.androiddevelopertask.presentation.viewmodel.CartViewModel
import com.masum.androiddevelopertask.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
@Singleton
@Provides
fun provideHomeViewModel(app: Application, shopRepository: ShopRepository) =
    HomeViewModel(app, shopRepository)

@Singleton
@Provides
fun provideCartViewModel(app: Application, shopRepository: ShopRepository) =
    CartViewModel(app, shopRepository)
}