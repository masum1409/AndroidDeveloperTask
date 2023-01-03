package com.masum.androiddevelopertask.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.masum.androiddevelopertask.data.db.ShopDao
import com.masum.androiddevelopertask.data.db.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application)=
        Room.databaseBuilder(app,ShopDatabase::class.java, "shop_database")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideShopDao(database: ShopDatabase)=
        database.shopDao()
}