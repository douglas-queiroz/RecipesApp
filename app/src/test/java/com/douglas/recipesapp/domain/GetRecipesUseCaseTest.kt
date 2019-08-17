package com.douglas.recipesapp.domain

import com.douglas.recipesapp.domain.exception.NoInternetConnection
import com.douglas.recipesapp.service.RecipeService
import com.douglas.recipesapp.service.model.GetRecipesResponse
import com.douglas.recipesapp.util.InternetConnectionHelper
import com.douglas.recipesapp.util.SessionUtil
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetRecipesUseCaseTest {

    @Mock
    lateinit var internetConnection: InternetConnectionHelper

    @Mock
    lateinit var sessionUtil: SessionUtil

    @Mock
    lateinit var recipeService: RecipeService

    private val response = GetRecipesResponse(0, emptyList())

    private val apiKey = "api_key"

    private lateinit var target: GetRecipesUseCase

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        target = GetRecipesUseCaseImpl(internetConnection, sessionUtil, recipeService)

    }

    @Test
    fun `When has no internet connection`() = runBlockingTest {

        `when`(internetConnection.checkInternetConnection()).thenReturn(false)
        `when`(sessionUtil.getApiKey()).thenReturn(apiKey)
        `when`(recipeService.getRecipeList(apiKey)).thenReturn(response)

        try {
            target.getRecipes()
            fail()
        } catch (error: Exception) {
            assert(error is NoInternetConnection)
        }
    }

    @Test
    fun `When everything works fine`() = runBlockingTest {

        `when`(internetConnection.checkInternetConnection()).thenReturn(true)
        `when`(sessionUtil.getApiKey()).thenReturn(apiKey)
        `when`(recipeService.getRecipeList(apiKey)).thenReturn(response)

        try {
            val recipes = target.getRecipes()
            assertEquals(response.recipes, recipes)
        } catch (error: Exception) {
            fail()
        }
    }
}