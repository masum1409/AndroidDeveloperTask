package com.masum.androiddevelopertask.domain.repository

import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.util.Resource


interface ShopRepository {
    suspend fun  getAllProducts(): Resource<List<ShopItem>>
}