package com.luminuses.easyshopmvvmcleanarch.di.usecase

import com.luminuses.easyshopmvvmcleanarch.domain.usecase.category.CategoryUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.category.CategoryUseCaseImpl
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.product.GetAllProductsUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.product.GetAllProductsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllCategoryUseCase(
        getAllCategoryUseCaseImpl: CategoryUseCaseImpl,
    ): CategoryUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindGetAllProductsUseCase(
        getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl
    ): GetAllProductsUseCase
}