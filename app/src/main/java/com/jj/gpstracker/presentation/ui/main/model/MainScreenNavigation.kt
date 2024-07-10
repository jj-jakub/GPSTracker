package com.jj.gpstracker.presentation.ui.main.model

import com.jj.gpstracker.framework.navigation.model.Route

sealed class MainScreenNavigation(val route: Route) {
    data class SecondaryScreen(
        val text: String,
        val secondaryText: String?,
        val tertiaryText: String?,
    ) : MainScreenNavigation(
        Route.SecondaryScreen(
            text = text,
            textSecondary = secondaryText,
            textTertiary = tertiaryText,
         )
    )
}
