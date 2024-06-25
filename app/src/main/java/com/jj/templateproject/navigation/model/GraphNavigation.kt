package com.jj.templateproject.navigation.model

sealed class GraphNavigation(val route: String) {
    data object MainNavGraph : GraphNavigation(route = "main_nav_graph")
}