package com.example.reciepevault.data.apiInterfaces

import com.example.reciepevault.data.models.RandomRecipesResponse
import com.example.reciepevault.data.models.Recipe
import com.example.reciepevault.data.models.RecipeByIngredientsResponse
import com.example.reciepevault.data.models.RecipeSearchResponse
import retrofit2.Response
import retrofit2.http.*

interface RecipeApiServices {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String?,
        @Query("number") number: Int = 12,
        @Query("offset") offset: Int = 0,
        @Query("type") type: String? = null,
        @Query("cuisine") cuisine: String? = null,
        @Query("diet") diet: String? = null,
        @Query("intolerances") intolerances: String? = null,
        @Query("includeIngredients") includeIngredients: String? = null,
        @Query("excludeIngredients") excludeIngredients: String? = null,
        @Query("maxReadyTime") maxReadyTime: Int? = null,
        @Query("minCalories") minCalories: Int? = null,
        @Query("maxCalories") maxCalories: Int? = null,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("addRecipeNutrition") addRecipeNutrition: Boolean = false,
        @Query("fillIngredients") fillIngredients: Boolean = true,
        @Query("sort") sort: String? = null,
        @Query("sortDirection") sortDirection: String? = "asc"
    ): Response<RecipeSearchResponse>

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean = true,
        @Query("addWinePairing") addWinePairing: Boolean = false,
        @Query("addTasteData") addTasteData: Boolean = false
    ): Response<Recipe>

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = 10,
        @Query("include-tags") includeTags: String? = null,
        @Query("exclude-tags") excludeTags: String? = null
    ): Response<RandomRecipesResponse>

    @GET("recipes/findByIngredients")
    suspend fun findRecipesByIngredients(
        @Query("apiKey") apiKey: String,
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int = 10,
        @Query("ranking") ranking: Int = 1, // 1 = maximize used ingredients, 2 = minimize missing
        @Query("ignorePantry") ignorePantry: Boolean = true
    ): Response<List<RecipeByIngredientsResponse>>

    @GET("recipes/{id}/similar")
    suspend fun getSimilarRecipes(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = 4
    ): Response<List<Recipe>>

}
