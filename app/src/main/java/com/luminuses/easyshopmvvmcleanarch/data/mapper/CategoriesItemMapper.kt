package com.luminuses.easyshopmvvmcleanarch.data.mapper

import com.luminuses.easyshopmvvmcleanarch.data.dto.categories.CategoriesItem
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import javax.inject.Inject

class CategoriesItemMapper @Inject constructor():
ProductListMapper<CategoriesItem, String>
{
    override fun map(input: List<CategoriesItem>): List<String> {
         return input.map {
             it.name
         }
    }
}