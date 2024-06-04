package com.example.yevydpochynok.presentation.navigator

import android.content.Intent
import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yevydpochynok.R
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.Place
import com.example.yevydpochynok.presentation.categories.CategoriesScreen
import com.example.yevydpochynok.presentation.categories.CategoriesViewModel
import com.example.yevydpochynok.presentation.categories.category.Categories
import com.example.yevydpochynok.presentation.categories.category.CategoryScreen
import com.example.yevydpochynok.presentation.categories.category.CategoryViewModel
import com.example.yevydpochynok.presentation.categories.components.PlaceDetails
import com.example.yevydpochynok.presentation.categories.filter.FoodPlaceFilterScreen
import com.example.yevydpochynok.presentation.categories.filter.FoodPlaceFilterVM
import com.example.yevydpochynok.presentation.categories.filter.MuseumPlaceFilterScreen
import com.example.yevydpochynok.presentation.categories.filter.MuseumPlaceFilterVM
import com.example.yevydpochynok.presentation.categories.filter.ParkPlaceFilterScreen
import com.example.yevydpochynok.presentation.categories.filter.ParkPlaceFilterVM
import com.example.yevydpochynok.presentation.home.HomeScreen
import com.example.yevydpochynok.presentation.home.HomeViewModel
import com.example.yevydpochynok.presentation.home.components.HowItWorksScreen
import com.example.yevydpochynok.presentation.home.location.LocationScreen
import com.example.yevydpochynok.presentation.home.location.LocationViewModel
import com.example.yevydpochynok.presentation.navgraph.Route
import com.example.yevydpochynok.presentation.navigator.components.BottomNavigationItem
import com.example.yevydpochynok.presentation.plan.PlanScreen
import com.example.yevydpochynok.presentation.plan.PlanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator(){
    val handler = LocalUriHandler.current

    val websiteLink = remember { Intent(Intent.ACTION_VIEW, Uri.parse("")) }

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.test, text = "Home"),
            BottomNavigationItem(icon = R.drawable.test, text = "Account"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        else -> 0
    }

//    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
////        if (isBottomBarVisible) {
//            BottomNavigation(
//                items = bottomNavigationItems,
//                selectedItem = selectedItem,
//                onItemClick = { index ->
//                    when (index) {
//                        0 -> navigateToTab(
//                            navController = navController,
//                            route = Route.HomeScreen.route
//                        )
//                    }
//                }
//            )
////        }
//    }) {
//        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
//            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    event = viewModel::onEvent,
                    navigateToPlan = {
                        navigateToTab(
                            navController = navController,
                            route = Route.PlanScreen.route
                        )
                    },
                    navigateToLocation = {
                        navigateToTab(
                            navController = navController,
                            route = Route.LocationScreen.route
                        )
                    },
                    navigateToCategories = {
                        navigateToTab(
                            navController = navController,
                            route = Route.CategoriesScreen.route
                        )
                    },
                    navigateToWebsite = {
                        navigateToWebsite(handler)
                    },
                    navigateToAbout = {
                        navigateToTab(
                            navController = navController,
                            route = Route.AboutScreen.route
                        )
                    },
                )
            }
            composable(route = Route.PlanScreen.route) {BackStackEntry ->
                val viewModel: PlanViewModel = hiltViewModel()
                PlanScreen(
//                    navigateUp = {navController.navigateUp()}
                    viewModel = viewModel,
                    goToDetails = {
                            place ->
                        navigateToDetails(
                            navController = navController,
                            route = Route.DetailsScreen.route,
                            place = place,
                        )
                    },
                    nextQuestion = {
                        navigateToQuestion(
                            navController = navController,
                            route = Route.PlanScreen.route
                        )
                    }
                )
            }
            composable(route = Route.LocationScreen.route) {BackStackEntry ->
                val viewModel: LocationViewModel = hiltViewModel()
                LocationScreen(
                    navigateUp = {navController.navigateUp()}
                )
            }
            composable(route = Route.CategoriesScreen.route) {BackStackEntry ->
                val viewModel: CategoriesViewModel = hiltViewModel()
                CategoriesScreen (
                    navigateUp = {navController.navigateUp()},
                    navigateToRestaurants = {
                        navigateToCategory(
                            navController = navController,
                            route = Route.CategoryScreen.route,
                            Categories.RESTAURANT.toString()
                        )
                    },
                    navigateToParks = {
                        navigateToCategory(
                            navController = navController,
                            route = Route.CategoryScreen.route,
                            Categories.PARK.toString()
                        )
                    },
                    navigateToMuseums = {
                        navigateToCategory(
                            navController = navController,
                            route = Route.CategoryScreen.route,
                            Categories.MUSEUM.toString()
                        )
                    }
                )
            }
            composable(route = Route.CategoryScreen.route+"/{category}",
                arguments = listOf(navArgument("category") {type = NavType.StringType})
            ) {BackStackEntry ->
                val viewModel: CategoryViewModel = hiltViewModel()
                val category = BackStackEntry.arguments?.getString("category")!!
                var route = Route.FoodPlaceFilterScreen.route
                when (Categories.valueOf(category)){
                    Categories.RESTAURANT -> route = Route.FoodPlaceFilterScreen.route
                    Categories.PARK -> route = Route.ParkPlaceFilterScreen.route
                    Categories.MUSEUM -> route = Route.MuseumPlaceFilterScreen.route
                }
                CategoryScreen (
                    category = category,
                    navigateUp = {navController.navigateUp()},
                    viewModel,
                    goToFilters = {
                        navigateToTab(
                            navController = navController,
                            route = route
                        )
                    },
                    goToDetails = {
                        place ->
                            navigateToDetails(
                                navController = navController,
                                route = Route.DetailsScreen.route,
                                place = place
                            )
                    }
                )
            }
            composable(route = Route.FoodPlaceFilterScreen.route) {BackStackEntry ->
                val viewModel: FoodPlaceFilterVM = hiltViewModel()
                FoodPlaceFilterScreen(
                    viewModel = viewModel,
                    dismiss = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = Route.ParkPlaceFilterScreen.route) {BackStackEntry ->
                val viewModel: ParkPlaceFilterVM = hiltViewModel()
                ParkPlaceFilterScreen(
                    viewModel = viewModel,
                    dismiss = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = Route.MuseumPlaceFilterScreen.route) {BackStackEntry ->
                val viewModel: MuseumPlaceFilterVM = hiltViewModel()
                MuseumPlaceFilterScreen(
                    viewModel = viewModel,
                    dismiss = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) { BackStackEntry ->
                UserData.getInstance().getDetails()
                    ?.let { place ->
                        PlaceDetails(
                            place = place,
                            navigateUp = {navController.navigateUp()},
                        )
                    }
            }
            composable(route = Route.AboutScreen.route) {BackStackEntry ->
                HowItWorksScreen(
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
        }
//    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToQuestion(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(Route.HomeScreen.route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToCategory(navController: NavController, route: String, category: String) {
    navController.navigate(route+ "/" + category){
        popUpTo(route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, route: String, place: Place) {
    UserData.getInstance().setDetails(place)
    navController.navigate(route){
        popUpTo(route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToWebsite(handler: UriHandler) {
//    handler.openUri("https://hereisrest.com.ua/")
}