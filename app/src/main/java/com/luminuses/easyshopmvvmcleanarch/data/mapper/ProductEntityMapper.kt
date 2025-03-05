package com.luminuses.easyshopmvvmcleanarch.data.mapper

import com.luminuses.easyshopmvvmcleanarch.data.dto.Product
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import javax.inject.Inject

class ProductEntityMapper @Inject constructor() :
    ProductListMapper <Product, ProductEntity>{
    override fun map(input: List<Product>): List<ProductEntity> {
        return input.map {
            ProductEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price.toString(),
                imageUrl = it.images[0],
                rating = it.rating,
            )
        }
    }
}