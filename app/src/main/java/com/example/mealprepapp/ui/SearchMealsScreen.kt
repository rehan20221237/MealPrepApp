package com.example.mealprepapp.ui

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search Meals") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.searchMealsFromApi(query)
            }
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.apiMeals.value) { meal ->
                Column(modifier = Modifier.padding(bottom = 12.dp)) {
                    Text(text = meal.strMeal)
                    Text(text = "Category: ${meal.strCategory ?: "N/A"}")
                    Text(text = "Area: ${meal.strArea ?: "N/A"}")
                }
            }
        }
    }
}