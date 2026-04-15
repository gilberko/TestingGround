package com.example.developmentapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.developmentapp.screens.AssemblyHubScreen
import com.example.developmentapp.screens.ComingSoonScreen
import com.example.developmentapp.screens.DataStructuresScreen
import com.example.developmentapp.screens.HomeScreen
import com.example.developmentapp.screens.assembly.BasicArithmeticScreen
import com.example.developmentapp.screens.assembly.FunctionCallsScreen
import com.example.developmentapp.screens.assembly.JumpsScreen
import com.example.developmentapp.screens.assembly.X86EnvironmentScreen

sealed class Screen(val route: String) {
    object Home            : Screen("home")
    object ComingSoon      : Screen("coming_soon/{label}") {
        fun withLabel(label: String) = "coming_soon/$label"
    }
    object AssemblyHub     : Screen("assembly_hub")
    object DataStructures  : Screen("data_structures")
    object X86Environment  : Screen("x86_environment")
    object BasicArithmetic : Screen("basic_arithmetic")
    object Jumps           : Screen("jumps")
    object FunctionCalls   : Screen("function_calls")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(
                onCpp           = { navController.navigate(Screen.ComingSoon.withLabel("C/C++")) },
                onRust          = { navController.navigate(Screen.ComingSoon.withLabel("Rust")) },
                onGo            = { navController.navigate(Screen.ComingSoon.withLabel("Go")) },
                onPython        = { navController.navigate(Screen.ComingSoon.withLabel("Python")) },
                onAssembly      = { navController.navigate(Screen.AssemblyHub.route) },
                onDataStructures = { navController.navigate(Screen.DataStructures.route) },
                onAlgorithms    = { navController.navigate(Screen.ComingSoon.withLabel("Algorithms")) }
            )
        }

        composable(
            route = Screen.ComingSoon.route,
            arguments = listOf(navArgument("label") { type = NavType.StringType })
        ) {
            ComingSoonScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.AssemblyHub.route) {
            AssemblyHubScreen(
                onBack            = { navController.popBackStack() },
                onX86Environment  = { navController.navigate(Screen.X86Environment.route) },
                onBasicArithmetic = { navController.navigate(Screen.BasicArithmetic.route) },
                onJumps           = { navController.navigate(Screen.Jumps.route) },
                onFunctionCalls   = { navController.navigate(Screen.FunctionCalls.route) }
            )
        }

        composable(Screen.DataStructures.route) {
            DataStructuresScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.X86Environment.route) {
            X86EnvironmentScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.BasicArithmetic.route) {
            BasicArithmeticScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Jumps.route) {
            JumpsScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.FunctionCalls.route) {
            FunctionCallsScreen(onBack = { navController.popBackStack() })
        }
    }
}
