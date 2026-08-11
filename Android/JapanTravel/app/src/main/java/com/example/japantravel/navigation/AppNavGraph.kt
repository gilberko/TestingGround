package com.example.japantravel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.japantravel.screens.AboutFoodScreen
import com.example.japantravel.screens.ClassicalCultureScreen
import com.example.japantravel.screens.FoodAndCafesScreen
import com.example.japantravel.screens.FujiQScreen
import com.example.japantravel.screens.GeneralInformationScreen
import com.example.japantravel.screens.GhibliMuseumScreen
import com.example.japantravel.screens.GhibliParkScreen
import com.example.japantravel.screens.HakoneGettingThereScreen
import com.example.japantravel.screens.HakoneRopewayScreen
import com.example.japantravel.screens.HakoneScreen
import com.example.japantravel.screens.HomeScreen
import com.example.japantravel.screens.JoypolisScreen
import com.example.japantravel.screens.KawaguchikoOverviewScreen
import com.example.japantravel.screens.KawaguchikoScreen
import com.example.japantravel.screens.KyotoCityRegionsScreen
import com.example.japantravel.screens.KyotoOverviewScreen
import com.example.japantravel.screens.KyotoParksScreen
import com.example.japantravel.screens.KyotoScreen
import com.example.japantravel.screens.KyotoTeamLabBiovortexScreen
import com.example.japantravel.screens.MedicalIssuesScreen
import com.example.japantravel.screens.NaraScreen
import com.example.japantravel.screens.NaraTemplesAndDeerScreen
import com.example.japantravel.screens.NinjaMuseumScreen
import com.example.japantravel.screens.NintendoMuseumScreen
import com.example.japantravel.screens.OsakaOverviewScreen
import com.example.japantravel.screens.OsakaParksScreen
import com.example.japantravel.screens.OsakaScreen
import com.example.japantravel.screens.OsakaTeamLabBotanicalGardenScreen
import com.example.japantravel.screens.PlaceDetailScreen
import com.example.japantravel.screens.PlacesScreen
import com.example.japantravel.screens.SimAndEsimScreen
import com.example.japantravel.screens.SpecialAnimalPlacesScreen
import com.example.japantravel.screens.StoresAndChainsScreen
import com.example.japantravel.screens.TeamLabBorderlessScreen
import com.example.japantravel.screens.TeamLabPlanetsScreen
import com.example.japantravel.screens.TokyoCityRegionsScreen
import com.example.japantravel.screens.TokyoDisneyScreen
import com.example.japantravel.screens.TokyoDomeCityScreen
import com.example.japantravel.screens.TokyoOutsideAreaScreen
import com.example.japantravel.screens.TokyoParksScreen
import com.example.japantravel.screens.TokyoScreen
import com.example.japantravel.screens.TokyoSpecialCafesScreen
import com.example.japantravel.screens.TokyoSummerlandScreen
import com.example.japantravel.screens.UsefulAppsScreen
import com.example.japantravel.screens.UsjScreen
import com.example.japantravel.screens.YomiurilandScreen

sealed class Screen(val route: String) {
    object Home                    : Screen("home")
    object Places                  : Screen("places")
    object PlaceDetail             : Screen("place/{placeName}")
    object GeneralInfo             : Screen("general_info")
    object SimAndEsim              : Screen("sim_and_esim")
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

    object Osaka                   : Screen("osaka")
    object OsakaOverview           : Screen("osaka_overview")
    object OsakaParks              : Screen("osaka_parks")
    object Usj                     : Screen("usj")
    object OsakaTeamLabBotanicalGarden : Screen("osaka_teamlab_botanical_garden")

    object Kyoto                   : Screen("kyoto")
    object KyotoCityRegions        : Screen("kyoto_city_regions")
    object KyotoParks              : Screen("kyoto_parks")
    object NinjaMuseum             : Screen("ninja_museum")
    object KyotoNintendoMuseum     : Screen("kyoto_nintendo_museum")
    object KyotoOverview           : Screen("kyoto_overview")
    object KyotoTeamLabBiovortex   : Screen("kyoto_teamlab_biovortex")

    object Kawaguchiko             : Screen("kawaguchiko")
    object KawaguchikoOverview     : Screen("kawaguchiko_overview")
    object FujiQ                   : Screen("fuji_q")

    object Hakone                  : Screen("hakone")
    object HakoneGettingThere      : Screen("hakone_getting_there")
    object HakoneRopeway           : Screen("hakone_ropeway")

    object Nara                    : Screen("nara")
    object NaraTemplesAndDeer      : Screen("nara_temples_and_deer")

    object GhibliPark              : Screen("ghibli_park")

    object TokyoOutsideArea        : Screen("tokyo_outside_area")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenPlaces            = { navController.navigate(Screen.Places.route) },
                onOpenGeneralInfo       = { navController.navigate(Screen.GeneralInfo.route) },
                onOpenSimAndEsim        = { navController.navigate(Screen.SimAndEsim.route) },
                onOpenUsefulApps        = { navController.navigate(Screen.UsefulApps.route) },
                onOpenStoresAndChains   = { navController.navigate(Screen.StoresAndChains.route) },
                onOpenClassicalCulture  = { navController.navigate(Screen.ClassicalCulture.route) },
                onOpenMedicalIssues     = { navController.navigate(Screen.MedicalIssues.route) },
                onOpenFoodAndCafes          = { navController.navigate(Screen.FoodAndCafes.route) },
                onOpenSpecialAnimalPlaces   = { navController.navigate(Screen.SpecialAnimalPlaces.route) },
                onOpenAboutFood             = { navController.navigate(Screen.AboutFood.route) }
            )
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
        composable(Screen.SimAndEsim.route) {
            SimAndEsimScreen(onBack = { navController.popBackStack() })
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
                onOpenOutsideTokyo          = { navController.navigate(Screen.TokyoOutsideArea.route) }
            )
        }
        composable(Screen.TokyoOutsideArea.route) {
            TokyoOutsideAreaScreen(onBack = { navController.popBackStack() })
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
                onOpenSummerland            = { navController.navigate(Screen.TokyoSummerland.route) }
            )
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
        composable(Screen.TokyoCityRegions.route) {
            TokyoCityRegionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TokyoSpecialCafes.route) {
            TokyoSpecialCafesScreen(onBack = { navController.popBackStack() })
        }

        // Osaka
        composable(Screen.Osaka.route) {
            OsakaScreen(
                onBack                      = { navController.popBackStack() },
                onOpenParksAndAttractions   = { navController.navigate(Screen.OsakaParks.route) },
                onOpenOverview              = { navController.navigate(Screen.OsakaOverview.route) },
                onOpenTeamLabBotanicalGarden = { navController.navigate(Screen.OsakaTeamLabBotanicalGarden.route) }
            )
        }
        composable(Screen.OsakaOverview.route) {
            OsakaOverviewScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaParks.route) {
            OsakaParksScreen(
                onBack      = { navController.popBackStack() },
                onOpenUsj   = { navController.navigate(Screen.Usj.route) }
            )
        }
        composable(Screen.Usj.route) {
            UsjScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.OsakaTeamLabBotanicalGarden.route) {
            OsakaTeamLabBotanicalGardenScreen(onBack = { navController.popBackStack() })
        }

        // Kyoto
        composable(Screen.Kyoto.route) {
            KyotoScreen(
                onBack                      = { navController.popBackStack() },
                onOpenCityRegions           = { navController.navigate(Screen.KyotoCityRegions.route) },
                onOpenParksAndAttractions   = { navController.navigate(Screen.KyotoParks.route) },
                onOpenTeamLabBiovortex      = { navController.navigate(Screen.KyotoTeamLabBiovortex.route) }
            )
        }
        composable(Screen.KyotoCityRegions.route) {
            KyotoCityRegionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KyotoParks.route) {
            KyotoParksScreen(
                onBack                  = { navController.popBackStack() },
                onOpenNinjaMuseum       = { navController.navigate(Screen.NinjaMuseum.route) },
                onOpenNintendoMuseum    = { navController.navigate(Screen.KyotoNintendoMuseum.route) },
                onOpenOverview          = { navController.navigate(Screen.KyotoOverview.route) }
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
                onOpenTemplesAndDeer    = { navController.navigate(Screen.NaraTemplesAndDeer.route) }
            )
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
