package com.masum.androiddevelopertask.domain.usecase

import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.util.Resource
import com.masum.androiddevelopertask.domain.repository.ShopRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ShopRepository
){
    suspend fun getAllProducts() : Resource<List<ShopItem>> =
        repository.getAllProducts()
}