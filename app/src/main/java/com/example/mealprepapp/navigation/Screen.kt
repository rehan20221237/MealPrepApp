package com.example.mealprepapp.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object AddMeals : Screen("add_meals")
    object SearchIngredient : Screen("search_ingredient")
    object SearchMeals : Screen("search_meal")
}