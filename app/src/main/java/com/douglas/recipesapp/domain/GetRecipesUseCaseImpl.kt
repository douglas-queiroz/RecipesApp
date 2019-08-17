package com.douglas.recipesapp.domain

import com.douglas.recipesapp.domain.exception.NoInternetConnection
import com.douglas.recipesapp.domain.model.Recipe
import com.douglas.recipesapp.service.RecipeService
import com.douglas.recipesapp.util.InternetConnectionHelper
import com.douglas.recipesapp.util.SessionUtil

class GetRecipesUseCaseImpl(private val internetConnectionHelper: InternetConnectionHelper,
                            private val sessionUtil: SessionUtil,
                            private val recipeService: RecipeService): GetRecipesUseCase {

    override suspend fun getRecipes(): List<Recipe> {

        checkInternetConnection()

        val sessionKey = sessionUtil.getApiKey()
        val recipesResponse = recipeService.getRecipeList(sessionKey)

        return recipesResponse.recipes.orEmpty()
    }

    private fun checkInternetConnection() {

        if (!internetConnectionHelper.checkInternetConnection()) {
            throw NoInternetConnection()
        }
    }


}