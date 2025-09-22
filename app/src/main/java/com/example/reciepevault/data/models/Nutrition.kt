package com.example.reciepevault.data.models

data class Nutrition(
    val nutrients: List<Nutrient> = emptyList()
)

data class Nutrient(
    val name: String,
    val amount: Double,
    val unit: String,
    val percentOfDailyNeeds: Double?
)