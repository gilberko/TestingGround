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
import com.example.developmentapp.screens.PythonHubScreen
import com.example.developmentapp.screens.assembly.BasicArithmeticScreen
import com.example.developmentapp.screens.assembly.ComparisonsLoopsScreen
import com.example.developmentapp.screens.assembly.FunctionCallsScreen
import com.example.developmentapp.screens.assembly.JumpsScreen
import com.example.developmentapp.screens.assembly.MoreArithmeticScreen
import com.example.developmentapp.screens.assembly.MoveDataScreen
import com.example.developmentapp.screens.assembly.NumberRepresentationScreen
import com.example.developmentapp.screens.assembly.StackScreen
import com.example.developmentapp.screens.assembly.SyscallIntScreen
import com.example.developmentapp.screens.assembly.X86EnvironmentScreen
import com.example.developmentapp.screens.python.ClassesObjectsScreen
import com.example.developmentapp.screens.python.PythonArithmeticScreen
import com.example.developmentapp.screens.python.PythonConditionsScreen
import com.example.developmentapp.screens.python.PythonFunctionsScreen
import com.example.developmentapp.screens.python.PythonInputOutputScreen
import com.example.developmentapp.screens.python.PythonLibrariesScreen
import com.example.developmentapp.screens.python.PythonLoopsScreen
import com.example.developmentapp.screens.python.PythonStringsScreen
import com.example.developmentapp.screens.python.PythonSyntaxScreen
import com.example.developmentapp.screens.python.PythonVariablesScreen

sealed class Screen(val route: String) {
    object Home                : Screen("home")
    object ComingSoon          : Screen("coming_soon/{label}") {
        fun withLabel(label: String) = "coming_soon/$label"
    }
    // Assembly
    object AssemblyHub         : Screen("assembly_hub")
    object X86Environment      : Screen("x86_environment")
    object BasicArithmetic     : Screen("basic_arithmetic")
    object Jumps               : Screen("jumps")
    object FunctionCalls       : Screen("function_calls")
    object SyscallInt          : Screen("syscall_int")
    object MoveData            : Screen("move_data")
    object AsmStack            : Screen("asm_stack")
    object ComparisonsLoops    : Screen("comparisons_loops")
    object MoreArithmetic      : Screen("more_arithmetic")
    object NumberRepresentation: Screen("number_representation")
    // Data Structures
    object DataStructures      : Screen("data_structures")
    // Python
    object PythonHub           : Screen("python_hub")
    object PythonSyntax        : Screen("python_syntax")
    object PythonFunctions     : Screen("python_functions")
    object PythonVariables     : Screen("python_variables")
    object PythonClassesObjects: Screen("python_classes_objects")
    object PythonConditions    : Screen("python_conditions")
    object PythonLoops         : Screen("python_loops")
    object PythonArithmetic    : Screen("python_arithmetic")
    object PythonStrings       : Screen("python_strings")
    object PythonLibraries     : Screen("python_libraries")
    object PythonInputOutput   : Screen("python_input_output")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(
                onCpp            = { navController.navigate(Screen.ComingSoon.withLabel("C/C++")) },
                onRust           = { navController.navigate(Screen.ComingSoon.withLabel("Rust")) },
                onGo             = { navController.navigate(Screen.ComingSoon.withLabel("Go")) },
                onPython         = { navController.navigate(Screen.PythonHub.route) },
                onAssembly       = { navController.navigate(Screen.AssemblyHub.route) },
                onDataStructures = { navController.navigate(Screen.DataStructures.route) },
                onAlgorithms     = { navController.navigate(Screen.ComingSoon.withLabel("Algorithms")) }
            )
        }

        composable(
            route = Screen.ComingSoon.route,
            arguments = listOf(navArgument("label") { type = NavType.StringType })
        ) {
            ComingSoonScreen(onBack = { navController.popBackStack() })
        }

        // ── Assembly ──────────────────────────────────────────────────────
        composable(Screen.AssemblyHub.route) {
            AssemblyHubScreen(
                onBack               = { navController.popBackStack() },
                onX86Environment     = { navController.navigate(Screen.X86Environment.route) },
                onBasicArithmetic    = { navController.navigate(Screen.BasicArithmetic.route) },
                onJumps              = { navController.navigate(Screen.Jumps.route) },
                onFunctionCalls      = { navController.navigate(Screen.FunctionCalls.route) },
                onSyscallInt         = { navController.navigate(Screen.SyscallInt.route) },
                onMoveData           = { navController.navigate(Screen.MoveData.route) },
                onStack              = { navController.navigate(Screen.AsmStack.route) },
                onComparisonsLoops   = { navController.navigate(Screen.ComparisonsLoops.route) },
                onMoreArithmetic     = { navController.navigate(Screen.MoreArithmetic.route) },
                onNumberRepresentation = { navController.navigate(Screen.NumberRepresentation.route) }
            )
        }
        composable(Screen.X86Environment.route)       { X86EnvironmentScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.BasicArithmetic.route)      { BasicArithmeticScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Jumps.route)                { JumpsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.FunctionCalls.route)        { FunctionCallsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.SyscallInt.route)           { SyscallIntScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.MoveData.route)             { MoveDataScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AsmStack.route)             { StackScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.ComparisonsLoops.route)     { ComparisonsLoopsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.MoreArithmetic.route)       { MoreArithmeticScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.NumberRepresentation.route) { NumberRepresentationScreen(onBack = { navController.popBackStack() }) }

        // ── Data Structures ───────────────────────────────────────────────
        composable(Screen.DataStructures.route) {
            DataStructuresScreen(onBack = { navController.popBackStack() })
        }

        // ── Python ────────────────────────────────────────────────────────
        composable(Screen.PythonHub.route) {
            PythonHubScreen(
                onBack           = { navController.popBackStack() },
                onSyntax         = { navController.navigate(Screen.PythonSyntax.route) },
                onFunctions      = { navController.navigate(Screen.PythonFunctions.route) },
                onVariables      = { navController.navigate(Screen.PythonVariables.route) },
                onClassesObjects = { navController.navigate(Screen.PythonClassesObjects.route) },
                onConditions     = { navController.navigate(Screen.PythonConditions.route) },
                onLoops          = { navController.navigate(Screen.PythonLoops.route) },
                onArithmetic     = { navController.navigate(Screen.PythonArithmetic.route) },
                onStrings        = { navController.navigate(Screen.PythonStrings.route) },
                onLibraries      = { navController.navigate(Screen.PythonLibraries.route) },
                onInputOutput    = { navController.navigate(Screen.PythonInputOutput.route) }
            )
        }
        composable(Screen.PythonSyntax.route)         { PythonSyntaxScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonFunctions.route)      { PythonFunctionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonVariables.route)      { PythonVariablesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonClassesObjects.route) { ClassesObjectsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonConditions.route)    { PythonConditionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonLoops.route)         { PythonLoopsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonArithmetic.route)    { PythonArithmeticScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonStrings.route)       { PythonStringsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonLibraries.route)     { PythonLibrariesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonInputOutput.route)   { PythonInputOutputScreen(onBack = { navController.popBackStack() }) }
    }
}
