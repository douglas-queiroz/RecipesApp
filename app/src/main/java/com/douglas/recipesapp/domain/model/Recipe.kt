package com.douglas.recipesapp.domain.model

import com.google.gson.annotations.SerializedName

data class Recipe(

    val title: String?,

    @SerializedName("image_url")
    val imageUrl: String?
)
