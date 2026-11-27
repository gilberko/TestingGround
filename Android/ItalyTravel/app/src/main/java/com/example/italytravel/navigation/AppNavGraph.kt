package com.example.italytravel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.italytravel.screens.MainScreen
import com.example.italytravel.screens.RomeAttractionsScreen
import com.example.italytravel.screens.RomeCoffeeScreen
import com.example.italytravel.screens.RomeFoodScreen
import com.example.italytravel.screens.RomeGeneralInformationScreen
import com.example.italytravel.screens.RomeNatureScreen
import com.example.italytravel.screens.RomeScreen
import com.example.italytravel.screens.RomeShoppingScreen

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Rome : Screen("rome")
    data object RomeNature : Screen("rome_nature_day_trips")
    data object RomeCoffee : Screen("rome_coffee")
    data object RomeFood : Screen("rome_food")
    data object RomeShopping : Screen("rome_shopping")
    data object RomeAttractions : Screen("rome_attractions")
    data object RomeGeneralInfo : Screen("rome_general_information")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val back: () -> Unit = { navController.popBackStack() }
    fun go(screen: Screen): () -> Unit = { navController.navigate(screen.route) }

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(onOpenRome = go(Screen.Rome))
        }
        composable(Screen.Rome.route) {
            RomeScreen(
                onBack = back,
                onOpenNature = go(Screen.RomeNature),
                onOpenCoffee = go(Screen.RomeCoffee),
                onOpenFood = go(Screen.RomeFood),
                onOpenShopping = go(Screen.RomeShopping),
                onOpenAttractions = go(Screen.RomeAttractions),
                onOpenGeneralInfo = go(Screen.RomeGeneralInfo)
            )
        }
        composable(Screen.RomeNature.route) { RomeNatureScreen(onBack = back) }
        composable(Screen.RomeCoffee.route) { RomeCoffeeScreen(onBack = back) }
        composable(Screen.RomeFood.route) { RomeFoodScreen(onBack = back) }
        composable(Screen.RomeShopping.route) { RomeShoppingScreen(onBack = back) }
        composable(Screen.RomeAttractions.route) { RomeAttractionsScreen(onBack = back) }
        composable(Screen.RomeGeneralInfo.route) { RomeGeneralInformationScreen(onBack = back) }
    }
}
