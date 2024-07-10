package com.jj.gpstracker.framework.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.jj.gpstracker.presentation.MainRoot
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            MainRoot(
                navController = navHostController,
                viewModel = koinViewModel(),
            )
        }
    }
}