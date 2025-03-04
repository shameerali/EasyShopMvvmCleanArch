package com.luminuses.easyshopmvvmcleanarch.domain.usecase.category

import com.luminuses.easyshopmvvmcleanarch.common.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    operator fun invoke() : Flow<NetworkResponseState<List<String>>>
}