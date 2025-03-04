package com.luminuses.easyshopmvvmcleanarch.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel : ViewModel() {
    private val _products = MutableLiveData<ScreenState<List<ProductUiData>>>()
    val products: LiveData<ScreenState<List<ProductUiData>>> get() = _products

    private val _categories = MutableLiveData<ScreenState<List<String>>>()
    val categories: LiveData<ScreenState<List<String>>> get() = _categories

    init {
        getAllCategory()
        getAllProducts()
    }

    private fun getAllCategory() {


    }

    private fun getAllProducts() {


    }


}