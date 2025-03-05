package com.luminuses.easyshopmvvmcleanarch.di.mappers

import com.luminuses.easyshopmvvmcleanarch.data.dto.Product
import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
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
    abstract fun bindAllProductsEntityMapper(): ProductListMapper<Product, ProductEntity>
}