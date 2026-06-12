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
import com.example.linuxapp.screens.usermode.UserModeFanotifyScreen
import com.example.linuxapp.screens.permissions.CallingConventionsScreen
import com.example.linuxapp.screens.permissions.MakefileCMakeScreen
import com.example.linuxapp.screens.permissions.IptablesNetfilterScreen
import com.example.linuxapp.screens.permissions.DeviceTreesScreen
import com.example.linuxapp.screens.permissions.PlugAndPlayScreen
import com.example.linuxapp.screens.permissions.StackFramesScreen
import com.example.linuxapp.screens.permissions.KernelVmDebuggingScreen
import com.example.linuxapp.screens.ebpf.EbpfSharingDataScreen
import com.example.linuxapp.screens.kernel.KernelPendOperationsScreen
import com.example.linuxapp.screens.kernel.KernelMemoryScreen
import com.example.linuxapp.screens.TheWholePictureHubScreen
import com.example.linuxapp.screens.ebpf.EbpfSimpleExampleScreen
import com.example.linuxapp.screens.ebpf.BtfScreen
import com.example.linuxapp.screens.ebpf.EbpfAdvancedScreen
import com.example.linuxapp.screens.ebpf.EbpfSecurityScreen
import com.example.linuxapp.screens.ebpf.EbpfKptrsScreen
import com.example.linuxapp.screens.ebpf.EbpfHelpersKfuncsScreen
import com.example.linuxapp.screens.ebpf.EbpfSleepableScreen
import com.example.linuxapp.screens.LinuxUsage2Screen
import com.example.linuxapp.screens.kernel.DeviceTypesHubScreen
import com.example.linuxapp.screens.kernel.KernelMemoryAccessScreen
import com.example.linuxapp.screens.kernel.KernelThreadingScreen
import com.example.linuxapp.screens.kernel.KernelProcessSchedulingScreen
import com.example.linuxapp.screens.kernel.ForkCloneScreen
import com.example.linuxapp.screens.kernel.DkmsScreen
import com.example.linuxapp.screens.NamespacesHubScreen
import com.example.linuxapp.screens.permissions.namespaces.AboutNamespacesScreen
import com.example.linuxapp.screens.permissions.namespaces.PidNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.NetNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.MountNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.UtsNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.IpcNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.UserNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.TimeNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.CgroupNamespaceScreen
import com.example.linuxapp.screens.permissions.namespaces.AccessingNamespacesScreen
import com.example.linuxapp.screens.permissions.ProcessStartScreen
import com.example.linuxapp.screens.kernel.WritingKernelModulesHubScreen
import com.example.linuxapp.screens.kernel.SysCallsScreen
import com.example.linuxapp.screens.kernel.KallsymsScreen
import com.example.linuxapp.screens.kernel.KernelAddingFilesScreen
import com.example.linuxapp.screens.kernel.SecurityFeaturesScreen
import com.example.linuxapp.screens.kernel.NetlinkScreen
import com.example.linuxapp.screens.usermode.UserModeCommunicatingWithKernelScreen
import com.example.linuxapp.screens.kernel.KernelNetworkingScreen
import com.example.linuxapp.screens.kernel.KernelFileAccessScreen
import com.example.linuxapp.screens.kernel.LinuxDeviceModelScreen
import com.example.linuxapp.screens.kernel.ExportingKernelModuleApiScreen
import com.example.linuxapp.screens.kernel.AlternativeModuleCommScreen
import com.example.linuxapp.screens.kernel.NotifierChainsScreen
import com.example.linuxapp.screens.kernel.ErrorInjectionScreen
import com.example.linuxapp.screens.kernel.KprobesScreen
import com.example.linuxapp.screens.ebpf.EbpfModifyReturnScreen
import com.example.linuxapp.screens.permissions.ElfFileFormatScreen
import com.example.linuxapp.screens.permissions.IoUringScreen
import com.example.linuxapp.screens.permissions.DaemonsScreen
import com.example.linuxapp.screens.permissions.NatVpnScreen
import com.example.linuxapp.screens.ebpf.AboutEbpfHubScreen
import com.example.linuxapp.screens.ebpf.WritingEbpfHubScreen
import com.example.linuxapp.screens.ebpf.EbpfBasicCSyntaxScreen
import com.example.linuxapp.screens.ebpf.EbpfCompilingScreen
import com.example.linuxapp.screens.ebpf.EbpfPythonBccScreen
import com.example.linuxapp.screens.ebpf.BpftraceScreen
import com.example.linuxapp.screens.ebpf.BpftraceScriptSyntaxScreen
import com.example.linuxapp.screens.ebpf.EbpfPersistencyScreen
import com.example.linuxapp.screens.ebpf.EbpfLsmScreen
import com.example.linuxapp.screens.ebpf.EbpfSampleProgramsScreen
import com.example.linuxapp.screens.ebpf.EbpfManagingScreen
import com.example.linuxapp.screens.ebpf.AboutCiliumScreen
import com.example.linuxapp.screens.LicensingScreen
import com.example.linuxapp.screens.usermode.UserModeMemAllocScreen
import com.example.linuxapp.screens.usermode.UserModeMemAlloc2Screen
import com.example.linuxapp.screens.usermode.UserModeGlibcScreen
import com.example.linuxapp.screens.LinuxIdesScreen
import com.example.linuxapp.screens.LinuxPackageManagersScreen

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
    object UserModeFanotify          : Screen("user_mode_fanotify")
    object CallingConventions        : Screen("calling_conventions")
    object KernelPendOperations      : Screen("kernel_pend_operations")
    object TheWholePictureHub        : Screen("the_whole_picture_hub")
    object KernelMemoryManagement    : Screen("kernel_memory_management")
    object AdvancedMakefileCmake      : Screen("advanced_makefile_cmake")
    object AdvancedIptablesNetfilter  : Screen("advanced_iptables_netfilter")
    object AdvancedDeviceTrees       : Screen("advanced_device_trees")
    object AdvancedPlugAndPlay       : Screen("advanced_plug_and_play")
    object AdvancedStackFrames       : Screen("advanced_stack_frames")
    object AdvancedKernelVmDebugging : Screen("advanced_kernel_vm_debugging")
    object EbpfSharingData           : Screen("ebpf_sharing_data")
    object EbpfKptrs                 : Screen("ebpf_kptrs")
    object EbpfHelpersKfuncs         : Screen("ebpf_helpers_kfuncs")
    object DeviceTypesHub            : Screen("device_types_hub")
    object KernelMemoryAccess        : Screen("kernel_memory_access")
    object KernelThreading           : Screen("kernel_threading")
    object LinuxUsage2               : Screen("linux_usage_2")
    object KernelForkClone                 : Screen("kernel_fork_clone")
    object KernelDkms                      : Screen("kernel_dkms")
    object KernelProcessSchedulingWhole    : Screen("kernel_process_scheduling_whole")
    object EbpfSleepable                  : Screen("ebpf_sleepable")
    object NamespacesHub          : Screen("namespaces_hub")
    object AboutNamespaces        : Screen("about_namespaces")
    object PidNamespace           : Screen("pid_namespace")
    object NetNamespace           : Screen("net_namespace")
    object MountNamespace         : Screen("mount_namespace")
    object UtsNamespace           : Screen("uts_namespace")
    object IpcNamespace           : Screen("ipc_namespace")
    object UserNamespace          : Screen("user_namespace")
    object TimeNamespace          : Screen("time_namespace")
    object CgroupNamespace        : Screen("cgroup_namespace")
    object AccessingNamespaces    : Screen("accessing_namespaces")
    object AdvancedProcessStart       : Screen("advanced_process_start")
    object WritingKernelModulesHub    : Screen("writing_kernel_modules_hub")
    object SysCalls                   : Screen("kernel_sys_calls")
    object Kallsyms                   : Screen("kernel_kallsyms")
    object KernelAddingFiles          : Screen("kernel_adding_files")
    object KernelSecurityFeatures     : Screen("kernel_security_features")
    object KernelNetlink               : Screen("kernel_netlink")
    object UsermodeCommunicatingWithKernel : Screen("usermode_communicating_with_kernel")
    object KernelNetworking            : Screen("kernel_networking")
    object KernelFileAccess            : Screen("kernel_file_access")
    object LinuxDeviceModel            : Screen("linux_device_model")
    object ExportingKernelModuleApi    : Screen("exporting_kernel_module_api")
    object AlternativeModuleComm       : Screen("alternative_module_comm")
    object NotifierChains              : Screen("notifier_chains")
    object KernelErrorInjection        : Screen("kernel_error_injection")
    object KernelKprobes               : Screen("kernel_kprobes")
    object EbpfModifyReturn            : Screen("ebpf_modify_return")
    object AdvancedElfFileFormat       : Screen("advanced_elf_file_format")
    object AdvancedIoUring             : Screen("advanced_io_uring")
    object AdvancedDaemons             : Screen("advanced_daemons")
    object AdvancedNatVpn              : Screen("advanced_nat_vpn")
    object EbpfBpftrace                : Screen("ebpf_bpftrace")
    object EbpfPersistency             : Screen("ebpf_persistency")
    object EbpfLsm                     : Screen("ebpf_lsm")
    object EbpfSamplePrograms          : Screen("ebpf_sample_programs")
    object EbpfManaging                : Screen("ebpf_managing")
    object EbpfAboutCilium             : Screen("ebpf_about_cilium")
    object EbpfBpftraceScriptSyntax    : Screen("ebpf_bpftrace_script_syntax")
    object AboutEbpfHub                : Screen("about_ebpf_hub")
    object WritingEbpfHub              : Screen("writing_ebpf_hub")
    object EbpfBasicCSyntax            : Screen("ebpf_basic_c_syntax")
    object EbpfCompiling               : Screen("ebpf_compiling")
    object EbpfPythonBcc               : Screen("ebpf_python_bcc")
    object Licensing                   : Screen("licensing")
    object UserModeMemAlloc            : Screen("user_mode_mem_alloc")
    object UserModeMemAlloc2           : Screen("user_mode_mem_alloc_2")
    object UserModeGlibc               : Screen("user_mode_glibc")
    object LinuxIdes                   : Screen("linux_ides")
    object LinuxPackageManagers        : Screen("linux_package_managers")
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
                onLinuxUsage2 = { navController.navigate(Screen.LinuxUsage2.route) },
                onShellScripting = { navController.navigate(Screen.ShellScripting.route) },
                onLinuxHistory = { navController.navigate(Screen.LinuxHistory.route) },
                onIdes = { navController.navigate(Screen.LinuxIdes.route) },
                onPackageManagers = { navController.navigate(Screen.LinuxPackageManagers.route) },
                onUserMode = { navController.navigate(Screen.UserModeHub.route) },
                onKernelMode = { navController.navigate(Screen.KernelHub.route) },
                onEbpf = { navController.navigate(Screen.EbpfHub.route) },
                onPermissions = { navController.navigate(Screen.PermissionsHub.route) },
                onLicensing = { navController.navigate(Screen.Licensing.route) }
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
                onWritingKernelModules = { navController.navigate(Screen.WritingKernelModulesHub.route) },
                onLowLevel = { navController.navigate(Screen.KernelLowLevel.route) },
                onOsStructs = { navController.navigate(Screen.KernelOsStructs.route) },
                onLowLevel2 = { navController.navigate(Screen.KernelLowLevel2.route) },
                onDebugging = { navController.navigate(Screen.KernelDebugging.route) },
                onDataStructures = { navController.navigate(Screen.KernelDataStructures.route) },
                onVfs = { navController.navigate(Screen.KernelVfs.route) },
                onTheWholePicture = { navController.navigate(Screen.TheWholePictureHub.route) },
                onMemoryManagement = { navController.navigate(Screen.KernelMemoryManagement.route) },
                onForkClone = { navController.navigate(Screen.KernelForkClone.route) },
                onDkms = { navController.navigate(Screen.KernelDkms.route) },
                onSysCalls = { navController.navigate(Screen.SysCalls.route) },
                onKallsyms = { navController.navigate(Screen.Kallsyms.route) },
                onAddingFiles = { navController.navigate(Screen.KernelAddingFiles.route) },
                onSecurityFeatures = { navController.navigate(Screen.KernelSecurityFeatures.route) }
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
                onInlineAssembly = { navController.navigate(Screen.UserModeInlineAssembly.route) },
                onFanotify = { navController.navigate(Screen.UserModeFanotify.route) },
                onCommunicatingWithKernel = { navController.navigate(Screen.UsermodeCommunicatingWithKernel.route) },
                onMemAlloc = { navController.navigate(Screen.UserModeMemAlloc.route) },
                onMemAlloc2 = { navController.navigate(Screen.UserModeMemAlloc2.route) },
                onGlibc = { navController.navigate(Screen.UserModeGlibc.route) }
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
        composable(Screen.LinuxIdes.route) {
            LinuxIdesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.LinuxPackageManagers.route) {
            LinuxPackageManagersScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfHub.route) {
            EbpfHubScreen(
                onBack = { navController.popBackStack() },
                onAboutEbpf = { navController.navigate(Screen.AboutEbpfHub.route) },
                onEbpfProgramTypes = { navController.navigate(Screen.EbpfProgramTypes.route) },
                onBtf = { navController.navigate(Screen.EbpfBtf.route) },
                onEbpfSecurity = { navController.navigate(Screen.EbpfSecurity.route) },
                onEbpfHelpersKfuncs = { navController.navigate(Screen.EbpfHelpersKfuncs.route) },
                onEbpfSleepable = { navController.navigate(Screen.EbpfSleepable.route) },
                onAboutCilium = { navController.navigate(Screen.EbpfAboutCilium.route) },
                onEbpfPersistency = { navController.navigate(Screen.EbpfPersistency.route) },
                onEbpfLsm = { navController.navigate(Screen.EbpfLsm.route) },
                onEbpfManaging = { navController.navigate(Screen.EbpfManaging.route) },
                onWritingEbpf = { navController.navigate(Screen.WritingEbpfHub.route) }
            )
        }
        composable(Screen.AboutEbpfHub.route) {
            AboutEbpfHubScreen(
                onBack = { navController.popBackStack() },
                onEbpfHistory = { navController.navigate(Screen.EbpfHistory.route) },
                onWhatIsEbpf = { navController.navigate(Screen.EbpfWhatIs.route) }
            )
        }
        composable(Screen.WritingEbpfHub.route) {
            WritingEbpfHubScreen(
                onBack = { navController.popBackStack() },
                onEbpfBasicCSyntax = { navController.navigate(Screen.EbpfBasicCSyntax.route) },
                onEbpfCompiling = { navController.navigate(Screen.EbpfCompiling.route) },
                onEbpfPythonBcc = { navController.navigate(Screen.EbpfPythonBcc.route) },
                onEbpfSimpleExample = { navController.navigate(Screen.EbpfSimpleExample.route) },
                onEbpfAdvanced = { navController.navigate(Screen.EbpfAdvanced.route) },
                onEbpfSharingData = { navController.navigate(Screen.EbpfSharingData.route) },
                onEbpfKptrs = { navController.navigate(Screen.EbpfKptrs.route) },
                onEbpfModifyReturn = { navController.navigate(Screen.EbpfModifyReturn.route) },
                onEbpfSamplePrograms = { navController.navigate(Screen.EbpfSamplePrograms.route) },
                onBpftrace = { navController.navigate(Screen.EbpfBpftrace.route) },
                onBpftraceScriptSyntax = { navController.navigate(Screen.EbpfBpftraceScriptSyntax.route) }
            )
        }
        composable(Screen.EbpfBasicCSyntax.route) {
            EbpfBasicCSyntaxScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfCompiling.route) {
            EbpfCompilingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfPythonBcc.route) {
            EbpfPythonBccScreen(onBack = { navController.popBackStack() })
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
                onLsm = { navController.navigate(Screen.KernelLsm.route) },
                onMakefileCmake = { navController.navigate(Screen.AdvancedMakefileCmake.route) },
                onIptablesNetfilter = { navController.navigate(Screen.AdvancedIptablesNetfilter.route) },
                onDeviceTrees = { navController.navigate(Screen.AdvancedDeviceTrees.route) },
                onPlugAndPlay = { navController.navigate(Screen.AdvancedPlugAndPlay.route) },
                onStackFrames = { navController.navigate(Screen.AdvancedStackFrames.route) },
                onKernelVmDebugging = { navController.navigate(Screen.AdvancedKernelVmDebugging.route) },
                onNamespaces = { navController.navigate(Screen.NamespacesHub.route) },
                onProcessStart = { navController.navigate(Screen.AdvancedProcessStart.route) },
                onElfFileFormat = { navController.navigate(Screen.AdvancedElfFileFormat.route) },
                onIoUring = { navController.navigate(Screen.AdvancedIoUring.route) },
                onDaemons = { navController.navigate(Screen.AdvancedDaemons.route) },
                onNatVpn = { navController.navigate(Screen.AdvancedNatVpn.route) }
            )
        }
        composable(Screen.NamespacesHub.route) {
            NamespacesHubScreen(
                onBack = { navController.popBackStack() },
                onAboutNamespaces = { navController.navigate(Screen.AboutNamespaces.route) },
                onPidNamespace = { navController.navigate(Screen.PidNamespace.route) },
                onNetNamespace = { navController.navigate(Screen.NetNamespace.route) },
                onMountNamespace = { navController.navigate(Screen.MountNamespace.route) },
                onUtsNamespace = { navController.navigate(Screen.UtsNamespace.route) },
                onIpcNamespace = { navController.navigate(Screen.IpcNamespace.route) },
                onUserNamespace = { navController.navigate(Screen.UserNamespace.route) },
                onTimeNamespace = { navController.navigate(Screen.TimeNamespace.route) },
                onCgroupNamespace = { navController.navigate(Screen.CgroupNamespace.route) },
                onAccessingNamespaces = { navController.navigate(Screen.AccessingNamespaces.route) }
            )
        }
        composable(Screen.AboutNamespaces.route) {
            AboutNamespacesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.PidNamespace.route) {
            PidNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.NetNamespace.route) {
            NetNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.MountNamespace.route) {
            MountNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UtsNamespace.route) {
            UtsNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.IpcNamespace.route) {
            IpcNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserNamespace.route) {
            UserNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TimeNamespace.route) {
            TimeNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.CgroupNamespace.route) {
            CgroupNamespaceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AccessingNamespaces.route) {
            AccessingNamespacesScreen(onBack = { navController.popBackStack() })
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
        composable(Screen.UserModeFanotify.route) {
            UserModeFanotifyScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeMemAlloc.route) {
            UserModeMemAllocScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeMemAlloc2.route) {
            UserModeMemAlloc2Screen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UserModeGlibc.route) {
            UserModeGlibcScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.CallingConventions.route) {
            CallingConventionsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelPendOperations.route) {
            KernelPendOperationsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.TheWholePictureHub.route) {
            TheWholePictureHubScreen(
                onBack = { navController.popBackStack() },
                onFileAccessWhole = { navController.navigate(Screen.KernelFileAccessWhole.route) },
                onMemoryAccessWhole = { navController.navigate(Screen.KernelMemoryAccessWhole.route) },
                onProcessScheduling = { navController.navigate(Screen.KernelProcessSchedulingWhole.route) }
            )
        }
        composable(Screen.KernelProcessSchedulingWhole.route) {
            KernelProcessSchedulingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelMemoryManagement.route) {
            KernelMemoryScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedMakefileCmake.route) {
            MakefileCMakeScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedIptablesNetfilter.route) {
            IptablesNetfilterScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedDeviceTrees.route) {
            DeviceTreesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedPlugAndPlay.route) {
            PlugAndPlayScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedStackFrames.route) {
            StackFramesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedKernelVmDebugging.route) {
            KernelVmDebuggingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedProcessStart.route) {
            ProcessStartScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedElfFileFormat.route) {
            ElfFileFormatScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedIoUring.route) {
            IoUringScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedDaemons.route) {
            DaemonsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AdvancedNatVpn.route) {
            NatVpnScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfSharingData.route) {
            EbpfSharingDataScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfKptrs.route) {
            EbpfKptrsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfHelpersKfuncs.route) {
            EbpfHelpersKfuncsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfSleepable.route) {
            EbpfSleepableScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.DeviceTypesHub.route) {
            DeviceTypesHubScreen(
                onBack = { navController.popBackStack() },
                onCharDevice = { navController.navigate(Screen.KernelCharDevice.route) },
                onBlockDevice = { navController.navigate(Screen.KernelBlockDevice.route) },
                onNetDevice = { navController.navigate(Screen.KernelNetDevice.route) }
            )
        }
        composable(Screen.KernelMemoryAccess.route) {
            KernelMemoryAccessScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelThreading.route) {
            KernelThreadingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.LinuxUsage2.route) {
            LinuxUsage2Screen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelForkClone.route) {
            ForkCloneScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelDkms.route) {
            DkmsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.WritingKernelModulesHub.route) {
            WritingKernelModulesHubScreen(
                onBack               = { navController.popBackStack() },
                onLkm                = { navController.navigate(Screen.KernelLkm.route) },
                onDeviceTypes        = { navController.navigate(Screen.DeviceTypesHub.route) },
                onKernelMemoryAccess = { navController.navigate(Screen.KernelMemoryAccess.route) },
                onKernelThreading    = { navController.navigate(Screen.KernelThreading.route) },
                onPendOperations     = { navController.navigate(Screen.KernelPendOperations.route) },
                onDeferredWork       = { navController.navigate(Screen.KernelDeferredWork.route) },
                onNetlink            = { navController.navigate(Screen.KernelNetlink.route) },
                onNetworking         = { navController.navigate(Screen.KernelNetworking.route) },
                onFileAccess         = { navController.navigate(Screen.KernelFileAccess.route) },
                onLinuxDeviceModel   = { navController.navigate(Screen.LinuxDeviceModel.route) },
                onExportingApi       = { navController.navigate(Screen.ExportingKernelModuleApi.route) },
                onAltModuleComm      = { navController.navigate(Screen.AlternativeModuleComm.route) },
                onNotifierChains     = { navController.navigate(Screen.NotifierChains.route) },
                onErrorInjection     = { navController.navigate(Screen.KernelErrorInjection.route) },
                onKprobes            = { navController.navigate(Screen.KernelKprobes.route) }
            )
        }
        composable(Screen.SysCalls.route) {
            SysCallsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Kallsyms.route) {
            KallsymsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelAddingFiles.route) {
            KernelAddingFilesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelSecurityFeatures.route) {
            SecurityFeaturesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelNetlink.route) {
            NetlinkScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.UsermodeCommunicatingWithKernel.route) {
            UserModeCommunicatingWithKernelScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelNetworking.route) {
            KernelNetworkingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelFileAccess.route) {
            KernelFileAccessScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.LinuxDeviceModel.route) {
            LinuxDeviceModelScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.ExportingKernelModuleApi.route) {
            ExportingKernelModuleApiScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.AlternativeModuleComm.route) {
            AlternativeModuleCommScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.NotifierChains.route) {
            NotifierChainsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelErrorInjection.route) {
            ErrorInjectionScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.KernelKprobes.route) {
            KprobesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfModifyReturn.route) {
            EbpfModifyReturnScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfBpftrace.route) {
            BpftraceScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfPersistency.route) {
            EbpfPersistencyScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfLsm.route) {
            EbpfLsmScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfSamplePrograms.route) {
            EbpfSampleProgramsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfManaging.route) {
            EbpfManagingScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfAboutCilium.route) {
            AboutCiliumScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EbpfBpftraceScriptSyntax.route) {
            BpftraceScriptSyntaxScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Licensing.route) {
            LicensingScreen(onBack = { navController.popBackStack() })
        }
    }
}
