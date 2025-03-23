package com.luminuses.easyshopmvvmcleanarch.ui.mapper

import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import com.luminuses.easyshopmvvmcleanarch.ui.cart.UserCartUiData
import javax.inject.Inject

class CartEntityToUiMapper @Inject constructor() :
ProductListMapper<UserCartEntity, UserCartUiData>{
    override fun map(input: List<UserCartEntity>): List<UserCartUiData> {
         return input.map {
             UserCartUiData(
                 userId = it.userId,
                 productId = it.productId,
                 price = it.price,
                 title = it.title,
                 imageUrl = it.image,
                 quantity = it.quantity
             )
         }
    }

}