package com.example.windowsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.windowsapp.ui.theme.WindowsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowsAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("kernel") { KernelProgrammingScreen(navController) }
                    composable("user_mode") { UserModeProgrammingScreen(navController) }
                    composable("advanced") { AdvancedTopicsScreen(navController) }
                    composable("kernel_data_structures") { InternalDataStructuresScreen(navController) }
                    composable("kernel_driver_object") { DriverObjectScreen(navController) }
                    composable("kernel_device_object") { DeviceObjectScreen(navController) }
                    composable("kernel_irp") { IrpScreen(navController) }
                    composable("kernel_mdl") { MdlScreen(navController) }
                    composable("kernel_nbl") { NetBufferListScreen(navController) }
                    composable("kernel_ioctl") { IoctlScreen(navController) }
                    composable("advanced_system_services") { SystemServicesScreen(navController) }
                    composable("kernel_processes_threads") { ProcessesAndThreadsScreen(navController) }
                    composable("kernel_nt_zw") { NtZwScreen(navController) }
                    composable("kernel_debugging") { KernelDebuggingScreen(navController) }
                    composable("kernel_simple_driver") { SimpleDriverScreen(navController) }
                    composable("advanced_rpc_wmi") { RpcWmiScreen(navController) }
                    composable("kernel_pnp") { PlugAndPlayScreen(navController) }
                    composable("user_writing_dll") { WritingADllScreen(navController) }
                    composable("advanced_pe_file") { PeFileScreen(navController) }
                    composable("kernel_callbacks") { RegisteringForCallbacksScreen(navController) }
                    composable("irql") { IrqlScreen(navController) }
                    composable("user_com") { ComScreen(navController) }
                    composable("how_a_debugger_works") { HowADebuggerWorksScreen(navController) }
                    composable("user_communicating_drivers") { CommunicatingWithDeviceDriversScreen(navController) }
                    composable("kernel_filter_drivers") { FilterDriversScreen(navController) }
                    composable("kernel_symbolic_links") { SymbolicLinksScreen(navController) }
                    composable("kernel_objects_overview") { ObjectsOverviewScreen(navController) }
                    composable("kernel_threads") { ThreadsScreen(navController) }
                    composable("kernel_synchronization") { SynchronizationScreen(navController) }
                    composable("kernel_interrupt_handling") { InterruptHandlingScreen(navController) }
                    composable("kernel_wfp") { WFPHubScreen(navController) }
                    composable("kernel_wfp_overview") { WFPOverviewScreen(navController) }
                    composable("kernel_wfp_sublayers") { WFPSublayersScreen(navController) }
                    composable("kernel_wfp_classify_notify") { WFPClassifyAndNotifyScreen(navController) }
                    composable("kernel_wfp_flows") { WFPFlowsScreen(navController) }
                    composable("kernel_wfp_ale_layers") { WFPALELayersScreen(navController) }
                    composable("kernel_fs_minifilters") { FileSystemMinifiltersScreen(navController) }
                    composable("user_ipc") { IPCScreen(navController) }
                    composable("user_threading_sync") { UserThreadingAndSyncScreen(navController) }
                    composable("user_winsock") { WinSockScreen(navController) }
                    composable("user_writing_service") { WritingAServiceScreen(navController) }
                    composable("advanced_access_tokens") { AccessTokensAndImpersonationScreen(navController) }
                    composable("kernel_apc") { APCScreen(navController) }
                    composable("kernel_memory_allocation") { MemoryAllocationScreen(navController) }
                    composable("kernel_bugchecks") { BugChecksAndDriverVerifierScreen(navController) }
                    composable("advanced_memory_paging") { MemoryAndPagingScreen(navController) }
                    composable("advanced_dlls") { DLLsScreen(navController) }
                    composable("user_io_completion_port") { IOCompletionPortScreen(navController) }
                    composable("user_ui_programming") { UIProgrammingScreen(navController) }
                    composable("user_memory_allocation") { UserMemoryAllocationScreen(navController) }
                    composable("kernel_fsmf_history") { FSMFHistoryScreen(navController) }
                    composable("kernel_fsmf_altitude") { FSMFAltitudeScreen(navController) }
                    composable("kernel_fsmf_basic") { FSMFBasicScreen(navController) }
                    composable("kernel_fsmf_pre_post") { FSMFPrePostScreen(navController) }
                    composable("kernel_fsmf_raw_disk") { FSMFRawDiskScreen(navController) }
                    composable("kernel_fsmf_named_pipes") { FSMFNamedPipesScreen(navController) }
                    composable("kernel_fsmf_mup") { FSMFMupScreen(navController) }
                    composable("kernel_fsmf_reparse_point") { FSMFReparsePointScreen(navController) }
                    composable("kernel_wfp_raw_sockets") { WFPRawSocketsScreen(navController) }
                    composable("user_calling_conventions") { CallingConventionsScreen(navController) }
                    composable("advanced_oplocks") { OpportunisticLocksScreen(navController) }
                    composable("kernel_ndis_lwf") { NdisLightweightFilterScreen(navController) }
                    composable("advanced_registry") { RegistryScreen(navController) }
                    composable("advanced_paging_io") { PagingIOScreen(navController) }
                    composable("advanced_lsass") { LsassScreen(navController) }
                    composable("advanced_werfault") { WerFaultScreen(navController) }
                    composable("kernel_wsk") { WinSockKernelScreen(navController) }
                    composable("kernel_seh") { KernelSehScreen(navController) }
                }
            }
        }
    }
}
