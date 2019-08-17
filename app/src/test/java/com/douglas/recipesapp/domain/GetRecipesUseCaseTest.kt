package com.douglas.recipesapp.domain

import com.douglas.recipesapp.domain.exception.NoInternetConnection
import com.douglas.recipesapp.service.model.GetRecipesResponse
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetRecipesUseCaseTest {

    private val response = GetRecipesResponse(0, emptyList())

    private lateinit var target: GetRecipesUseCase

    @Before
    fun setUp() {
    }

    @Test
    fun `When has no internet connection`() = runBlockingTest {

        try {
            target.getRecipes()
            fail()
        } catch (error: Exception) {
            assert(error is NoInternetConnection)
        }
    }

    @Test
    fun `When everything works fine`() = runBlockingTest {

        try {
            val recipes = target.getRecipes()
            assertEquals(response.recipes, recipes)
        } catch (error: Exception) {
            fail()
        }
    }
}