package com.masum.androiddevelopertask.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.android.gms.analytics.ecommerce.Product
import com.masum.androiddevelopertask.data.model.ShopItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: List<ShopItem>)

    @Query("select * from shopItem")
    suspend fun getProduct() : List<ShopItem>

}