package com.luminuses.easyshopmvvmcleanarch.di.mappers

import com.luminuses.easyshopmvvmcleanarch.domain.entity.product.ProductEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductListMapper
import com.luminuses.easyshopmvvmcleanarch.ui.auth.UserInformationUiData
import com.luminuses.easyshopmvvmcleanarch.ui.home.ProductUiData
import com.luminuses.easyshopmvvmcleanarch.ui.mapper.ProductEntityToUiMapper
import com.luminuses.easyshopmvvmcleanarch.ui.mapper.UserInfoUiDataToEntityMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UiMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHomeProductUiMapper(productUiDataMapper: ProductEntityToUiMapper) : ProductListMapper<ProductEntity, ProductUiData>


    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoUiDataToEntityMapper(userInformationEntityMapperToUi: UserInfoUiDataToEntityMapper) : ProductBaseMapper<UserInformationUiData, UserInformationEntity>
}