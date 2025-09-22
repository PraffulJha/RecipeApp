package com.example.reciepevault.data.models

data class Ingredient(
    val id: Int,
    val name: String,
    val image: String,
    val amount: Double,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)