package com.douglas.recipesapp.service

import com.douglas.recipesapp.service.model.GetRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("search")
    suspend fun getRecipeList(@Query("key") key: String): GetRecipesResponse
}