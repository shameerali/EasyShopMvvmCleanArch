package com.luminuses.easyshopmvvmcleanarch.di.mappers

import com.luminuses.easyshopmvvmcleanarch.data.dto.Product
import com.luminuses.easyshopmvvmcleanarch.data.dto.categories.CategoriesItem
import com.luminuses.easyshopmvvmcleanarch.data.mapper.CategoriesItemMapper
import com.luminuses.easyshopmvvmcleanarch.data.mapper.ProductEntityMapper
import com.luminuses.easyshopmvvmcleanarch.data.mapper.SingleProductEntityMapper
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.DetailProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAllCategoriesListMapper(categoriesItemMapper: CategoriesItemMapper) : ProductListMapper<CategoriesItem, String>

    @Binds
    @ViewModelScoped
    abstract fun bindAllProductsEntityMapper(productEntityMapper: ProductEntityMapper): ProductListMapper<Product, ProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleProductEntityMapper(singleProductEntityMapper: SingleProductEntityMapper) : ProductBaseMapper<Product, DetailProductEntity>
}