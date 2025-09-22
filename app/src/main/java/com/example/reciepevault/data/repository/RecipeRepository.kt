package com.example.reciepevault.data.repository


import com.example.reciepevault.data.apiInterfaces.RecipeApiServices
import com.example.reciepevault.data.models.Recipe
import com.example.reciepevault.data.models.RecipeByIngredientsResponse
import com.example.reciepevault.data.models.RecipeSearchResponse
import javax.inject.Inject

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
    class Loading<T> : Resource<T>()
}

@Singleton
class RecipeRepository @Inject constructor(
    private val apiService: RecipeApiServices
)  {
    companion object {
        private const val API_KEY = "YOUR_SPOONACULAR_API_KEY"
    }

    suspend fun searchRecipes(
        query: String? = null,
        type: String? = null,
        cuisine: String? = null,
        diet: String? = null,
        intolerances: String? = null,
        includeIngredients: String? = null,
        excludeIngredients: String? = null,
        maxReadyTime: Int? = null,
        minCalories: Int? = null,
        maxCalories: Int? = null,
        offset: Int = 0,
        number: Int = 12
    ): Flow<Resource<RecipeSearchResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.searchRecipes(
                apiKey = API_KEY,
                query = query,
                type = type,
                cuisine = cuisine,
                diet = diet,
                intolerances = intolerances,
                includeIngredients = includeIngredients,
                excludeIngredients = excludeIngredients,
                maxReadyTime = maxReadyTime,
                minCalories = minCalories,
                maxCalories = maxCalories,
                offset = offset,
                number = number
            )

            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(Resource.Success(data))
                } ?: emit(Resource.Error("Empty response"))
            } else {
                emit(Resource.Error("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    suspend fun getRecipeDetails(id: Int): Flow<Resource<Recipe>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.getRecipeInformation(
                id = id,
                apiKey = API_KEY,
                includeNutrition = true
            )

            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(Resource.Success(data))
                } ?: emit(Resource.Error("Recipe not found"))
            } else {
                emit(Resource.Error("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    suspend fun getRandomRecipes(number: Int = 10): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.getRandomRecipes(
                apiKey = API_KEY,
                number = number
            )

            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(Resource.Success(data.recipes))
                } ?: emit(Resource.Error("No recipes found"))
            } else {
                emit(Resource.Error("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    suspend fun getPopularRecipes(): Result<List<Recipe>> {
        return try {
            val response = apiService.getRandomRecipes(
                apiKey = API_KEY,
                number = 12,
                includeTags = "popular"
            )
            if (response.isSuccessful) {
                Result.success(response.body()?.recipes ?: emptyList())
            } else {
                Result.failure(Exception("Failed to load recipes: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun findRecipesByIngredients(
        ingredients: String,
        number: Int = 10
    ): Flow<Resource<List<RecipeByIngredientsResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.findRecipesByIngredients(
                apiKey = API_KEY,
                ingredients = ingredients,
                number = number
            )

            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(Resource.Success(data))
                } ?: emit(Resource.Error("No recipes found"))
            } else {
                emit(Resource.Error("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}