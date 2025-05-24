package com.example.newsaggregator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsaggregator.ui.screens.aboutNews.AboutNewsScreen
import com.example.newsaggregator.ui.screens.newsHome.NewsHomeScreen

@Composable
fun AppNavHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen"){
        composable("home_screen") {
            NewsHomeScreen(navController = navController)
        }

        composable("about_news_screen/{url}"){ backStackEntry ->
            AboutNewsScreen(navController = navController)
        }
    }
}