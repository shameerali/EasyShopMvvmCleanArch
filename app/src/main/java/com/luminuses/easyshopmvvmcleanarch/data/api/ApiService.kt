package com.luminuses.easyshopmvvmcleanarch.data.api

import com.luminuses.easyshopmvvmcleanarch.data.dto.Product
import com.luminuses.easyshopmvvmcleanarch.data.dto.Products
import com.luminuses.easyshopmvvmcleanarch.data.dto.categories.CategoriesItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products/categories")
    suspend fun getAllCategoriesListFromApi(): List<CategoriesItem>

    @GET("products")
    suspend fun getProductsListFromApi(): Products

    @GET("products/{id}")
    suspend fun getSingleProductByIdFromApi(@Path("id") productId: Int): Product
}