package com.luminuses.easyshopmvvmcleanarch.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart.CartUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart.DeleteUserCartUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val deleteCartUseCase: DeleteUserCartUseCase,
    private val mapper: ProductListMapper<UserCartEntity, UserCartUiData>
    ): ViewModel() {
    private val _userCarts = MutableLiveData<ScreenState<List<UserCartUiData>>>()
    val userCarts: LiveData<ScreenState<List<UserCartUiData>>> get() = _userCarts

    private val _totalPriceLiveData: MutableLiveData<Double> = MutableLiveData(0.0)
    val totalPriceLiveData: LiveData<Double> get() = _totalPriceLiveData

    fun getCartsByUserId(userId: String) {
        viewModelScope.launch {
            cartUseCase(userId).collect{
                when(it){
                    is NetworkResponseState.Error -> {
                        _userCarts.value = ScreenState.Error(it.exception.message!!)
//                        _userCarts.postValue(ScreenState.Error(it.exception.toString()))
                    }
                    is NetworkResponseState.Loading -> {
                        Log.d("TAG", "getCartsByUserId:  loading ")
                        _userCarts.value = ScreenState.Loading
                    }
                    is NetworkResponseState.Success -> {
                        Log.d("TAG", "getCartsByUserId:  success ")
                        Log.d("TAG", "getCartsByUserId:  success "+it.result)
                        _userCarts.value = ScreenState.Success(mapper.map(it.result))
                    }
                }
            }
        }
    }

    fun updateTotalPrice(totalPrice: Double) {
        _totalPriceLiveData.value = totalPrice
    }

    fun deleteUserCartItem(userCartUiData: UserCartUiData) {

    }

    fun updateUserCartItem(userCartUiData: UserCartUiData) {

    }

    fun calculateTotalPrice(cartList: List<UserCartUiData>): Double {
        var totalPrice = 0.0
        for (cart in cartList) {
            totalPrice += cart.price * cart.quantity
        }
        return totalPrice
    }
}