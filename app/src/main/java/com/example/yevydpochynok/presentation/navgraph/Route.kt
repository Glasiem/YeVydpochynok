package com.example.yevydpochynok.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
){

    object HomeScreen : Route("homeScreen")
    object PlanScreen : Route("planScreen")
    object LocationScreen : Route("locationScreen")
    object FoodPlaceFilterScreen : Route("categoryScreen/RESTAURANT/foodPlaceFilterScreen")
    object ParkPlaceFilterScreen : Route("categoryScreen/PARK/parkPlaceFilterScreen")
    object MuseumPlaceFilterScreen : Route("categoryScreen/MUSEUM/museumPlaceFilterScreen")
    object CategoriesScreen : Route("categoriesScreen")
    object CategoryScreen : Route("categoryScreen")
    object Navigation : Route(route = "navigation")
    object NavigatorScreen : Route(route = "navigatorScreen")
    object DetailsScreen : Route("detailsScreen")
    object AboutScreen : Route("aboutScreen")
}