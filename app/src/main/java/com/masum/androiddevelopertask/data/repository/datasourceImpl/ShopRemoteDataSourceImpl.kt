package com.masum.androiddevelopertask.data.repository.datasourceImpl

import com.masum.androiddevelopertask.data.api.FakeShopApiService
import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.repository.datasource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ShopRemoteDataSourceImpl @Inject constructor(
    private val api : FakeShopApiService
): RemoteDataSource {
    override suspend fun getAllProducts(): Response<Shop> =
        api.getAllProducts()
}