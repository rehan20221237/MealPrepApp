package com.example.mealprepapp.ui

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mealprepapp.navigation.Screen
import com.example.mealprepapp.viewmodel.MealViewModel

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel = remember {
        MealViewModel(context.applicationContext as Application)
    }

    LaunchedEffect(Unit) {
        viewModel.loadMeals()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Meal Prep App",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Screen.AddMeals.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Meals to DB")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(Screen.SearchIngredient.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search for Meals By Ingredient")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(Screen.SearchMeals.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search for Meals")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {navController.navigate(Screen.WebSearch.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Web Search")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Meals in Database",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (viewModel.meals.value.isEmpty()) {
                Text("No meals added yet")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.meals.value) { meal ->
                        Column {
                            Text(
                                text = meal.strMeal ?: "Unnamed Meal",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Category: ${meal.strCategory ?: "N/A"}"
                            )
                            Text(
                                text = "Area: ${meal.strArea ?: "N/A"}"
                            )
                        }
                    }
                }
            }
        }
    }
}