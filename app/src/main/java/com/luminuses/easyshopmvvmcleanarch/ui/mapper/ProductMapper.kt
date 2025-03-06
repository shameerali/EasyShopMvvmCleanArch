package com.luminuses.easyshopmvvmcleanarch.ui.mapper

import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import com.luminuses.easyshopmvvmcleanarch.ui.home.ProductUiData
import javax.inject.Inject

class ProductEntityToUiMapper @Inject constructor() :
    ProductListMapper<ProductEntity, ProductUiData>{
    override fun map(input: List<ProductEntity>): List<ProductUiData> {
         return input.map {
             ProductUiData(
                 id = it.id,
                 title = it.title,
                 description = it.description,
                 price = it.price,
                 imageUrl = it.imageUrl,
                 rating = it.rating,
             )
         }
    }

}