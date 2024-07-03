package com.jj.templateproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jj.templateproject.navigation.model.GraphNavigation
import com.jj.templateproject.navigation.model.Route
import com.jj.templateproject.presentation.ui.main.MainScreen
import com.jj.templateproject.presentation.ui.main.MainScreenViewModel
import com.jj.templateproject.presentation.ui.secondary.SecondaryScreen
import com.jj.templateproject.presentation.ui.secondary.SecondaryScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.MainScreen,
        route = GraphNavigation.MainNavGraph::class,
    ) {
        composable<Route.MainScreen> {
            val viewModel: MainScreenViewModel = koinViewModel()
            MainScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable<Route.SecondaryScreen> {
            val viewModel: SecondaryScreenViewModel = koinViewModel()
            SecondaryScreen(
                viewModel = viewModel,
            )
        }
    }
}