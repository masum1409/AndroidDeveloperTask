package com.masum.androiddevelopertask.data.repository.datasourceImpl

import com.masum.androiddevelopertask.data.db.ShopDao
import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.repository.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val shopDao: ShopDao
) : LocalDataSource{
    override suspend fun addProduct(products: List<ShopItem>) =
        shopDao.addProduct(products)

    override suspend fun getAllProducts()=
        shopDao.getProduct()
    override suspend fun addToCart(cartItem: CartItem) {
        if(cartItem.quantity>0)
        shopDao.addToCart(cartItem)
        else shopDao.removeFromCart(cartItem)
    }

    override  fun getAllCart(): Flow<List<CartItem>> =
        shopDao.getAllCart()

}