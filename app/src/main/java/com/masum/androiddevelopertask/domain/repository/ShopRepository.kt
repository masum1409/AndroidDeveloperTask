package com.masum.androiddevelopertask.domain.repository

import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface ShopRepository {
    suspend fun  getAllProducts(): Flow<Resource<List<ShopItem>>>
    suspend fun  getAllLocalProducts(): Flow<List<ShopItem>>
    suspend fun  getFilteredProducts(query : String): Flow<List<ShopItem>>
    suspend fun  getFilteredCart(query : String): Flow<List<CartItem>>
    suspend fun addCart(cartItem: CartItem)
     fun getAllCartItems(): Flow<List<CartItem>>

}