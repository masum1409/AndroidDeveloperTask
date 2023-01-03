package com.masum.androiddevelopertask.data.repository.datasourceImpl

import com.masum.androiddevelopertask.data.db.ShopDao
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.repository.datasource.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val shopDao: ShopDao
) : LocalDataSource{
    override suspend fun addProduct(products: List<ShopItem>) {
        shopDao.addProduct(products)
    }
    override suspend fun getAllProducts()=shopDao.getProduct()
}