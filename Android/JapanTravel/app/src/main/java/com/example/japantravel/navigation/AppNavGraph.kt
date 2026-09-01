package com.example.japantravel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.japantravel.R
import com.example.japantravel.screens.AboutFoodScreen
import com.example.japantravel.screens.AmanohashidateDayTripScreen
import com.example.japantravel.screens.ClassicalCultureScreen
import com.example.japantravel.screens.FoodAndCafesScreen
import com.example.japantravel.screens.FujiQScreen
import com.example.japantravel.screens.GeneralInformationScreen
import com.example.japantravel.screens.GhibliMuseumScreen
import com.example.japantravel.screens.GhibliParkDayTripScreen
import com.example.japantravel.screens.GhibliParkScreen
import com.example.japantravel.screens.HakoneDayTripScreen
import com.example.japantravel.screens.HakoneGettingThereScreen
import com.example.japantravel.screens.HakoneRopewayScreen
import com.example.japantravel.screens.HakoneScreen
import com.example.japantravel.screens.HomeScreen
import com.example.japantravel.screens.JoypolisScreen
import com.example.japantravel.screens.KawagoeDayTripScreen
import com.example.japantravel.screens.KawaguchikoOverviewScreen
import com.example.japantravel.screens.KawaguchikoScreen
import com.example.japantravel.screens.KyotoCityRegionsScreen
import com.example.japantravel.screens.KyotoDayPlansScreen
import com.example.japantravel.screens.KyotoGettingAroundScreen
import com.example.japantravel.screens.KyotoGlutenFreeKetoScreen
import com.example.japantravel.screens.KyotoMapsScreen
import com.example.japantravel.screens.KyotoOverviewScreen
import com.example.japantravel.screens.KyotoParksScreen
import com.example.japantravel.screens.KyotoScreen
import com.example.japantravel.screens.KyotoTeamLabBiovortexScreen
import com.example.japantravel.screens.KyotoToeiStudioParkScreen
import com.example.japantravel.screens.KyotoWhereToSleepScreen
import com.example.japantravel.screens.LakesKawaguchikoDayTripScreen
import com.example.japantravel.screens.MapImageScreen
import com.example.japantravel.screens.MedicalIssuesScreen
import com.example.japantravel.screens.MountTakaoDayTripScreen
import com.example.japantravel.screens.NaraGettingThereScreen
import com.example.japantravel.screens.NaraScreen
import com.example.japantravel.screens.NaraTemplesAndDeerScreen
import com.example.japantravel.screens.NikkoDayTripScreen
import com.example.japantravel.screens.NinjaMuseumScreen
import com.example.japantravel.screens.NintendoMuseumScreen
import com.example.japantravel.screens.OkutamaMitakeDayTripScreen
import com.example.japantravel.screens.OsakaAirportTravelScreen
import com.example.japantravel.screens.OsakaDayPlansScreen
import com.example.japantravel.screens.OsakaGettingAroundScreen
import com.example.japantravel.screens.OsakaGlutenFreeKetoScreen
import com.example.japantravel.screens.OsakaMapsScreen
import com.example.japantravel.screens.OsakaOverviewScreen
import com.example.japantravel.screens.OsakaParksScreen
import com.example.japantravel.screens.OsakaScreen
import com.example.japantravel.screens.OsakaTeamLabBotanicalGardenScreen
import com.example.japantravel.screens.OsakaWhereToSleepScreen
import com.example.japantravel.screens.PlaceDetailScreen
import com.example.japantravel.screens.PlacesScreen
import com.example.japantravel.screens.SamuraiNinjaTeaCeremonyScreen
import com.example.japantravel.screens.SpecialAnimalPlacesScreen
import com.example.japantravel.screens.SplashScreen
import com.example.japantravel.screens.StoresAndChainsScreen
import com.example.japantravel.screens.TeamLabBorderlessScreen
import com.example.japantravel.screens.TeamLabPlanetsScreen
import com.example.japantravel.screens.TokyoAirportTravelScreen
import com.example.japantravel.screens.TokyoCityRegionsScreen
import com.example.japantravel.screens.TokyoDayPlansScreen
import com.example.japantravel.screens.TokyoDayTripsScreen
import com.example.japantravel.screens.TokyoDisneyScreen
import com.example.japantravel.screens.TokyoDomeCityScreen
import com.example.japantravel.screens.TokyoGlutenFreeKetoScreen
import com.example.japantravel.screens.TokyoMapsScreen
import com.example.japantravel.screens.TokyoOutsideAreaScreen
import com.example.japantravel.screens.TokyoParksScreen
import com.example.japantravel.screens.TokyoPlacesOfInterestScreen
import com.example.japantravel.screens.TokyoScreen
import com.example.japantravel.screens.TokyoSpecialCafesScreen
import com.example.japantravel.screens.TokyoSummerlandScreen
import com.example.japantravel.screens.TokyoToeiAnimationMuseumScreen
import com.example.japantravel.screens.TokyoWhereToSleepScreen
import com.example.japantravel.screens.UsefulAppsScreen
import com.example.japantravel.screens.UsjScreen
import com.example.japantravel.screens.WarnerBrosHarryPotterScreen
import com.example.japantravel.screens.WeatherScreen
import com.example.japantravel.screens.YokohamaDayTripScreen
import com.example.japantravel.screens.YomiurilandScreen

sealed class Screen(val route: String) {
    object Splash                  : Screen("splash")
    object Home                    : Screen("home")
    object Places                  : Screen("places")
    object PlaceDetail             : Screen("place/{placeName}")
    object GeneralInfo             : Screen("general_info")
    object UsefulApps              : Screen("useful_apps")
    object StoresAndChains         : Screen("stores_and_chains")
    object ClassicalCulture        : Screen("classical_culture")
    object MedicalIssues           : Screen("medical_issues")
    object FoodAndCafes            : Screen("food_and_cafes")
    object AboutFood               : Screen("about_food")
    object SpecialAnimalPlaces     : Screen("special_animal_places")

    object Tokyo                   : Screen("tokyo")
    object TokyoParks              : Screen("tokyo_parks")
    object TokyoDisney             : Screen("tokyo_disney")
    object TokyoJoypolis           : Screen("tokyo_joypolis")
    object TokyoDomeCity           : Screen("tokyo_dome_city")
    object TokyoTeamLabPlanets     : Screen("tokyo_teamlab_planets")
    object TokyoTeamLabBorderless  : Screen("tokyo_teamlab_borderless")
    object TokyoGhibliMuseum       : Screen("tokyo_ghibli_museum")
    object TokyoCityRegions        : Screen("tokyo_city_regions")
    object TokyoSpecialCafes       : Screen("tokyo_special_cafes")
    object TokyoYomiuriland        : Screen("tokyo_yomiuriland")
    object TokyoSummerland         : Screen("tokyo_summerland")
    object TokyoPlacesOfInterest   : Screen("tokyo_places_of_interest")
    object TokyoSamuraiNinjaTeaCeremony : Screen("tokyo_samurai_ninja_tea_ceremony")
    object TokyoWarnerBrosHarryPotter : Screen("tokyo_warner_bros_harry_potter")
    object TokyoToeiAnimationMuseum : Screen("tokyo_toei_animation_museum")
    object TokyoGlutenFreeKeto     : Screen("tokyo_gluten_free_keto")
    object TokyoDayTrips           : Screen("tokyo_day_trips")
    object TokyoAirportTravel      : Screen("tokyo_airport_travel")
    object TokyoDayPlans           : Screen("tokyo_day_plans")
    object TokyoMaps               : Screen("tokyo_maps")
    object TokyoSubwayMap          : Screen("tokyo_subway_map")
    object TokyoCityMap            : Screen("tokyo_city_map")
    object TokyoWhereToSleep       : Screen("tokyo_where_to_sleep")
    object HakoneDayTrip           : Screen("hakone_day_trip")
    object NikkoDayTrip            : Screen("nikko_day_trip")
    object LakesKawaguchikoDayTrip : Screen("lakes_kawaguchiko_day_trip")
    object MountTakaoDayTrip       : Screen("mount_takao_day_trip")
    object GhibliParkDayTrip       : Screen("ghibli_park_day_trip")
    object YokohamaDayTrip         : Screen("yokohama_day_trip")
    object KawagoeDayTrip          : Screen("kawagoe_day_trip")
    object OkutamaMitakeDayTrip    : Screen("okutama_mitake_day_trip")

    object Osaka                   : Screen("osaka")
    object OsakaOverview           : Screen("osaka_overview")
    object OsakaParks              : Screen("osaka_parks")
    object Usj                     : Screen("usj")
    object OsakaTeamLabBotanicalGarden : Screen("osaka_teamlab_botanical_garden")
    object OsakaGlutenFreeKeto     : Screen("osaka_gluten_free_keto")
    object OsakaGettingAround      : Screen("osaka_getting_around")
    object OsakaAirportTravel      : Screen("osaka_airport_travel")
    object OsakaDayPlans           : Screen("osaka_day_plans")
    object OsakaMaps               : Screen("osaka_maps")
    object OsakaSubwayMap          : Screen("osaka_subway_map")
    object OsakaCityMap            : Screen("osaka_city_map")
    object OsakaWhereToSleep       : Screen("osaka_where_to_sleep")

    object Kyoto                   : Screen("kyoto")
    object KyotoCityRegions        : Screen("kyoto_city_regions")
    object KyotoParks              : Screen("kyoto_parks")
    object NinjaMuseum             : Screen("ninja_museum")
    object KyotoNintendoMuseum     : Screen("kyoto_nintendo_museum")
    object KyotoOverview           : Screen("kyoto_overview")
    object KyotoTeamLabBiovortex   : Screen("kyoto_teamlab_biovortex")
    object KyotoToeiStudioPark     : Screen("kyoto_toei_studio_park")
    object KyotoGlutenFreeKeto     : Screen("kyoto_gluten_free_keto")
    object KyotoGettingAround      : Screen("kyoto_getting_around")
    object KyotoDayPlans           : Screen("kyoto_day_plans")
    object KyotoMaps               : Screen("kyoto_maps")
    object KyotoSubwayMap          : Screen("kyoto_subway_map")
    object KyotoCityMap            : Screen("kyoto_city_map")
    object KyotoAmanohashidateDayTrip : Screen("kyoto_amanohashidate_day_trip")
    object KyotoWhereToSleep       : Screen("kyoto_where_to_sleep")

    object Kawaguchiko             : Screen("kawaguchiko")
    object KawaguchikoOverview     : Screen("kawaguchiko_overview")
    object FujiQ                   : Screen("fuji_q")

    object Hakone                  : Screen("hakone")
    object HakoneGettingThere      : Screen("hakone_getting_there")
    object HakoneRopeway           : Screen("hakone_ropeway")

    object Nara                    : Screen("nara")
    object NaraGettingThere        : Screen("nara_getting_there")
    object NaraTemplesAndDeer      : Screen("nara_temples_and_deer")

    object GhibliPark              : Screen("ghibli_park")

    object TokyoOutsideArea        : Screen("tokyo_outside_area")

    object Weather                 : Screen("weather")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenPlaces            = { navController.navigate(Screen.Places.route) },
                onOpenGeneralInfo       = { navController.navigate(Screen.GeneralInfo.route) },
                onOpenUsefulApps        = { navController.navigate(Screen.UsefulApps.route) },
                onOpenStoresAndChains   = { navController.navigate(Screen.StoresAndChains.route) },
                onOpenClassicalCulture  = { navController.navigate(Screen.ClassicalCulture.route) },
                onOpenMedicalIssues     = { navController.navigate(Screen.MedicalIssues.route) },
                onOpenFoodAndCafes          = { navController.navigate(Screen.FoodAndCafes.route) },
                onOpenSpecialAnimalPlaces   = { navController.navigate(Screen.SpecialAnimalPlaces.route) },
                onOpenAboutFood             = { navController.navigate(Screen.AboutFood.route) },
                onOpenWeather               = { navController.navigate(Screen.Weather.route) }
            )
        }
        composable(Screen.Weather.route) {
            WeatherScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Places.route) {
            PlacesScreen(
                onBack      = { navController.popBackStack() },
                onOpenPlace = { placeName ->
                    when (placeName) {
                        "Tokyo"         -> navController.navigate(Screen.Tokyo.route)
                        "Osaka"         -> navController.navigate(Screen.Osaka.route)
                        "Kyoto"         -> navController.navigate(Screen.Kyoto.route)
                        "Kawaguchiko"   -> navController.navigate(Screen.Kawaguchiko.route)
                        "Hakone"        -> navController.navigate(Screen.Hakone.route)
                        "Nara"          -> navController.navigate(Screen.Nara.route)
                        "Ghibli Park"   -> navController.navigate(Screen.GhibliPark.route)
                        else            -> navController.navigate("place/$placeName")
                    }
                }
            )
        }
        composable(
            Screen.PlaceDetail.route,
            arguments = listOf(navArgument("placeName") { type = NavType.StringType })
        ) { backStackEntry ->
            val placeName = backStackEntry.arguments?.getString("placeName") ?: ""
            PlaceDetailScreen(placeName = placeName, onBack = { navController.popBackStack() })
        }
        composable(Screen.GeneralInfo.route) {
            GeneralInformationScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UsefulApps.route) {
            UsefulAppsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.StoresAndChains.route) {
            StoresAndChainsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ClassicalCulture.route) {
            ClassicalCultureScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.MedicalIssues.route) {
            MedicalIssuesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.FoodAndCafes.route) {
            FoodAndCafesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AboutFood.route) {
            AboutFoodScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SpecialAnimalPlaces.route) {
            SpecialAnimalPlacesScreen(onBack = { navController.popBackStack() })
        }

        // Tokyo
        composable(Screen.Tokyo.route) {
            TokyoScreen(
                onBack                      = { navController.popBackStack() },
                onOpenParksAndAttractions   = { navController.navigate(Screen.TokyoParks.route) },
                onOpenCityRegions           = { navController.navigate(Screen.TokyoCityRegions.route) },
                onOpenSpecialCafes          = { navController.navigate(Screen.TokyoSpecialCafes.route) },
                onOpenOutsideTokyo          = { navController.navigate(Screen.TokyoOutsideArea.route) },
                onOpenPlacesOfInterest      = { navController.navigate(Screen.TokyoPlacesOfInterest.route) },
                onOpenGlutenFreeAndKeto     = { navController.navigate(Screen.TokyoGlutenFreeKeto.route) },
                onOpenDayTrips              = { navController.navigate(Screen.TokyoDayTrips.route) },
                onOpenAirportTravel         = { navController.navigate(Screen.TokyoAirportTravel.route) },
                onOpenDayPlans              = { navController.navigate(Screen.TokyoDayPlans.route) },
                onOpenMaps                  = { navController.navigate(Screen.TokyoMaps.route) },
                onOpenWhereToSleep          = { navController.navigate(Screen.TokyoWhereToSleep.route) }
            )
        }
        composable(Screen.TokyoOutsideArea.route) {
            TokyoOutsideAreaScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoAirportTravel.route) {
            TokyoAirportTravelScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoDayPlans.route) {
            TokyoDayPlansScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoMaps.route) {
            TokyoMapsScreen(
                onBack              = { navController.popBackStack() },
                onOpenSubwayMap     = { navController.navigate(Screen.TokyoSubwayMap.route) },
                onOpenCityMap       = { navController.navigate(Screen.TokyoCityMap.route) }
            )
        }
        composable(Screen.TokyoSubwayMap.route) {
            MapImageScreen(
                title = "Tokyo Subway Map",
                onBack = { navController.popBackStack() },
                imageRes = R.drawable.tokyo_subway_map,
                sourceCredit = "Source: Tokyo Subway Linemap (en), Wikimedia Commons, CC BY-SA 4.0"
            )
        }
        composable(Screen.TokyoCityMap.route) {
            MapImageScreen(
                title = "Tokyo City Map",
                onBack = { navController.popBackStack() },
                imageRes = R.drawable.tokyo_city_map,
                sourceCredit = "Source: Tokyo Special Wards map, Wikimedia Commons, CC BY-SA 3.0"
            )
        }
        composable(Screen.TokyoPlacesOfInterest.route) {
            TokyoPlacesOfInterestScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoGlutenFreeKeto.route) {
            TokyoGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoDayTrips.route) {
            TokyoDayTripsScreen(
                onBack                      = { navController.popBackStack() },
                onOpenHakone                = { navController.navigate(Screen.HakoneDayTrip.route) },
                onOpenNikko                 = { navController.navigate(Screen.NikkoDayTrip.route) },
                onOpenLakesKawaguchiko      = { navController.navigate(Screen.LakesKawaguchikoDayTrip.route) },
                onOpenMountTakao            = { navController.navigate(Screen.MountTakaoDayTrip.route) },
                onOpenGhibliPark            = { navController.navigate(Screen.GhibliParkDayTrip.route) },
                onOpenYokohama              = { navController.navigate(Screen.YokohamaDayTrip.route) },
                onOpenKawagoe               = { navController.navigate(Screen.KawagoeDayTrip.route) },
                onOpenOkutamaMitake         = { navController.navigate(Screen.OkutamaMitakeDayTrip.route) }
            )
        }
        composable(Screen.HakoneDayTrip.route) {
            HakoneDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.NikkoDayTrip.route) {
            NikkoDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.LakesKawaguchikoDayTrip.route) {
            LakesKawaguchikoDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.MountTakaoDayTrip.route) {
            MountTakaoDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.GhibliParkDayTrip.route) {
            GhibliParkDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.YokohamaDayTrip.route) {
            YokohamaDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KawagoeDayTrip.route) {
            KawagoeDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OkutamaMitakeDayTrip.route) {
            OkutamaMitakeDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoParks.route) {
            TokyoParksScreen(
                onBack                      = { navController.popBackStack() },
                onOpenDisney                = { navController.navigate(Screen.TokyoDisney.route) },
                onOpenJoypolis              = { navController.navigate(Screen.TokyoJoypolis.route) },
                onOpenTokyoDomeCity         = { navController.navigate(Screen.TokyoDomeCity.route) },
                onOpenTeamLabPlanets        = { navController.navigate(Screen.TokyoTeamLabPlanets.route) },
                onOpenTeamLabBorderless     = { navController.navigate(Screen.TokyoTeamLabBorderless.route) },
                onOpenGhibliMuseum          = { navController.navigate(Screen.TokyoGhibliMuseum.route) },
                onOpenYomiuriland           = { navController.navigate(Screen.TokyoYomiuriland.route) },
                onOpenSummerland            = { navController.navigate(Screen.TokyoSummerland.route) },
                onOpenSamuraiNinjaTeaCeremony = { navController.navigate(Screen.TokyoSamuraiNinjaTeaCeremony.route) },
                onOpenWarnerBrosHarryPotter = { navController.navigate(Screen.TokyoWarnerBrosHarryPotter.route) },
                onOpenToeiAnimationMuseum   = { navController.navigate(Screen.TokyoToeiAnimationMuseum.route) }
            )
        }
        composable(Screen.TokyoToeiAnimationMuseum.route) {
            TokyoToeiAnimationMuseumScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoDisney.route) {
            TokyoDisneyScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoJoypolis.route) {
            JoypolisScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoDomeCity.route) {
            TokyoDomeCityScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoTeamLabPlanets.route) {
            TeamLabPlanetsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoTeamLabBorderless.route) {
            TeamLabBorderlessScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoGhibliMuseum.route) {
            GhibliMuseumScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoYomiuriland.route) {
            YomiurilandScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoSummerland.route) {
            TokyoSummerlandScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoSamuraiNinjaTeaCeremony.route) {
            SamuraiNinjaTeaCeremonyScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoWarnerBrosHarryPotter.route) {
            WarnerBrosHarryPotterScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoCityRegions.route) {
            TokyoCityRegionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoSpecialCafes.route) {
            TokyoSpecialCafesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoWhereToSleep.route) {
            TokyoWhereToSleepScreen(onBack = { navController.popBackStack() })
        }

        // Osaka
        composable(Screen.Osaka.route) {
            OsakaScreen(
                onBack                      = { navController.popBackStack() },
                onOpenParksAndAttractions   = { navController.navigate(Screen.OsakaParks.route) },
                onOpenOverview              = { navController.navigate(Screen.OsakaOverview.route) },
                onOpenGlutenFreeAndKeto     = { navController.navigate(Screen.OsakaGlutenFreeKeto.route) },
                onOpenGettingAround         = { navController.navigate(Screen.OsakaGettingAround.route) },
                onOpenAirportTravel         = { navController.navigate(Screen.OsakaAirportTravel.route) },
                onOpenDayPlans              = { navController.navigate(Screen.OsakaDayPlans.route) },
                onOpenMaps                  = { navController.navigate(Screen.OsakaMaps.route) },
                onOpenWhereToSleep          = { navController.navigate(Screen.OsakaWhereToSleep.route) }
            )
        }
        composable(Screen.OsakaDayPlans.route) {
            OsakaDayPlansScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaMaps.route) {
            OsakaMapsScreen(
                onBack              = { navController.popBackStack() },
                onOpenSubwayMap     = { navController.navigate(Screen.OsakaSubwayMap.route) },
                onOpenCityMap       = { navController.navigate(Screen.OsakaCityMap.route) }
            )
        }
        composable(Screen.OsakaSubwayMap.route) {
            MapImageScreen(
                title = "Osaka Subway Map",
                onBack = { navController.popBackStack() },
                imageRes = R.drawable.osaka_subway_map,
                sourceCredit = "Source: Wide-Area Map of Osaka City Subway and Newtram, Wikimedia Commons, public domain"
            )
        }
        composable(Screen.OsakaCityMap.route) {
            MapImageScreen(
                title = "Osaka City Map",
                onBack = { navController.popBackStack() },
                imageRes = R.drawable.osaka_city_map,
                sourceCredit = "Source: Osaka Wards map, Wikimedia Commons, CC BY-SA 3.0"
            )
        }
        composable(Screen.OsakaOverview.route) {
            OsakaOverviewScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaGlutenFreeKeto.route) {
            OsakaGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaGettingAround.route) {
            OsakaGettingAroundScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaParks.route) {
            OsakaParksScreen(
                onBack                          = { navController.popBackStack() },
                onOpenUsj                       = { navController.navigate(Screen.Usj.route) },
                onOpenTeamLabBotanicalGarden    = { navController.navigate(Screen.OsakaTeamLabBotanicalGarden.route) }
            )
        }
        composable(Screen.Usj.route) {
            UsjScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaTeamLabBotanicalGarden.route) {
            OsakaTeamLabBotanicalGardenScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaAirportTravel.route) {
            OsakaAirportTravelScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaWhereToSleep.route) {
            OsakaWhereToSleepScreen(onBack = { navController.popBackStack() })
        }

        // Kyoto
        composable(Screen.Kyoto.route) {
            KyotoScreen(
                onBack                      = { navController.popBackStack() },
                onOpenCityRegions           = { navController.navigate(Screen.KyotoCityRegions.route) },
                onOpenParksAndAttractions   = { navController.navigate(Screen.KyotoParks.route) },
                onOpenGlutenFreeAndKeto     = { navController.navigate(Screen.KyotoGlutenFreeKeto.route) },
                onOpenGettingAround         = { navController.navigate(Screen.KyotoGettingAround.route) },
                onOpenDayPlans              = { navController.navigate(Screen.KyotoDayPlans.route) },
                onOpenMaps                  = { navController.navigate(Screen.KyotoMaps.route) },
                onOpenAmanohashidateDayTrip = { navController.navigate(Screen.KyotoAmanohashidateDayTrip.route) },
                onOpenWhereToSleep          = { navController.navigate(Screen.KyotoWhereToSleep.route) }
            )
        }
        composable(Screen.KyotoDayPlans.route) {
            KyotoDayPlansScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoMaps.route) {
            KyotoMapsScreen(
                onBack              = { navController.popBackStack() },
                onOpenSubwayMap     = { navController.navigate(Screen.KyotoSubwayMap.route) },
                onOpenCityMap       = { navController.navigate(Screen.KyotoCityMap.route) }
            )
        }
        composable(Screen.KyotoSubwayMap.route) {
            MapImageScreen(
                title = "Kyoto Subway Map",
                onBack = { navController.popBackStack() },
                imageRes = R.drawable.kyoto_subway_map,
                sourceCredit = "Source: Kyoto Metro Map, Wikimedia Commons, CC BY-SA 3.0"
            )
        }
        composable(Screen.KyotoCityMap.route) {
            MapImageScreen(
                title = "Kyoto City Map",
                onBack = { navController.popBackStack() },
                imageRes = R.drawable.kyoto_city_map,
                sourceCredit = "Source: Wards of Kyoto map, Wikimedia Commons, CC BY-SA 3.0"
            )
        }
        composable(Screen.KyotoCityRegions.route) {
            KyotoCityRegionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoGlutenFreeKeto.route) {
            KyotoGlutenFreeKetoScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoGettingAround.route) {
            KyotoGettingAroundScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoParks.route) {
            KyotoParksScreen(
                onBack                  = { navController.popBackStack() },
                onOpenNinjaMuseum       = { navController.navigate(Screen.NinjaMuseum.route) },
                onOpenNintendoMuseum    = { navController.navigate(Screen.KyotoNintendoMuseum.route) },
                onOpenOverview          = { navController.navigate(Screen.KyotoOverview.route) },
                onOpenTeamLabBiovortex  = { navController.navigate(Screen.KyotoTeamLabBiovortex.route) },
                onOpenToeiStudioPark    = { navController.navigate(Screen.KyotoToeiStudioPark.route) }
            )
        }
        composable(Screen.NinjaMuseum.route) {
            NinjaMuseumScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoNintendoMuseum.route) {
            NintendoMuseumScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoOverview.route) {
            KyotoOverviewScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoTeamLabBiovortex.route) {
            KyotoTeamLabBiovortexScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoToeiStudioPark.route) {
            KyotoToeiStudioParkScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoAmanohashidateDayTrip.route) {
            AmanohashidateDayTripScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoWhereToSleep.route) {
            KyotoWhereToSleepScreen(onBack = { navController.popBackStack() })
        }

        // Kawaguchiko
        composable(Screen.Kawaguchiko.route) {
            KawaguchikoScreen(
                onBack          = { navController.popBackStack() },
                onOpenFujiQ     = { navController.navigate(Screen.FujiQ.route) },
                onOpenOverview  = { navController.navigate(Screen.KawaguchikoOverview.route) }
            )
        }
        composable(Screen.KawaguchikoOverview.route) {
            KawaguchikoOverviewScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.FujiQ.route) {
            FujiQScreen(onBack = { navController.popBackStack() })
        }

        // Hakone
        composable(Screen.Hakone.route) {
            HakoneScreen(
                onBack              = { navController.popBackStack() },
                onOpenRopeway       = { navController.navigate(Screen.HakoneRopeway.route) },
                onOpenGettingThere  = { navController.navigate(Screen.HakoneGettingThere.route) }
            )
        }
        composable(Screen.HakoneGettingThere.route) {
            HakoneGettingThereScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.HakoneRopeway.route) {
            HakoneRopewayScreen(onBack = { navController.popBackStack() })
        }

        // Nara
        composable(Screen.Nara.route) {
            NaraScreen(
                onBack                  = { navController.popBackStack() },
                onOpenGettingThere      = { navController.navigate(Screen.NaraGettingThere.route) },
                onOpenTemplesAndDeer    = { navController.navigate(Screen.NaraTemplesAndDeer.route) }
            )
        }
        composable(Screen.NaraGettingThere.route) {
            NaraGettingThereScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.NaraTemplesAndDeer.route) {
            NaraTemplesAndDeerScreen(onBack = { navController.popBackStack() })
        }

        // Ghibli Park (top-level place, not nested under any city)
        composable(Screen.GhibliPark.route) {
            GhibliParkScreen(onBack = { navController.popBackStack() })
        }
    }
}
