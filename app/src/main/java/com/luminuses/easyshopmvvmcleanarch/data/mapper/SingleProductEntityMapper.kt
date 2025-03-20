package com.luminuses.easyshopmvvmcleanarch.data.mapper

import com.luminuses.easyshopmvvmcleanarch.data.dto.Product
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.DetailProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class SingleProductEntityMapper  @Inject constructor():
ProductBaseMapper<Product, DetailProductEntity>{
    override fun map(input: Product): DetailProductEntity {
        return DetailProductEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            price = input.price.toString(),
            imageUrl = input.images,
            rating = input.rating.toString(),
            )
    }
}