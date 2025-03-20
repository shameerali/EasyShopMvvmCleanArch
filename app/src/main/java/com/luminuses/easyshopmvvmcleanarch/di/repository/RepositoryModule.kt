package com.luminuses.easyshopmvvmcleanarch.di.repository

import com.luminuses.easyshopmvvmcleanarch.data.repository.FirebaseRepositoryImpl
import com.luminuses.easyshopmvvmcleanarch.data.repository.LocalRepositoryImpl
import com.luminuses.easyshopmvvmcleanarch.data.repository.RemoteRepositoryImpl
import com.luminuses.easyshopmvvmcleanarch.domain.repository.FirebaseRepository
import com.luminuses.easyshopmvvmcleanarch.domain.repository.LocalRepository
import com.luminuses.easyshopmvvmcleanarch.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteRepository(
        repository: RemoteRepositoryImpl,
    ): RemoteRepository

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseRepository(
        repository: FirebaseRepositoryImpl,
    ): FirebaseRepository


    @Binds
    @ViewModelScoped
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ):LocalRepository
}