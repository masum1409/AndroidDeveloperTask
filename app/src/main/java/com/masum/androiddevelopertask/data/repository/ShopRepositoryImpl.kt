package com.masum.androiddevelopertask.data.repository

import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.repository.datasource.LocalDataSource
import com.masum.androiddevelopertask.data.repository.datasource.RemoteDataSource
import com.masum.androiddevelopertask.data.util.Resource
import com.masum.androiddevelopertask.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ShopRepository {
    override suspend fun getAllProducts(): Flow<Resource<List<ShopItem>>> {

        return flow {
            emit(Resource.Loading(localDataSource.getAllProducts()))
            val response = remoteDataSource.getAllProducts()
            try {
                if (response.isSuccessful){
                    response.body()?.let {result ->
                        localDataSource.addProduct(result)
                        emit(Resource.Success(result))
                    }
                }
                else emit(Resource.Error(message = "${response.errorBody()?.string()}"))
            } catch (e: Exception) {
                 emit(Resource.Error(message = "${response.errorBody()?.string()}"))
            }


        }

    }

    override suspend fun getAllLocalProducts(): List<ShopItem> =
        localDataSource.getAllProducts()


    override suspend fun addCart(cartItem: CartItem) =
        localDataSource.addToCart(cartItem)

    override  fun getAllCartItems(): Flow<List<CartItem>> =
        localDataSource.getAllCart()
}