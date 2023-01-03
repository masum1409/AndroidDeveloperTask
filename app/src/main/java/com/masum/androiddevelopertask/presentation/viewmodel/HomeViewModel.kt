package com.masum.androiddevelopertask.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.masum.androiddevelopertask.data.model.Shop
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.data.repository.datasource.LocalDataSource
import com.masum.androiddevelopertask.data.util.Network.isNetworkAvailable
import com.masum.androiddevelopertask.data.util.Resource
import com.masum.androiddevelopertask.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app : Application,
    private  val productUseCase: ProductUseCase,
    private val localDataSource: LocalDataSource
) : AndroidViewModel(app){
    val products : MutableLiveData<Resource<List<ShopItem>>> = MutableLiveData()

    init {
        getAllProducts()
    }

     fun getAllProducts() =viewModelScope.launch {
        products.postValue(Resource.Loading(localDataSource.getAllProducts()))
        try {
            if(isNetworkAvailable(app)) {
                val result =productUseCase.getAllProducts()
                products.postValue(result)
            } else {
                products.postValue(Resource.Error(message = "Internet not available"))
            }
        } catch(e: Exception){
            products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))

        }
    }
}