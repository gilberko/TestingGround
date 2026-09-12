package com.example.southkoreatravel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.southkoreatravel.screens.BusanAirportTravelScreen
import com.example.southkoreatravel.screens.BusanAttractionsScreen
import com.example.southkoreatravel.screens.BusanCafesScreen
import com.example.southkoreatravel.screens.BusanGlutenFreeKetoScreen
import com.example.southkoreatravel.screens.BusanPlacesOfInterestScreen
import com.example.southkoreatravel.screens.BusanRegionsScreen
import com.example.southkoreatravel.screens.BusanScreen
import com.example.southkoreatravel.screens.BusanWhereToStayScreen
import com.example.southkoreatravel.screens.CeliacCardScreen
import com.example.southkoreatravel.screens.FoodScreen
import com.example.southkoreatravel.screens.GeneralInformationScreen
import com.example.southkoreatravel.screens.GyeongjuScreen
import com.example.southkoreatravel.screens.JejuScreen
import com.example.southkoreatravel.screens.JeonjuScreen
import com.example.southkoreatravel.screens.MainScreen
import com.example.southkoreatravel.screens.MedicalIssuesScreen
import com.example.southkoreatravel.screens.PlacesScreen
import com.example.southkoreatravel.screens.SeoulAirportTravelScreen
import com.example.southkoreatravel.screens.SeoulAttractionsScreen
import com.example.southkoreatravel.screens.SeoulCafesScreen
import com.example.southkoreatravel.screens.SeoulGlutenFreeKetoScreen
import com.example.southkoreatravel.screens.SeoulPlacesOfInterestScreen
import com.example.southkoreatravel.screens.SeoulRegionsScreen
import com.example.southkoreatravel.screens.SeoulScreen
import com.example.southkoreatravel.screens.SeoulWhereToStayScreen
import com.example.southkoreatravel.screens.ShoppingScreen
import com.example.southkoreatravel.screens.SplashScreen
import com.example.southkoreatravel.screens.UsefulAppsScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Places : Screen("places")
    object Seoul : Screen("seoul")
    object SeoulPlacesOfInterest : Screen("seoul_places_of_interest")
    object SeoulAttractions : Screen("seoul_attractions")
    object SeoulCafes : Screen("seoul_cafes")
    object SeoulGlutenFreeKeto : Screen("seoul_gluten_free_keto")
    object SeoulRegions : Screen("seoul_regions")
    object SeoulWhereToStay : Screen("seoul_where_to_stay")
    object SeoulAirportTravel : Screen("seoul_airport_travel")
    object Busan : Screen("busan")
    object BusanRegions : Screen("busan_regions")
    object BusanPlacesOfInterest : Screen("busan_places_of_interest")
    object BusanAirportTravel : Screen("busan_airport_travel")
    object BusanGlutenFreeKeto : Screen("busan_gluten_free_keto")
    object BusanWhereToStay : Screen("busan_where_to_stay")
    object BusanAttractions : Screen("busan_attractions")
    object BusanCafes : Screen("busan_cafes")
    object Gyeongju : Screen("gyeongju")
    object Jeju : Screen("jeju")
    object Jeonju : Screen("jeonju")
    object GeneralInfo : Screen("general_info")
    object MedicalIssues : Screen("medical_issues")
    object Shopping : Screen("shopping")
    object Food : Screen("food")
    object UsefulApps : Screen("useful_apps")
    object CeliacCard : Screen("celiac_card")
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
                onOpenFood = { navController.navigate(Screen.Food.route) },
                onOpenUsefulApps = { navController.navigate(Screen.UsefulApps.route) },
                onOpenCeliacCard = { navController.navigate(Screen.CeliacCard.route) }
            )
        }
        composable(Screen.Places.route) {
            PlacesScreen(
                onBack = { navController.popBackStack() },
                onOpenSeoul = { navController.navigate(Screen.Seoul.route) },
                onOpenBusan = { navController.navigate(Screen.Busan.route) },
                onOpenGyeongju = { navController.navigate(Screen.Gyeongju.route) },
                onOpenJeju = { navController.navigate(Screen.Jeju.route) },
                onOpenJeonju = { navController.navigate(Screen.Jeonju.route) }
            )
        }
        composable(Screen.Seoul.route) {
            SeoulScreen(
                onBack = { navController.popBackStack() },
                onOpenPlacesOfInterest = { navController.navigate(Screen.SeoulPlacesOfInterest.route) },
                onOpenAttractions = { navController.navigate(Screen.SeoulAttractions.route) },
                onOpenCafes = { navController.navigate(Screen.SeoulCafes.route) },
                onOpenGlutenFreeKeto = { navController.navigate(Screen.SeoulGlutenFreeKeto.route) },
                onOpenRegions = { navController.navigate(Screen.SeoulRegions.route) },
                onOpenWhereToStay = { navController.navigate(Screen.SeoulWhereToStay.route) },
                onOpenAirportTravel = { navController.navigate(Screen.SeoulAirportTravel.route) }
            )
        }
        composable(Screen.SeoulPlacesOfInterest.route) {
            SeoulPlacesOfInterestScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulAttractions.route) {
            SeoulAttractionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulCafes.route) {
            SeoulCafesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulGlutenFreeKeto.route) {
            SeoulGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulRegions.route) {
            SeoulRegionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulWhereToStay.route) {
            SeoulWhereToStayScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulAirportTravel.route) {
            SeoulAirportTravelScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Busan.route) {
            BusanScreen(
                onBack = { navController.popBackStack() },
                onOpenRegions = { navController.navigate(Screen.BusanRegions.route) },
                onOpenPlacesOfInterest = { navController.navigate(Screen.BusanPlacesOfInterest.route) },
                onOpenAirportTravel = { navController.navigate(Screen.BusanAirportTravel.route) },
                onOpenGlutenFreeKeto = { navController.navigate(Screen.BusanGlutenFreeKeto.route) },
                onOpenWhereToStay = { navController.navigate(Screen.BusanWhereToStay.route) },
                onOpenAttractions = { navController.navigate(Screen.BusanAttractions.route) },
                onOpenCafes = { navController.navigate(Screen.BusanCafes.route) }
            )
        }
        composable(Screen.BusanRegions.route) {
            BusanRegionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.BusanPlacesOfInterest.route) {
            BusanPlacesOfInterestScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.BusanAirportTravel.route) {
            BusanAirportTravelScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.BusanGlutenFreeKeto.route) {
            BusanGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.BusanWhereToStay.route) {
            BusanWhereToStayScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.BusanAttractions.route) {
            BusanAttractionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.BusanCafes.route) {
            BusanCafesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Gyeongju.route) {
            GyeongjuScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Jeju.route) {
            JejuScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Jeonju.route) {
            JeonjuScreen(onBack = { navController.popBackStack() })
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
        composable(Screen.UsefulApps.route) {
            UsefulAppsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.CeliacCard.route) {
            CeliacCardScreen(onBack = { navController.popBackStack() })
        }
    }
}
