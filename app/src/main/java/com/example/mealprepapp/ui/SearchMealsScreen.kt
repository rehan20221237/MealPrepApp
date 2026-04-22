package com.example.mealprepapp.ui

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealprepapp.viewmodel.MealViewModel

@Composable
fun SearchMealsScreen() {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val viewModel: MealViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MealViewModel(application) as T
            }
        }
    )

    var query by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search Meals By Ingredient") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.searchMealsFromApi(query)
            }
        ) {
            Text("Retrieve Meals")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.saveApiMealsToDatabase()
                Toast.makeText(context, "Meals saved to database", Toast.LENGTH_SHORT).show()
            },
            enabled = viewModel.apiMeals.value.isNotEmpty()
        ) {
            Text("Save Meals to Database")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            viewModel.isApiLoading.value -> {
                Text("Loading...")
            }

            query.isNotBlank() && viewModel.apiMeals.value.isEmpty() -> {
                Text("No meals found")
            }

            else -> {
                LazyColumn {
                    items(viewModel.apiMeals.value) { meal ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "Meal: ${meal.strMeal}")
                                Text(text = "DrinkAlternate: ${meal.strDrinkAlternate ?: "null"}")
                                Text(text = "Category: ${meal.strCategory ?: "N/A"}")
                                Text(text = "Area: ${meal.strArea ?: "N/A"}")
                                Text(text = "Tags: ${meal.strTags ?: "null"}")
                                Text(text = "Youtube: ${meal.strYoutube ?: "null"}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Instructions: ${meal.strInstructions ?: "N/A"}")

                                Spacer(modifier = Modifier.height(8.dp))

                                val ingredients = listOf(
                                    meal.strIngredient1 to meal.strMeasure1,
                                    meal.strIngredient2 to meal.strMeasure2,
                                    meal.strIngredient3 to meal.strMeasure3,
                                    meal.strIngredient4 to meal.strMeasure4,
                                    meal.strIngredient5 to meal.strMeasure5,
                                    meal.strIngredient6 to meal.strMeasure6,
                                    meal.strIngredient7 to meal.strMeasure7,
                                    meal.strIngredient8 to meal.strMeasure8,
                                    meal.strIngredient9 to meal.strMeasure9,
                                    meal.strIngredient10 to meal.strMeasure10,
                                    meal.strIngredient11 to meal.strMeasure11,
                                    meal.strIngredient12 to meal.strMeasure12,
                                    meal.strIngredient13 to meal.strMeasure13,
                                    meal.strIngredient14 to meal.strMeasure14,
                                    meal.strIngredient15 to meal.strMeasure15,
                                    meal.strIngredient16 to meal.strMeasure16,
                                    meal.strIngredient17 to meal.strMeasure17,
                                    meal.strIngredient18 to meal.strMeasure18,
                                    meal.strIngredient19 to meal.strMeasure19,
                                    meal.strIngredient20 to meal.strMeasure20
                                )

                                ingredients.forEachIndexed { index, pair ->
                                    val ingredient = pair.first
                                    val measure = pair.second

                                    if (!ingredient.isNullOrBlank()) {
                                        Text(text = "Ingredient${index + 1}: $ingredient")
                                        Text(text = "Measure${index + 1}: ${measure ?: ""}")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}