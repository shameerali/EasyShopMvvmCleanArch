package com.luminuses.easyshopmvvmcleanarch.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.DetailProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.cart.CartUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.product.GetSingleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val mapper : ProductBaseMapper<DetailProductEntity ,DetailProductUiData >,
    private val cartUseCase: CartUseCase,

) : ViewModel() {
    private val _product = MutableLiveData<ScreenState<DetailProductUiData>>()
    val product: LiveData<ScreenState<DetailProductUiData>> get() = _product

    fun getProduct(id: Int) {
        viewModelScope.launch {
            getSingleProductUseCase.invoke(id).collect {
                when (it) {
                    is NetworkResponseState.Error -> _product.value =
                        ScreenState.Error(it.exception.message!!)

                    is NetworkResponseState.Loading -> _product.value = ScreenState.Loading
                    is NetworkResponseState.Success -> _product.value =
                        ScreenState.Success( mapper.map(it.result) )
                }
            }

        }
    }

    fun addToCart(userCartEntity: UserCartEntity) {

        viewModelScope.launch {
            cartUseCase.invoke(userCartEntity)
        }

    //
    }

    fun addToFavorite(userCartUiData: UserCartEntity) {

    }

}