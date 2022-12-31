package com.masum.androiddevelopertask.data.repository

import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.repository.datasource.RemoteDataSource
import com.masum.androiddevelopertask.data.util.Resource
import com.masum.androiddevelopertask.domain.repository.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ShopRepository {
    override suspend fun getAllProducts(): Resource<Shop> {
        val response = remoteDataSource.getAllProducts()
        if (response.isSuccessful){
            response.body()?.let {result ->
                return Resource.Success(result)
            }
        }

            return Resource.Error(message = "${response.errorBody()?.string()}")

    }
}