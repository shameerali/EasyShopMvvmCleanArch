package com.luminuses.easyshopmvvmcleanarch.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "availabilityStatus")
    val availabilityStatus: String,
//    @Json(name = "brand")
//    val brand: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "dimensions")
    val dimensions: Dimensions,
    @Json(name = "discountPercentage")
    val discountPercentage: Double,
    @Json(name = "id")
    val id: Int,
    @Json(name = "images")
    val images: List<String>,
//    val meta: Meta,
//    val minimumOrderQuantity: Int,
    @Json(name = "price")
    val price: Double,
    @Json(name = "rating")
    val rating: Double,
//    val returnPolicy: String,
//    val reviews: List<Review>,
//    val shippingInformation: String,
//    val sku: String,
//    val stock: Int,
//    val tags: List<String>,
//    val thumbnail: String,
    @Json(name = "title")
    val title: String,
//    val warrantyInformation: String,
//    val weight: Int
)