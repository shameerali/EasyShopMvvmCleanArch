package com.luminuses.easyshopmvvmcleanarch.domain.mapper

interface ProductBaseMapper<I, O> {
    fun map(input : I) : O
}