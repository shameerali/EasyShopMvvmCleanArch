package com.luminuses.easyshopmvvmcleanarch.di.usecase

import com.luminuses.easyshopmvvmcleanarch.domain.usecase.category.CategoryUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.category.CategoryUseCaseImpl
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.product.GetAllProductsUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.product.GetAllProductsUseCaseImpl
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_in.FirebaseUserSingInUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_in.FirebaseUserSingInUseCaseImpl
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_up.FirebaseUserSignUpUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.sign_up.FirebaseUserSignUpUseCaseImpl
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.write_user.WriteFirebaseUserInfosUseCase
import com.luminuses.easyshopmvvmcleanarch.domain.usecase.user.write_user.WriteFirebaseUserInfosUseCaseImpl
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

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignUpUseCase(
        firebaseSignUpUseCaseImpl: FirebaseUserSignUpUseCaseImpl,
    ): FirebaseUserSignUpUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseWriteUserUseCase(
        firebaseWriteUserUseCaseImpl: WriteFirebaseUserInfosUseCaseImpl,
    ): WriteFirebaseUserInfosUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignInUseCase(
        firebaseUserSingInUseCaseImpl: FirebaseUserSingInUseCaseImpl
    ): FirebaseUserSingInUseCase
}