package com.example.italytravel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.italytravel.screens.MainScreen
import com.example.italytravel.screens.RomeAttractionDetailScreen
import com.example.italytravel.screens.RomeAttractionsScreen
import com.example.italytravel.screens.RomeCoffeeScreen
import com.example.italytravel.screens.RomeFastFoodScreen
import com.example.italytravel.screens.RomeFoodScreen
import com.example.italytravel.screens.RomeFornoPizzaScreen
import com.example.italytravel.screens.RomeGeneralInformationScreen
import com.example.italytravel.screens.RomeGlutenFreeScreen
import com.example.italytravel.screens.RomeIceCreamScreen
import com.example.italytravel.screens.RomeKoreanFoodScreen
import com.example.italytravel.screens.RomeDayTripDetailScreen
import com.example.italytravel.screens.RomeDayTripsScreen
import com.example.italytravel.screens.RomeScreen
import com.example.italytravel.screens.RomeShoppingCentersScreen
import com.example.italytravel.screens.RomeShoppingScreen
import com.example.italytravel.screens.SplashScreen
import com.example.italytravel.screens.RideAppsScreen
import com.example.italytravel.screens.RomeSupermarketsScreen
import com.example.italytravel.screens.RomeTransportScreen

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Main : Screen("main")
    data object RideApps : Screen("ride_apps")
    data object RomeSupermarkets : Screen("rome_supermarkets")
    data object Rome : Screen("rome")
    data object RomeNature : Screen("rome_nature_day_trips")
    data object RomeCoffee : Screen("rome_coffee")
    data object RomeFood : Screen("rome_food")
    data object RomeFornoPizza : Screen("rome_forno_pizza")
    data object RomeKoreanFood : Screen("rome_korean_food")
    data object RomeIceCream : Screen("rome_ice_cream")
    data object RomeGlutenFree : Screen("rome_gluten_free")
    data object RomeFastFood : Screen("rome_fast_food")
    data object RomeShoppingCenters : Screen("rome_shopping_centers")
    data object RomeShopping : Screen("rome_shopping")
    data object RomeAttractions : Screen("rome_attractions")
    data object RomeDayTrip : Screen("rome_day_trip/{id}") {
        fun routeFor(id: String) = "rome_day_trip/$id"
    }
    data object RomeAttraction : Screen("rome_attraction/{id}") {
        fun routeFor(id: String) = "rome_attraction/$id"
    }
    data object RomeGeneralInfo : Screen("rome_general_information")
    data object RomeTransport : Screen("rome_public_transportation")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val back: () -> Unit = { navController.popBackStack() }
    fun go(screen: Screen): () -> Unit = { navController.navigate(screen.route) }

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onDone = {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Main.route) {
            MainScreen(onOpenRome = go(Screen.Rome), onOpenRideApps = go(Screen.RideApps))
        }
        composable(Screen.Rome.route) {
            RomeScreen(
                onBack = back,
                onOpenNature = go(Screen.RomeNature),
                onOpenCoffee = go(Screen.RomeCoffee),
                onOpenFood = go(Screen.RomeFood),
                onOpenFornoPizza = go(Screen.RomeFornoPizza),
                onOpenKoreanFood = go(Screen.RomeKoreanFood),
                onOpenIceCream = go(Screen.RomeIceCream),
                onOpenGlutenFree = go(Screen.RomeGlutenFree),
                onOpenFastFood = go(Screen.RomeFastFood),
                onOpenShopping = go(Screen.RomeShopping),
                onOpenShoppingCenters = go(Screen.RomeShoppingCenters),
                onOpenSupermarkets = go(Screen.RomeSupermarkets),
                onOpenAttractions = go(Screen.RomeAttractions),
                onOpenGeneralInfo = go(Screen.RomeGeneralInfo),
                onOpenTransport = go(Screen.RomeTransport)
            )
        }
        composable(Screen.RomeNature.route) {
            RomeDayTripsScreen(
                onBack = back,
                onOpen = { id -> navController.navigate(Screen.RomeDayTrip.routeFor(id)) }
            )
        }
        composable(
            Screen.RomeDayTrip.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            RomeDayTripDetailScreen(id = entry.arguments?.getString("id").orEmpty(), onBack = back)
        }
        composable(Screen.RomeCoffee.route) { RomeCoffeeScreen(onBack = back) }
        composable(Screen.RomeFood.route) { RomeFoodScreen(onBack = back) }
        composable(Screen.RomeFornoPizza.route) { RomeFornoPizzaScreen(onBack = back) }
        composable(Screen.RomeKoreanFood.route) { RomeKoreanFoodScreen(onBack = back) }
        composable(Screen.RomeIceCream.route) { RomeIceCreamScreen(onBack = back) }
        composable(Screen.RomeGlutenFree.route) { RomeGlutenFreeScreen(onBack = back) }
        composable(Screen.RomeFastFood.route) { RomeFastFoodScreen(onBack = back) }
        composable(Screen.RomeShoppingCenters.route) { RomeShoppingCentersScreen(onBack = back) }
        composable(Screen.RomeShopping.route) { RomeShoppingScreen(onBack = back) }
        composable(Screen.RomeAttractions.route) {
            RomeAttractionsScreen(
                onBack = back,
                onOpen = { id -> navController.navigate(Screen.RomeAttraction.routeFor(id)) }
            )
        }
        composable(
            Screen.RomeAttraction.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            RomeAttractionDetailScreen(id = entry.arguments?.getString("id").orEmpty(), onBack = back)
        }
        composable(Screen.RideApps.route) { RideAppsScreen(onBack = back) }
        composable(Screen.RomeSupermarkets.route) { RomeSupermarketsScreen(onBack = back) }
        composable(Screen.RomeTransport.route) { RomeTransportScreen(onBack = back) }
        composable(Screen.RomeGeneralInfo.route) { RomeGeneralInformationScreen(onBack = back) }
    }
}
