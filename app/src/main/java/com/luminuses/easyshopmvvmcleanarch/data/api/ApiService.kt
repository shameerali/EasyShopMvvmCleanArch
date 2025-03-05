package com.luminuses.easyshopmvvmcleanarch.data.api

import com.luminuses.easyshopmvvmcleanarch.data.dto.Products
import retrofit2.http.GET

interface ApiService {
    @GET("products/categories")
    suspend fun getAllCategoriesListFromApi(): List<String>

    @GET("products")
    suspend fun getProductsListFromApi(): Products
}