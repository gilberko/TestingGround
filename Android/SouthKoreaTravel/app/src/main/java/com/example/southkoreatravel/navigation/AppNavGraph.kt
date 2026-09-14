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
import com.example.southkoreatravel.screens.GyeongjuAttractionsScreen
import com.example.southkoreatravel.screens.GyeongjuCafesScreen
import com.example.southkoreatravel.screens.GyeongjuGlutenFreeKetoScreen
import com.example.southkoreatravel.screens.GyeongjuPlacesOfInterestScreen
import com.example.southkoreatravel.screens.GyeongjuScreen
import com.example.southkoreatravel.screens.GyeongjuWhereToStayScreen
import com.example.southkoreatravel.screens.JejuCafesScreen
import com.example.southkoreatravel.screens.JejuGettingToJejuScreen
import com.example.southkoreatravel.screens.JejuGlutenFreeKetoScreen
import com.example.southkoreatravel.screens.JejuPlacesOfInterestScreen
import com.example.southkoreatravel.screens.JejuScreen
import com.example.southkoreatravel.screens.JejuWhereToStayScreen
import com.example.southkoreatravel.screens.JeonjuCafesScreen
import com.example.southkoreatravel.screens.JeonjuGlutenFreeKetoScreen
import com.example.southkoreatravel.screens.JeonjuPlacesOfInterestScreen
import com.example.southkoreatravel.screens.JeonjuScreen
import com.example.southkoreatravel.screens.JeonjuWhereToStayScreen
import com.example.southkoreatravel.screens.KDramasScreen
import com.example.southkoreatravel.screens.MainScreen
import com.example.southkoreatravel.screens.MedicalIssuesScreen
import com.example.southkoreatravel.screens.PlacesScreen
import com.example.southkoreatravel.screens.SeoulAirportTravelScreen
import com.example.southkoreatravel.screens.SeoulAttractionsScreen
import com.example.southkoreatravel.screens.SeoulCafesScreen
import com.example.southkoreatravel.screens.SeoulDayTripGangneungScreen
import com.example.southkoreatravel.screens.SeoulDayTripKoreanFolkVillageScreen
import com.example.southkoreatravel.screens.SeoulDayTripNamiIslandScreen
import com.example.southkoreatravel.screens.SeoulDayTripSuwonScreen
import com.example.southkoreatravel.screens.SeoulDayTripsScreen
import com.example.southkoreatravel.screens.SeoulGlutenFreeKetoScreen
import com.example.southkoreatravel.screens.SeoulPlacesOfInterestScreen
import com.example.southkoreatravel.screens.SeoulRegionsScreen
import com.example.southkoreatravel.screens.SeoulScreen
import com.example.southkoreatravel.screens.SeoulVisitingTheDmzScreen
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
    object SeoulDmz : Screen("seoul_dmz")
    object SeoulDayTrips : Screen("seoul_day_trips")
    object SeoulDayTripSuwon : Screen("seoul_day_trip_suwon")
    object SeoulDayTripKoreanFolkVillage : Screen("seoul_day_trip_korean_folk_village")
    object SeoulDayTripNamiIsland : Screen("seoul_day_trip_nami_island")
    object SeoulDayTripGangneung : Screen("seoul_day_trip_gangneung")
    object Busan : Screen("busan")
    object BusanRegions : Screen("busan_regions")
    object BusanPlacesOfInterest : Screen("busan_places_of_interest")
    object BusanAirportTravel : Screen("busan_airport_travel")
    object BusanGlutenFreeKeto : Screen("busan_gluten_free_keto")
    object BusanWhereToStay : Screen("busan_where_to_stay")
    object BusanAttractions : Screen("busan_attractions")
    object BusanCafes : Screen("busan_cafes")
    object Gyeongju : Screen("gyeongju")
    object GyeongjuPlacesOfInterest : Screen("gyeongju_places_of_interest")
    object GyeongjuAttractions : Screen("gyeongju_attractions")
    object GyeongjuWhereToStay : Screen("gyeongju_where_to_stay")
    object GyeongjuGlutenFreeKeto : Screen("gyeongju_gluten_free_keto")
    object GyeongjuCafes : Screen("gyeongju_cafes")
    object Jeju : Screen("jeju")
    object JejuGettingToJeju : Screen("jeju_getting_to_jeju")
    object JejuPlacesOfInterest : Screen("jeju_places_of_interest")
    object JejuWhereToStay : Screen("jeju_where_to_stay")
    object JejuGlutenFreeKeto : Screen("jeju_gluten_free_keto")
    object JejuCafes : Screen("jeju_cafes")
    object Jeonju : Screen("jeonju")
    object JeonjuPlacesOfInterest : Screen("jeonju_places_of_interest")
    object JeonjuWhereToStay : Screen("jeonju_where_to_stay")
    object JeonjuGlutenFreeKeto : Screen("jeonju_gluten_free_keto")
    object JeonjuCafes : Screen("jeonju_cafes")
    object GeneralInfo : Screen("general_info")
    object MedicalIssues : Screen("medical_issues")
    object Shopping : Screen("shopping")
    object Food : Screen("food")
    object UsefulApps : Screen("useful_apps")
    object CeliacCard : Screen("celiac_card")
    object KDramas : Screen("k_dramas")
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
                onOpenCeliacCard = { navController.navigate(Screen.CeliacCard.route) },
                onOpenKDramas = { navController.navigate(Screen.KDramas.route) }
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
                onOpenAirportTravel = { navController.navigate(Screen.SeoulAirportTravel.route) },
                onOpenDmz = { navController.navigate(Screen.SeoulDmz.route) },
                onOpenDayTrips = { navController.navigate(Screen.SeoulDayTrips.route) }
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
        composable(Screen.SeoulDmz.route) {
            SeoulVisitingTheDmzScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulDayTrips.route) {
            SeoulDayTripsScreen(
                onBack = { navController.popBackStack() },
                onOpenSuwon = { navController.navigate(Screen.SeoulDayTripSuwon.route) },
                onOpenDmz = { navController.navigate(Screen.SeoulDmz.route) },
                onOpenKoreanFolkVillage = { navController.navigate(Screen.SeoulDayTripKoreanFolkVillage.route) },
                onOpenNamiIsland = { navController.navigate(Screen.SeoulDayTripNamiIsland.route) },
                onOpenGangneung = { navController.navigate(Screen.SeoulDayTripGangneung.route) }
            )
        }
        composable(Screen.SeoulDayTripSuwon.route) {
            SeoulDayTripSuwonScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulDayTripKoreanFolkVillage.route) {
            SeoulDayTripKoreanFolkVillageScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulDayTripNamiIsland.route) {
            SeoulDayTripNamiIslandScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SeoulDayTripGangneung.route) {
            SeoulDayTripGangneungScreen(onBack = { navController.popBackStack() })
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
            GyeongjuScreen(
                onBack = { navController.popBackStack() },
                onOpenPlacesOfInterest = { navController.navigate(Screen.GyeongjuPlacesOfInterest.route) },
                onOpenAttractions = { navController.navigate(Screen.GyeongjuAttractions.route) },
                onOpenWhereToStay = { navController.navigate(Screen.GyeongjuWhereToStay.route) },
                onOpenGlutenFreeKeto = { navController.navigate(Screen.GyeongjuGlutenFreeKeto.route) },
                onOpenCafes = { navController.navigate(Screen.GyeongjuCafes.route) }
            )
        }
        composable(Screen.GyeongjuPlacesOfInterest.route) {
            GyeongjuPlacesOfInterestScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GyeongjuAttractions.route) {
            GyeongjuAttractionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GyeongjuWhereToStay.route) {
            GyeongjuWhereToStayScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GyeongjuGlutenFreeKeto.route) {
            GyeongjuGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GyeongjuCafes.route) {
            GyeongjuCafesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Jeju.route) {
            JejuScreen(
                onBack = { navController.popBackStack() },
                onOpenGettingToJeju = { navController.navigate(Screen.JejuGettingToJeju.route) },
                onOpenPlacesOfInterest = { navController.navigate(Screen.JejuPlacesOfInterest.route) },
                onOpenWhereToStay = { navController.navigate(Screen.JejuWhereToStay.route) },
                onOpenGlutenFreeKeto = { navController.navigate(Screen.JejuGlutenFreeKeto.route) },
                onOpenCafes = { navController.navigate(Screen.JejuCafes.route) }
            )
        }
        composable(Screen.JejuGettingToJeju.route) {
            JejuGettingToJejuScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.JejuPlacesOfInterest.route) {
            JejuPlacesOfInterestScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.JejuWhereToStay.route) {
            JejuWhereToStayScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.JejuGlutenFreeKeto.route) {
            JejuGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.JejuCafes.route) {
            JejuCafesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Jeonju.route) {
            JeonjuScreen(
                onBack = { navController.popBackStack() },
                onOpenPlacesOfInterest = { navController.navigate(Screen.JeonjuPlacesOfInterest.route) },
                onOpenWhereToStay = { navController.navigate(Screen.JeonjuWhereToStay.route) },
                onOpenGlutenFreeKeto = { navController.navigate(Screen.JeonjuGlutenFreeKeto.route) },
                onOpenCafes = { navController.navigate(Screen.JeonjuCafes.route) }
            )
        }
        composable(Screen.JeonjuPlacesOfInterest.route) {
            JeonjuPlacesOfInterestScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.JeonjuWhereToStay.route) {
            JeonjuWhereToStayScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.JeonjuGlutenFreeKeto.route) {
            JeonjuGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.JeonjuCafes.route) {
            JeonjuCafesScreen(onBack = { navController.popBackStack() })
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
        composable(Screen.KDramas.route) {
            KDramasScreen(onBack = { navController.popBackStack() })
        }
    }
}
