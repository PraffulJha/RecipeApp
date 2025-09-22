package com.example.reciepevault.data.models

data class ExtendedIngredient(
    val id: Int,
    val name: String,
    val original: String,
    val originalName: String,
    val amount: Double,
    val unit: String,
    val image: String?,
    val consistency: String?,
    val aisle: String?
)