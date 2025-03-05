package com.luminuses.easyshopmvvmcleanarch.data.dto

data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
)