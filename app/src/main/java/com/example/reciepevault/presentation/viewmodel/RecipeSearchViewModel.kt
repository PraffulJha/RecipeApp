package com.example.reciepevault.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciepevault.data.models.RecipeSearchState
import com.example.reciepevault.data.repository.RecipeRepository
import com.example.reciepevault.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeSearchViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = mutableStateOf(RecipeSearchState())
    val state: State<RecipeSearchState> = _state

    init {
        getRandomRecipes()
    }

    fun searchRecipes(
        query: String = "",
        loadMore: Boolean = false,
        type: String? = null,
        cuisine: String? = null,
        diet: String? = null,
        maxReadyTime: Int? = null,
        minCalories: Int? = null,
        maxCalories: Int? = null
    ) {
        val currentState = _state.value
        val offset = if (loadMore) currentState.currentOffset + 12 else 0

        if (!loadMore) {
            _state.value = currentState.copy(
                searchQuery = query,
                currentOffset = 0
            )
        }

        viewModelScope.launch {
            repository.searchRecipes(
                query = query.ifBlank { null },
                type = type,
                cuisine = cuisine,
                diet = diet,
                maxReadyTime = maxReadyTime,
                minCalories = minCalories,
                maxCalories = maxCalories,
                offset = offset
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        val newRecipes = result.data.results
                        _state.value = _state.value.copy(
                            isLoading = false,
                            recipes = if (loadMore) currentState.recipes + newRecipes else newRecipes,
                            error = "",
                            totalResults = result.data.totalResults,
                            currentOffset = offset,
                            hasMorePages = (offset + 12) < result.data.totalResults
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getRandomRecipes() {
        viewModelScope.launch {
            repository.getRandomRecipes().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            recipes = result.data,
                            error = ""
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = "")
    }
}