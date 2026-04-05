package com.example.mealprepapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mealprepapp.navigation.AppNavGraph
import com.example.mealprepapp.ui.theme.MealPrepAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MealPrepAppTheme {
                AppNavGraph()
            }
        }
    }
}