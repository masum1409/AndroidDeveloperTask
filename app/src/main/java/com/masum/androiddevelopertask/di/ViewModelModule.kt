package com.masum.androiddevelopertask.di

import android.app.Application
import com.masum.androiddevelopertask.data.repository.datasource.LocalDataSource
import com.masum.androiddevelopertask.domain.usecase.ProductUseCase
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
fun provideHomeViewModel(app: Application, productUseCase: ProductUseCase,localDataSource: LocalDataSource) =
    HomeViewModel(app, productUseCase,localDataSource)
}