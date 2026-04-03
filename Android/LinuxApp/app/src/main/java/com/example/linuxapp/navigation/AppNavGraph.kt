package com.example.linuxapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.linuxapp.screens.HomeScreen
import com.example.linuxapp.screens.KernelHubScreen
import com.example.linuxapp.screens.LinuxUsageScreen
import com.example.linuxapp.screens.ShellScriptingScreen
import com.example.linuxapp.screens.UserModeHubScreen
import com.example.linuxapp.screens.kernel.CharDeviceScreen
import com.example.linuxapp.screens.kernel.DeferredWorkScreen
import com.example.linuxapp.screens.kernel.KernelDebuggingScreen
import com.example.linuxapp.screens.kernel.LoadableKernelModuleScreen
import com.example.linuxapp.screens.kernel.LowLevelPrinciplesScreen
import com.example.linuxapp.screens.kernel.LowLevelPrinciplesPart2Screen
import com.example.linuxapp.screens.kernel.OsStructsScreen
import com.example.linuxapp.screens.usermode.UserModeFilesScreen
import com.example.linuxapp.screens.usermode.UserModeNetworkingScreen
import com.example.linuxapp.screens.usermode.UserModeProcessesScreen
import com.example.linuxapp.screens.usermode.UserModeSyncScreen
import com.example.linuxapp.screens.usermode.UserModeThreadsScreen
import com.example.linuxapp.screens.usermode.UserModeIpcScreen
import com.example.linuxapp.screens.usermode.UserModeSignalsScreen
import com.example.linuxapp.screens.usermode.UserModeTunScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Placeholder : Screen("placeholder/{title}") {
        fun withTitle(t: String) = "placeholder/$t"
    }
    object KernelHub         : Screen("kernel_hub")
    object KernelLkm         : Screen("kernel_lkm")
    object KernelCharDevice  : Screen("kernel_char_device")
    object KernelLowLevel    : Screen("kernel_low_level")
    object KernelLowLevel2   : Screen("kernel_low_level_2")
    object KernelOsStructs   : Screen("kernel_os_structs")
    object KernelDebugging   : Screen("kernel_debugging")
    object LinuxUsage        : Screen("linux_usage")
    object UserModeHub       : Screen("user_mode_hub")
    object UserModeProcesses : Screen("user_mode_processes")
    object UserModeSync      : Screen("user_mode_sync")
    object UserModeNetworking: Screen("user_mode_networking")
    object UserModeThreads   : Screen("user_mode_threads")
    object UserModeFiles     : Screen("user_mode_files")
    object UserModeTun       : Screen("user_mode_tun")
    object KernelDeferredWork: Screen("kernel_deferred_work")
    object UserModeSignals   : Screen("user_mode_signals")
    object UserModeIpc       : Screen("user_mode_ipc")
    object ShellScripting    : Screen("shell_scripting")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(
                onLinuxUsage = { navController.navigate(Screen.LinuxUsage.route) },
                onShellScripting = { navController.navigate(Screen.ShellScripting.route) },
                onUserMode = { navController.navigate(Screen.UserModeHub.route) },
                onKernelMode = { navController.navigate(Screen.KernelHub.route) },
                onEbpf = { navController.navigate(Screen.Placeholder.withTitle("eBPF")) }
            )
        }

        composable(
            route = Screen.Placeholder.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStack ->
            val title = backStack.arguments?.getString("title") ?: ""
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "$title\n\nComing Soon", color = Color.White, textAlign = TextAlign.Center)
                }
            }
        }

        composable(Screen.KernelHub.route) {
            KernelHubScreen(
                onBack = { navController.popBackStack() },
                onLkm = { navController.navigate(Screen.KernelLkm.route) },
                onCharDevice = { navController.navigate(Screen.KernelCharDevice.route) },
                onBlockDevice = { navController.navigate(Screen.Placeholder.withTitle("Block Device")) },
                onNetDevice = { navController.navigate(Screen.Placeholder.withTitle("Net Device")) },
                onLowLevel = { navController.navigate(Screen.KernelLowLevel.route) },
                onOsStructs = { navController.navigate(Screen.KernelOsStructs.route) },
                onLowLevel2 = { navController.navigate(Screen.KernelLowLevel2.route) },
                onDebugging = { navController.navigate(Screen.KernelDebugging.route) },
                onDeferredWork = { navController.navigate(Screen.KernelDeferredWork.route) }
            )
        }

        composable(Screen.KernelLkm.route) {
            LoadableKernelModuleScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelCharDevice.route) {
            CharDeviceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelLowLevel.route) {
            LowLevelPrinciplesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelLowLevel2.route) {
            LowLevelPrinciplesPart2Screen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelOsStructs.route) {
            OsStructsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelDebugging.route) {
            KernelDebuggingScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.LinuxUsage.route) {
            LinuxUsageScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.UserModeHub.route) {
            UserModeHubScreen(
                onBack = { navController.popBackStack() },
                onProcesses = { navController.navigate(Screen.UserModeProcesses.route) },
                onSync = { navController.navigate(Screen.UserModeSync.route) },
                onNetworking = { navController.navigate(Screen.UserModeNetworking.route) },
                onThreads = { navController.navigate(Screen.UserModeThreads.route) },
                onFiles = { navController.navigate(Screen.UserModeFiles.route) },
                onTun = { navController.navigate(Screen.UserModeTun.route) },
                onSignals = { navController.navigate(Screen.UserModeSignals.route) },
                onIpc = { navController.navigate(Screen.UserModeIpc.route) }
            )
        }
        composable(Screen.UserModeSync.route) {
            UserModeSyncScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeNetworking.route) {
            UserModeNetworkingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeThreads.route) {
            UserModeThreadsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeFiles.route) {
            UserModeFilesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeProcesses.route) {
            UserModeProcessesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeTun.route) {
            UserModeTunScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelDeferredWork.route) {
            DeferredWorkScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeSignals.route) {
            UserModeSignalsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeIpc.route) {
            UserModeIpcScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ShellScripting.route) {
            ShellScriptingScreen(onBack = { navController.popBackStack() })
        }
    }
}
