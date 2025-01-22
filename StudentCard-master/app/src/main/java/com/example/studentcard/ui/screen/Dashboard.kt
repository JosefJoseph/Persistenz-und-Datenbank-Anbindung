package com.example.studentcard.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentcard.database.controller.ToDoController

/**
 * Ein Jetpack Compose-Element zur Anzeige des Dashboards.
 */
@Composable
fun Dashboard(toDoController: ToDoController) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { navController.navigate("todo_screen") }) {
                    Text("Manage ToDos", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        composable("todo_screen") {
            ToDoScreen(toDoController)
        }
    }
}