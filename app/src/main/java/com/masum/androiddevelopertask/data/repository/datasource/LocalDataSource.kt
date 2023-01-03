package com.masum.androiddevelopertask.data.repository.datasource

import com.masum.androiddevelopertask.data.model.ShopItem
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun addProduct(products: List<ShopItem>)
    suspend fun getAllProducts(): List<ShopItem>
}