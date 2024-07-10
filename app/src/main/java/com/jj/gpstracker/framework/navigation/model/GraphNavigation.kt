package com.jj.gpstracker.framework.navigation.model

import kotlinx.serialization.Serializable

sealed interface GraphNavigation {
    @Serializable
    data object MainNavGraph : GraphNavigation
}