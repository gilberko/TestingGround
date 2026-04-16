package com.example.developmentapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.developmentapp.screens.AlgorithmsHubScreen
import com.example.developmentapp.screens.AssemblyHubScreen
import com.example.developmentapp.screens.ComingSoonScreen
import com.example.developmentapp.screens.DataStructuresScreen
import com.example.developmentapp.screens.HomeScreen
import com.example.developmentapp.screens.PythonHubScreen
import com.example.developmentapp.screens.TcpIpHubScreen
import com.example.developmentapp.screens.CppHubScreen
import com.example.developmentapp.screens.algorithms.BasicGraphAlgorithmsScreen
import com.example.developmentapp.screens.algorithms.FourierTransformScreen
import com.example.developmentapp.screens.algorithms.MoreGraphAlgorithmsScreen
import com.example.developmentapp.screens.algorithms.SortingAndLookupScreen
import com.example.developmentapp.screens.cpp.CompilationLinkingLoadingScreen
import com.example.developmentapp.screens.cpp.CppMemoryAllocationsScreen
import com.example.developmentapp.screens.cpp.CppMoreVariableTypesScreen
import com.example.developmentapp.screens.cpp.CppPlusPlus101Screen
import com.example.developmentapp.screens.cpp.CppPreProcessorScreen
import com.example.developmentapp.screens.cpp.CppKeywordsScreen
import com.example.developmentapp.screens.cpp.CppOperatorOverloadingScreen
import com.example.developmentapp.screens.cpp.CppQuirksScreen
import com.example.developmentapp.screens.cpp.CppRuntimeMemoryScreen
import com.example.developmentapp.screens.cpp.CppStdioScreen
import com.example.developmentapp.screens.cpp.CppSyntaxScreen
import com.example.developmentapp.screens.python.PythonCoroutinesScreen
import com.example.developmentapp.screens.python.PythonNetworkingScreen
import com.example.developmentapp.screens.tcpip.ProxyScreen
import com.example.developmentapp.screens.tcpip.VpnScreen
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
import com.example.developmentapp.screens.python.PythonThreadsScreen
import com.example.developmentapp.screens.python.PythonVariablesScreen
import com.example.developmentapp.screens.tcpip.DnsScreen
import com.example.developmentapp.screens.tcpip.EthernetScreen
import com.example.developmentapp.screens.tcpip.IcmpScreen
import com.example.developmentapp.screens.tcpip.IpScreen
import com.example.developmentapp.screens.tcpip.IpsecScreen
import com.example.developmentapp.screens.tcpip.SslTlsScreen
import com.example.developmentapp.screens.tcpip.TcpScreen
import com.example.developmentapp.screens.tcpip.UdpScreen

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
    object PythonThreads       : Screen("python_threads")
    object PythonNetworking    : Screen("python_networking")
    object PythonCoroutines    : Screen("python_coroutines")
    // C/C++
    object CppHub                    : Screen("cpp_hub")
    object CppSyntax                 : Screen("cpp_syntax")
    object CppMoreVariableTypes      : Screen("cpp_more_variable_types")
    object CppPreProcessor           : Screen("cpp_preprocessor")
    object CppMemoryAllocations      : Screen("cpp_memory_allocations")
    object CppCompilation            : Screen("cpp_compilation")
    object CppPlusPlus101            : Screen("cpp_plus_plus_101")
    object CppQuirks                 : Screen("cpp_quirks")
    object CppStdio                  : Screen("cpp_stdio")
    object CppKeywords               : Screen("cpp_keywords")
    object CppRuntimeMemory          : Screen("cpp_runtime_memory")
    object CppOperatorOverloading    : Screen("cpp_operator_overloading")
    // Algorithms
    object AlgorithmsHub        : Screen("algorithms_hub")
    object BasicGraphAlgorithms : Screen("basic_graph_algorithms")
    object MoreGraphAlgorithms  : Screen("more_graph_algorithms")
    object SortingAndLookup     : Screen("sorting_and_lookup")
    object FourierTransform     : Screen("fourier_transform")
    // TCP/IP
    object TcpIpHub            : Screen("tcpip_hub")
    object Ethernet            : Screen("ethernet")
    object Ip                  : Screen("ip")
    object Icmp                : Screen("icmp")
    object Udp                 : Screen("udp")
    object Tcp                 : Screen("tcp")
    object Dns                 : Screen("dns")
    object Ipsec               : Screen("ipsec")
    object SslTls              : Screen("ssl_tls")
    object Vpn                 : Screen("vpn")
    object Proxy               : Screen("proxy")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(
                onCpp            = { navController.navigate(Screen.CppHub.route) },
                onRust           = { navController.navigate(Screen.ComingSoon.withLabel("Rust")) },
                onGo             = { navController.navigate(Screen.ComingSoon.withLabel("Go")) },
                onPython         = { navController.navigate(Screen.PythonHub.route) },
                onAssembly       = { navController.navigate(Screen.AssemblyHub.route) },
                onDataStructures = { navController.navigate(Screen.DataStructures.route) },
                onAlgorithms     = { navController.navigate(Screen.AlgorithmsHub.route) },
                onTcpIp          = { navController.navigate(Screen.TcpIpHub.route) }
            )
        }

        composable(
            route = Screen.ComingSoon.route,
            arguments = listOf(navArgument("label") { type = NavType.StringType })
        ) {
            ComingSoonScreen(onBack = { navController.popBackStack() })
        }

        // ── C/C++ ─────────────────────────────────────────────────────────
        composable(Screen.CppHub.route) {
            CppHubScreen(
                onBack                = { navController.popBackStack() },
                onSyntax              = { navController.navigate(Screen.CppSyntax.route) },
                onMoreVariableTypes   = { navController.navigate(Screen.CppMoreVariableTypes.route) },
                onCPreProcessor       = { navController.navigate(Screen.CppPreProcessor.route) },
                onMemoryAllocations   = { navController.navigate(Screen.CppMemoryAllocations.route) },
                onCompilation         = { navController.navigate(Screen.CppCompilation.route) },
                onPlusPlus101         = { navController.navigate(Screen.CppPlusPlus101.route) },
                onQuirks              = { navController.navigate(Screen.CppQuirks.route) },
                onStdio               = { navController.navigate(Screen.CppStdio.route) },
                onKeywords            = { navController.navigate(Screen.CppKeywords.route) },
                onRuntimeMemory       = { navController.navigate(Screen.CppRuntimeMemory.route) },
                onOperatorOverloading = { navController.navigate(Screen.CppOperatorOverloading.route) }
            )
        }
        composable(Screen.CppSyntax.route)           { CppSyntaxScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppMoreVariableTypes.route) { CppMoreVariableTypesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppPreProcessor.route)     { CppPreProcessorScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppMemoryAllocations.route) { CppMemoryAllocationsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppCompilation.route)      { CompilationLinkingLoadingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppPlusPlus101.route)      { CppPlusPlus101Screen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppQuirks.route)           { CppQuirksScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppStdio.route)             { CppStdioScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppKeywords.route)          { CppKeywordsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppRuntimeMemory.route)     { CppRuntimeMemoryScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppOperatorOverloading.route) { CppOperatorOverloadingScreen(onBack = { navController.popBackStack() }) }

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
                onInputOutput    = { navController.navigate(Screen.PythonInputOutput.route) },
                onThreads        = { navController.navigate(Screen.PythonThreads.route) },
                onNetworking     = { navController.navigate(Screen.PythonNetworking.route) },
                onCoroutines     = { navController.navigate(Screen.PythonCoroutines.route) }
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
        composable(Screen.PythonThreads.route)       { PythonThreadsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonNetworking.route)    { PythonNetworkingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonCoroutines.route)    { PythonCoroutinesScreen(onBack = { navController.popBackStack() }) }

        // ── Algorithms ────────────────────────────────────────────────
        composable(Screen.AlgorithmsHub.route) {
            AlgorithmsHubScreen(
                onBack                  = { navController.popBackStack() },
                onBasicGraphAlgorithms  = { navController.navigate(Screen.BasicGraphAlgorithms.route) },
                onMoreGraphAlgorithms   = { navController.navigate(Screen.MoreGraphAlgorithms.route) },
                onSortingAndLookup      = { navController.navigate(Screen.SortingAndLookup.route) },
                onFourierTransform      = { navController.navigate(Screen.FourierTransform.route) }
            )
        }
        composable(Screen.BasicGraphAlgorithms.route) { BasicGraphAlgorithmsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.MoreGraphAlgorithms.route)  { MoreGraphAlgorithmsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.SortingAndLookup.route)     { SortingAndLookupScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.FourierTransform.route)     { FourierTransformScreen(onBack = { navController.popBackStack() }) }

        // ── TCP/IP ────────────────────────────────────────────────────
        composable(Screen.TcpIpHub.route) {
            TcpIpHubScreen(
                onBack      = { navController.popBackStack() },
                onEthernet  = { navController.navigate(Screen.Ethernet.route) },
                onIp        = { navController.navigate(Screen.Ip.route) },
                onIcmp      = { navController.navigate(Screen.Icmp.route) },
                onUdp       = { navController.navigate(Screen.Udp.route) },
                onTcp       = { navController.navigate(Screen.Tcp.route) },
                onDns       = { navController.navigate(Screen.Dns.route) },
                onIpsec     = { navController.navigate(Screen.Ipsec.route) },
                onSslTls    = { navController.navigate(Screen.SslTls.route) },
                onVpn       = { navController.navigate(Screen.Vpn.route) },
                onProxy     = { navController.navigate(Screen.Proxy.route) }
            )
        }
        composable(Screen.Ethernet.route) { EthernetScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Ip.route)       { IpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Icmp.route)     { IcmpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Udp.route)      { UdpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Tcp.route)      { TcpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Dns.route)      { DnsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Ipsec.route)    { IpsecScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.SslTls.route)   { SslTlsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Vpn.route)      { VpnScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Proxy.route)    { ProxyScreen(onBack = { navController.popBackStack() }) }
    }
}
