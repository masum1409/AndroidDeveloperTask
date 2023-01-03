package com.masum.androiddevelopertask.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val app : Application,
    private  val shopRepository: ShopRepository
) : AndroidViewModel(app){
    val cartProducts : MutableLiveData<List<CartItem>> = MutableLiveData()

    init {
        getCartProducts()
    }


    fun getCartProducts() {
        viewModelScope.launch {
            shopRepository.getAllCartItems().collectLatest {
                cartProducts.postValue(it)
            }
        }
    }
    fun addToCart(cartItem: CartItem)=viewModelScope.launch {
        shopRepository.addCart(cartItem)
    }
}