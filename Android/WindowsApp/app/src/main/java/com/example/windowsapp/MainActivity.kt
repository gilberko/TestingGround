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
                }
            }
        }
    }
}
