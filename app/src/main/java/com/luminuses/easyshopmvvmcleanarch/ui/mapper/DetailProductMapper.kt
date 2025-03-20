package com.luminuses.easyshopmvvmcleanarch.ui.mapper

import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.DetailProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.ui.detail.DetailProductUiData
import javax.inject.Inject

class DetailProductEntityToUiMapper @Inject constructor() :
    ProductBaseMapper<DetailProductEntity, DetailProductUiData> {
    override fun map(input: DetailProductEntity): DetailProductUiData {
        return DetailProductUiData(
            id = input.id,
            title = input.title,
            description = input.description,
            price = input.price,
            imageUrl = input.imageUrl,
            rating = input.rating,
        )
    }
}