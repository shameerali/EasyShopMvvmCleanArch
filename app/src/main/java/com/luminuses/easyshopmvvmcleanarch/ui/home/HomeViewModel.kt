package com.luminuses.easyshopmvvmcleanarch.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.category.CategoryUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.product.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val mapper: ProductListMapper<ProductEntity, ProductUiData>,
) : ViewModel() {
    private val _products = MutableLiveData<ScreenState<List<ProductUiData>>>()
    val products: LiveData<ScreenState<List<ProductUiData>>> get() = _products

    private val _categories = MutableLiveData<ScreenState<List<String>>>()
    val categories: LiveData<ScreenState<List<String>>> get() = _categories

    init {
        getAllCategory()
        getAllProducts()
    }

    private fun getAllCategory() {

        categoryUseCase().onEach{
            when (it) {
                is NetworkResponseState.Error -> _categories.postValue(ScreenState.Error(it.exception.message!!))
                is NetworkResponseState.Loading -> _categories.postValue(ScreenState.Loading)
                is NetworkResponseState.Success -> {
                    Log.d("TAG", "getAllCategory: Success "+it.result)
                    _categories.postValue(ScreenState.Success(it.result))
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getAllProducts() {
        getAllProductsUseCase().onEach {
            when (it) {
                is NetworkResponseState.Error ->  _products.postValue(ScreenState.Error(it.exception.message!!))

                is NetworkResponseState.Loading ->  _products.postValue(ScreenState.Loading)

                is NetworkResponseState.Success ->{
                    _products.postValue(ScreenState.Success(mapper.map(it.result)))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProductsByCategory(categoryName: String) {
        TODO("Not yet implemented")
    }

}