package com.masum.androiddevelopertask.data.repository.datasource

import com.masum.androiddevelopertask.data.model.Shop
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getAllProducts() : Response<Shop>
}