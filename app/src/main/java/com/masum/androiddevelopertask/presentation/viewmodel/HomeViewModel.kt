package com.masum.androiddevelopertask.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.repository.datasource.LocalDataSource
import com.masum.androiddevelopertask.data.util.Network.isNetworkAvailable
import com.masum.androiddevelopertask.data.util.Resource
import com.masum.androiddevelopertask.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app : Application,
    private  val shopRepository: ShopRepository
) : AndroidViewModel(app){
    val products : MutableLiveData<Resource<List<ShopItem>>> = MutableLiveData()

    init {
        getAllLocalProducts()
        getAllProducts()
    }


    fun getFilteredProducts(query: String) = viewModelScope.launch {
        shopRepository.getFilteredProducts(query).collectLatest {
            products.postValue(Resource.Success(it))
        }
    }
    fun addToCart(cartItem: CartItem)=viewModelScope.launch {
        shopRepository.addCart(cartItem)
    }
    private fun getAllLocalProducts() = viewModelScope.launch {
        shopRepository.getAllLocalProducts().collectLatest {
            products.postValue(Resource.Loading(it))
        }
    }
     fun getAllProducts() =viewModelScope.launch {
        try {
            if(isNetworkAvailable(app)) {
               shopRepository.getAllProducts().collectLatest {
                   products.postValue(it)
               }
            } else {
                products.postValue(Resource.Error(message = "Internet not available"))
            }
        } catch(e: Exception){
            products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))

        }
    }


}