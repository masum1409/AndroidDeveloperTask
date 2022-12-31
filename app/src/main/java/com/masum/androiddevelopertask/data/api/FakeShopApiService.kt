package com.masum.androiddevelopertask.data.api

import com.masum.androiddevelopertask.data.model.Shop
import retrofit2.Response
import retrofit2.http.GET

interface FakeShopApiService {

    @GET("/products")
    suspend fun getAllProducts() : Response<Shop>
}