package com.luminuses.easyshopmvvmcleanarch.data.api

import com.luminuses.easyshopmvvmcleanarch.data.dto.Products
import com.luminuses.easyshopmvvmcleanarch.data.dto.categories.CategoriesItem
import retrofit2.http.GET

interface ApiService {
    @GET("products/categories")
    suspend fun getAllCategoriesListFromApi(): List<CategoriesItem>

    @GET("products")
    suspend fun getProductsListFromApi(): Products
}