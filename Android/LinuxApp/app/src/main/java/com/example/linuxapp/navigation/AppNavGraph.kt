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
import com.example.linuxapp.screens.EbpfHubScreen
import com.example.linuxapp.screens.HomeScreen
import com.example.linuxapp.screens.SplashScreen
import com.example.linuxapp.screens.KernelHubScreen
import com.example.linuxapp.screens.LinuxHistoryScreen
import com.example.linuxapp.screens.LinuxUsageScreen
import com.example.linuxapp.screens.ShellScriptingScreen
import com.example.linuxapp.screens.UserModeHubScreen
import com.example.linuxapp.screens.ebpf.EbpfHistoryScreen
import com.example.linuxapp.screens.ebpf.EbpfProgramTypesScreen
import com.example.linuxapp.screens.ebpf.WhatIsEbpfScreen
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
import com.example.linuxapp.screens.kernel.KernelDataStructuresScreen
import com.example.linuxapp.screens.kernel.KernelVfsScreen
import com.example.linuxapp.screens.kernel.BlockDeviceScreen
import com.example.linuxapp.screens.kernel.NetDeviceScreen
import com.example.linuxapp.screens.usermode.UserModeDebuggingScreen
import com.example.linuxapp.screens.usermode.UserModeSharedObjectsScreen
import com.example.linuxapp.screens.usermode.UserModeAsyncScreen
import com.example.linuxapp.screens.kernel.FileAccessWholeScreen
import com.example.linuxapp.screens.kernel.MemoryAccessWholeScreen
import com.example.linuxapp.screens.kernel.LsmScreen
import com.example.linuxapp.screens.PermissionsHubScreen
import com.example.linuxapp.screens.permissions.FilePermissionsScreen
import com.example.linuxapp.screens.permissions.CgroupsScreen
import com.example.linuxapp.screens.permissions.BootProcessScreen
import com.example.linuxapp.screens.permissions.AffinityScreen
import com.example.linuxapp.screens.permissions.ProcessSchedulingScreen
import com.example.linuxapp.screens.usermode.UserModeGraphicalScreen
import com.example.linuxapp.screens.usermode.UserModeInlineAssemblyScreen
import com.example.linuxapp.screens.permissions.CallingConventionsScreen
import com.example.linuxapp.screens.kernel.KernelPendOperationsScreen
import com.example.linuxapp.screens.ebpf.EbpfSimpleExampleScreen
import com.example.linuxapp.screens.ebpf.BtfScreen
import com.example.linuxapp.screens.ebpf.EbpfAdvancedScreen
import com.example.linuxapp.screens.ebpf.EbpfSecurityScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
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
    object ShellScripting         : Screen("shell_scripting")
    object KernelDataStructures   : Screen("kernel_data_structures")
    object KernelVfs              : Screen("kernel_vfs")
    object KernelBlockDevice      : Screen("kernel_block_device")
    object KernelNetDevice        : Screen("kernel_net_device")
    object UserModeDebugging      : Screen("user_mode_debugging")
    object UserModeSharedObjects  : Screen("user_mode_shared_objects")
    object LinuxHistory           : Screen("linux_history")
    object EbpfHub                : Screen("ebpf_hub")
    object EbpfHistory            : Screen("ebpf_history")
    object EbpfWhatIs             : Screen("ebpf_what_is")
    object EbpfProgramTypes       : Screen("ebpf_program_types")
    object UserModeAsync          : Screen("user_mode_async")
    object KernelFileAccessWhole  : Screen("kernel_file_access_whole")
    object KernelMemoryAccessWhole: Screen("kernel_memory_access_whole")
    object KernelLsm              : Screen("kernel_lsm")
    object PermissionsHub         : Screen("permissions_hub")
    object FilePermissions        : Screen("file_permissions")
    object Cgroups                : Screen("cgroups")
    object EbpfSimpleExample      : Screen("ebpf_simple_example")
    object EbpfBtf                : Screen("ebpf_btf")
    object EbpfAdvanced           : Screen("ebpf_advanced")
    object EbpfSecurity           : Screen("ebpf_security")
    object AdvancedBootProcess       : Screen("advanced_boot_process")
    object UserModeGraphical         : Screen("user_mode_graphical")
    object AdvancedAffinity          : Screen("advanced_affinity")
    object AdvancedProcessScheduling : Screen("advanced_process_scheduling")
    object UserModeInlineAssembly    : Screen("user_mode_inline_assembly")
    object CallingConventions        : Screen("calling_conventions")
    object KernelPendOperations      : Screen("kernel_pend_operations")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(onFinished = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onLinuxUsage = { navController.navigate(Screen.LinuxUsage.route) },
                onShellScripting = { navController.navigate(Screen.ShellScripting.route) },
                onLinuxHistory = { navController.navigate(Screen.LinuxHistory.route) },
                onUserMode = { navController.navigate(Screen.UserModeHub.route) },
                onKernelMode = { navController.navigate(Screen.KernelHub.route) },
                onEbpf = { navController.navigate(Screen.EbpfHub.route) },
                onPermissions = { navController.navigate(Screen.PermissionsHub.route) }
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
                onBlockDevice = { navController.navigate(Screen.KernelBlockDevice.route) },
                onNetDevice = { navController.navigate(Screen.KernelNetDevice.route) },
                onLowLevel = { navController.navigate(Screen.KernelLowLevel.route) },
                onOsStructs = { navController.navigate(Screen.KernelOsStructs.route) },
                onLowLevel2 = { navController.navigate(Screen.KernelLowLevel2.route) },
                onDebugging = { navController.navigate(Screen.KernelDebugging.route) },
                onDeferredWork = { navController.navigate(Screen.KernelDeferredWork.route) },
                onDataStructures = { navController.navigate(Screen.KernelDataStructures.route) },
                onVfs = { navController.navigate(Screen.KernelVfs.route) },
                onFileAccessWhole = { navController.navigate(Screen.KernelFileAccessWhole.route) },
                onMemoryAccessWhole = { navController.navigate(Screen.KernelMemoryAccessWhole.route) },
                onPendOperations = { navController.navigate(Screen.KernelPendOperations.route) }
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
                onSignals = { navController.navigate(Screen.UserModeSignals.route) },
                onIpc = { navController.navigate(Screen.UserModeIpc.route) },
                onDebugging = { navController.navigate(Screen.UserModeDebugging.route) },
                onSharedObjects = { navController.navigate(Screen.UserModeSharedObjects.route) },
                onAsyncOperations = { navController.navigate(Screen.UserModeAsync.route) },
                onGraphicalInterface = { navController.navigate(Screen.UserModeGraphical.route) },
                onInlineAssembly = { navController.navigate(Screen.UserModeInlineAssembly.route) }
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
        composable(Screen.KernelDataStructures.route) {
            KernelDataStructuresScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelVfs.route) {
            KernelVfsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelBlockDevice.route) {
            BlockDeviceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelNetDevice.route) {
            NetDeviceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeDebugging.route) {
            UserModeDebuggingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeSharedObjects.route) {
            UserModeSharedObjectsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.LinuxHistory.route) {
            LinuxHistoryScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfHub.route) {
            EbpfHubScreen(
                onBack = { navController.popBackStack() },
                onEbpfHistory = { navController.navigate(Screen.EbpfHistory.route) },
                onWhatIsEbpf = { navController.navigate(Screen.EbpfWhatIs.route) },
                onEbpfProgramTypes = { navController.navigate(Screen.EbpfProgramTypes.route) },
                onEbpfSimpleExample = { navController.navigate(Screen.EbpfSimpleExample.route) },
                onBtf = { navController.navigate(Screen.EbpfBtf.route) },
                onEbpfAdvanced = { navController.navigate(Screen.EbpfAdvanced.route) },
                onEbpfSecurity = { navController.navigate(Screen.EbpfSecurity.route) }
            )
        }
        composable(Screen.EbpfHistory.route) {
            EbpfHistoryScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfWhatIs.route) {
            WhatIsEbpfScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfProgramTypes.route) {
            EbpfProgramTypesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeAsync.route) {
            UserModeAsyncScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelFileAccessWhole.route) {
            FileAccessWholeScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelMemoryAccessWhole.route) {
            MemoryAccessWholeScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelLsm.route) {
            LsmScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.PermissionsHub.route) {
            PermissionsHubScreen(
                onBack = { navController.popBackStack() },
                onFilePermissions = { navController.navigate(Screen.FilePermissions.route) },
                onCgroups = { navController.navigate(Screen.Cgroups.route) },
                onTun = { navController.navigate(Screen.UserModeTun.route) },
                onBootProcess = { navController.navigate(Screen.AdvancedBootProcess.route) },
                onAffinity = { navController.navigate(Screen.AdvancedAffinity.route) },
                onProcessScheduling = { navController.navigate(Screen.AdvancedProcessScheduling.route) },
                onCallingConventions = { navController.navigate(Screen.CallingConventions.route) },
                onLsm = { navController.navigate(Screen.KernelLsm.route) }
            )
        }
        composable(Screen.FilePermissions.route) {
            FilePermissionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Cgroups.route) {
            CgroupsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfSimpleExample.route) {
            EbpfSimpleExampleScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfBtf.route) {
            BtfScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfAdvanced.route) {
            EbpfAdvancedScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfSecurity.route) {
            EbpfSecurityScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedBootProcess.route) {
            BootProcessScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeGraphical.route) {
            UserModeGraphicalScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedAffinity.route) {
            AffinityScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedProcessScheduling.route) {
            ProcessSchedulingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeInlineAssembly.route) {
            UserModeInlineAssemblyScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.CallingConventions.route) {
            CallingConventionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelPendOperations.route) {
            KernelPendOperationsScreen(onBack = { navController.popBackStack() })
        }
    }
}
