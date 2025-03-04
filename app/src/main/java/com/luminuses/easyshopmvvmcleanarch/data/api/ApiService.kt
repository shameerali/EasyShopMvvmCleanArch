package com.luminuses.easyshopmvvmcleanarch.data.api

import retrofit2.http.GET

interface ApiService {
    @GET("products/categories")
    suspend fun getAllCategoriesListFromApi(): List<String>
}