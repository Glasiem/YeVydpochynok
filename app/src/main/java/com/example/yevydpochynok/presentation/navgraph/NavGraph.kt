package com.example.yevydpochynok.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.yevydpochynok.presentation.home.HomeScreen
import com.example.yevydpochynok.presentation.home.HomeViewModel
import com.example.yevydpochynok.presentation.navigator.Navigator

@Composable
fun NavGraph (
    startDestination : String
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){

        navigation(
            route = Route.Navigation.route,
            startDestination = Route.NavigatorScreen.route
        ){
            composable(route = Route.NavigatorScreen.route){
                Navigator()
            }
        }
    }
}