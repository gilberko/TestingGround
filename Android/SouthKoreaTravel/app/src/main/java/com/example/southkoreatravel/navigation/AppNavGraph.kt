package com.example.southkoreatravel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.southkoreatravel.screens.BusanScreen
import com.example.southkoreatravel.screens.FoodScreen
import com.example.southkoreatravel.screens.GeneralInformationScreen
import com.example.southkoreatravel.screens.MainScreen
import com.example.southkoreatravel.screens.MedicalIssuesScreen
import com.example.southkoreatravel.screens.PlacesScreen
import com.example.southkoreatravel.screens.SeoulScreen
import com.example.southkoreatravel.screens.ShoppingScreen
import com.example.southkoreatravel.screens.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Places : Screen("places")
    object Seoul : Screen("seoul")
    object Busan : Screen("busan")
    object GeneralInfo : Screen("general_info")
    object MedicalIssues : Screen("medical_issues")
    object Shopping : Screen("shopping")
    object Food : Screen("food")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Main.route) {
            MainScreen(
                onOpenPlaces = { navController.navigate(Screen.Places.route) },
                onOpenGeneralInfo = { navController.navigate(Screen.GeneralInfo.route) },
                onOpenMedicalIssues = { navController.navigate(Screen.MedicalIssues.route) },
                onOpenShopping = { navController.navigate(Screen.Shopping.route) },
                onOpenFood = { navController.navigate(Screen.Food.route) }
            )
        }
        composable(Screen.Places.route) {
            PlacesScreen(
                onBack = { navController.popBackStack() },
                onOpenSeoul = { navController.navigate(Screen.Seoul.route) },
                onOpenBusan = { navController.navigate(Screen.Busan.route) }
            )
        }
        composable(Screen.Seoul.route) {
            SeoulScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Busan.route) {
            BusanScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GeneralInfo.route) {
            GeneralInformationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.MedicalIssues.route) {
            MedicalIssuesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Shopping.route) {
            ShoppingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Food.route) {
            FoodScreen(onBack = { navController.popBackStack() })
        }
    }
}
