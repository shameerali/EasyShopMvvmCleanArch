package com.luminuses.easyshopmvvmcleanarch.domain.usecase.category

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import com.luminuses.easyshopmvvmcleanarch.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCaseImpl @Inject constructor(private val repository: RemoteRepository) : CategoryUseCase {
    override fun invoke(): Flow<NetworkResponseState<List<String>>> {
       return repository.getAllCategoriesListFromApi()
    }
}