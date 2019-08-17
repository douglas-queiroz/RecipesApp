package com.douglas.recipesapp.domain

import com.douglas.recipesapp.domain.model.Recipe

interface GetRecipesUseCase {

    suspend fun getreceipts(): List<Recipe>
}