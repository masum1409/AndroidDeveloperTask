package com.masum.androiddevelopertask.data.repository.datasource

import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.ShopItem
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun addProduct(products: List<ShopItem>)
     fun getAllProducts(): Flow<List<ShopItem>>
     fun getFilteredProducts(query : String): Flow<List<ShopItem>>
     fun getFilteredCart(query : String): Flow<List<CartItem>>
    suspend fun addToCart(cartItem: CartItem)
     fun getAllCart() : Flow<List<CartItem>>
}