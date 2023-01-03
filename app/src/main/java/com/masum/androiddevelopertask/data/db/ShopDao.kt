package com.masum.androiddevelopertask.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.ShopItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: List<ShopItem>)

    @Query("select * from shopItem")
    suspend fun getProduct() : List<ShopItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cart: CartItem)

    @Query("select * from cart")
     fun getAllCart() : Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(cart: CartItem)

}