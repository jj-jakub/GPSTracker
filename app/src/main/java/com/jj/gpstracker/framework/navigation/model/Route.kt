package com.jj.gpstracker.framework.navigation.model

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MainScreen : Route

    @Serializable
    data class SecondaryScreen(
        val text: String,
        val textSecondary: String?,
        val textTertiary: String?,
    ) : Route
}
