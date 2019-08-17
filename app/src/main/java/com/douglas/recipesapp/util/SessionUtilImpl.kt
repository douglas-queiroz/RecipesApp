package com.douglas.recipesapp.util

import com.douglas.recipesapp.BuildConfig

class SessionUtilImpl: SessionUtil {

    override fun getApiKey(): String {
        return BuildConfig.API_KEY
    }
}