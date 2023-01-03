package com.masum.androiddevelopertask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.ShopItem

@Database(
    entities = [ShopItem::class,CartItem::class], version = 1, exportSchema = false
)
abstract class ShopDatabase : RoomDatabase(){
    abstract fun shopDao(): ShopDao
}