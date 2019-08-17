package com.douglas.recipesapp.service.model

import com.douglas.recipesapp.domain.model.Recipe

data class GetRecipesResponse(
    val count: Int?,
    val recipes: List<Recipe>
)