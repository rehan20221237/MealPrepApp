package com.example.mealprepapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealprepapp.ui.AddMealsScreen
import com.example.mealprepapp.ui.MainScreen
import com.example.mealprepapp.ui.SearchByIngredientScreen
import com.example.mealprepapp.ui.SearchMealsScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable (Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.AddMeals.route) {
            AddMealsScreen()
        }
        composable(Screen.SearchIngredient.route) {
            SearchByIngredientScreen()
        }
        composable(Screen.SearchMeals.route) {
            SearchMealsScreen()
        }
    }
}