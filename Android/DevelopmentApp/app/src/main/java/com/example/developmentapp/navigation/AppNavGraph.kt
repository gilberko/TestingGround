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
import com.example.developmentapp.screens.SplashScreen
import com.example.developmentapp.screens.PythonHubScreen
import com.example.developmentapp.screens.TcpIpHubScreen
import com.example.developmentapp.screens.CppHubScreen
import com.example.developmentapp.screens.StlHubScreen
import com.example.developmentapp.screens.stl.StlAsyncPromiseFutureScreen
import com.example.developmentapp.screens.tcpip.ArpScreen
import com.example.developmentapp.screens.tcpip.DhcpScreen
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
import com.example.developmentapp.screens.cpp.CppClassInheritanceScreen
import com.example.developmentapp.screens.cpp.CppLoopsConditionsScreen
import com.example.developmentapp.screens.cpp.CppReferencesScreen
import com.example.developmentapp.screens.cpp.CppRaiiSmartPtrsScreen
import com.example.developmentapp.screens.cpp.CppTemplatesScreen
import com.example.developmentapp.screens.cpp.CppConstAutoMutableScreen
import com.example.developmentapp.screens.cpp.CppErrorHandlingScreen
import com.example.developmentapp.screens.cpp.CppStlContainersScreen
import com.example.developmentapp.screens.cpp.CppLambdasThreadingScreen
import com.example.developmentapp.screens.algorithms.AlphaBetaPruningScreen
import com.example.developmentapp.screens.algorithms.HeapScreen
import com.example.developmentapp.screens.algorithms.TwoThreeTreesScreen
import com.example.developmentapp.screens.algorithms.LsmBloomFiltersScreen
import com.example.developmentapp.screens.database.WideColumnDbScreen
import com.example.developmentapp.screens.python.PythonCoroutinesScreen
import com.example.developmentapp.screens.python.PythonSwitchCaseScreen
import com.example.developmentapp.screens.python.PythonNetworkingScreen
import com.example.developmentapp.screens.tcpip.ProxyScreen
import com.example.developmentapp.screens.tcpip.VpnScreen
import com.example.developmentapp.screens.assembly.AsmLabelsScreen
import com.example.developmentapp.screens.assembly.AsmMemorySectionsScreen
import com.example.developmentapp.screens.assembly.AsmSpecialCommandsScreen
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
import com.example.developmentapp.screens.tcpip.QuicScreen
import com.example.developmentapp.screens.tcpip.SmtpScreen
import com.example.developmentapp.screens.tcpip.HttpScreen
import com.example.developmentapp.screens.tcpip.FtpScreen
import com.example.developmentapp.screens.tcpip.TftpScreen
import com.example.developmentapp.screens.tcpip.WifiScreen
import com.example.developmentapp.screens.GoHubScreen
import com.example.developmentapp.screens.AiHubScreen
import com.example.developmentapp.screens.go.GoAboutScreen
import com.example.developmentapp.screens.go.GoGettingStartedScreen
import com.example.developmentapp.screens.go.GoDataTypesScreen
import com.example.developmentapp.screens.go.GoLoopingScreen
import com.example.developmentapp.screens.go.GoConditionsScreen
import com.example.developmentapp.screens.go.GoFunctionsGotoScreen
import com.example.developmentapp.screens.go.GoAnonFuncsScreen
import com.example.developmentapp.screens.go.GoStructsArraysSlicesScreen
import com.example.developmentapp.screens.go.GoPointersAddressableScreen
import com.example.developmentapp.screens.go.GoMethodsScreen
import com.example.developmentapp.screens.go.GoPackagesImportsScreen
import com.example.developmentapp.screens.go.GoMapsScreen
import com.example.developmentapp.screens.go.GoGoroutinesSyncScreen
import com.example.developmentapp.screens.go.GoChannelsScreen
import com.example.developmentapp.screens.go.GoNewMakeScreen
import com.example.developmentapp.screens.go.GoErrorHandlingScreen
import com.example.developmentapp.screens.go.GoTypesInterfacesScreen
import com.example.developmentapp.screens.go.GoStandardLibraryScreen
import com.example.developmentapp.screens.stl.StlAtomicMemoryBarriersScreen
import com.example.developmentapp.screens.stl.StlChronoScreen
import com.example.developmentapp.screens.stl.StlFilesystemScreen
import com.example.developmentapp.screens.stl.StlMemoryStringsScreen
import com.example.developmentapp.screens.stl.StlAlgorithmsScreen
import com.example.developmentapp.screens.debugging.DebuggingProfilingTracingScreen
import com.example.developmentapp.screens.ai.PerceptronScreen
import com.example.developmentapp.screens.ai.NeuralNetworksScreen
import com.example.developmentapp.screens.ai.InferenceForwardPropScreen
import com.example.developmentapp.screens.ai.TrainingBackwardPropScreen
import com.example.developmentapp.screens.ai.GanScreen
import com.example.developmentapp.screens.ai.AttentionTransformersScreen
import com.example.developmentapp.screens.ai.AnomalyDetectionScreen
import com.example.developmentapp.screens.python.PythonGeneratorsScreen
import com.example.developmentapp.screens.python.PythonBuiltInVariablesScreen
import com.example.developmentapp.screens.python.PythonUsefulPackagesScreen
import com.example.developmentapp.screens.python.PythonAiFrameworksScreen
import com.example.developmentapp.screens.cpp.CppExpressionTypesCastingScreen
import com.example.developmentapp.screens.DatabasesHubScreen
import com.example.developmentapp.screens.database.DbHistoryScreen
import com.example.developmentapp.screens.database.SqlScreen
import com.example.developmentapp.screens.database.DocumentDbScreen
import com.example.developmentapp.screens.database.GraphDbScreen
import com.example.developmentapp.screens.database.KeyValueStoresScreen
import com.example.developmentapp.screens.JavaHubScreen
import com.example.developmentapp.screens.KotlinHubScreen
import com.example.developmentapp.screens.kotlin.KotlinAboutScreen
import com.example.developmentapp.screens.kotlin.KotlinHelloWorldScreen
import com.example.developmentapp.screens.kotlin.KotlinVariableTypesScreen
import com.example.developmentapp.screens.kotlin.KotlinBasicSyntaxScreen
import com.example.developmentapp.screens.kotlin.KotlinClasses101Screen
import com.example.developmentapp.screens.kotlin.KotlinPackagesScreen
import com.example.developmentapp.screens.kotlin.KotlinClasses2Screen
import com.example.developmentapp.screens.kotlin.KotlinLambdasScreen
import com.example.developmentapp.screens.kotlin.KotlinGenericsScreen
import com.example.developmentapp.screens.kotlin.KotlinCollectionsScreen
import com.example.developmentapp.screens.kotlin.KotlinExceptionsScreen
import com.example.developmentapp.screens.kotlin.KotlinThreadingSyncScreen
import com.example.developmentapp.screens.kotlin.KotlinIOScreen
import com.example.developmentapp.screens.kotlin.KotlinAndroid101Screen
import com.example.developmentapp.screens.kotlin.KotlinNullSafetyScreen
import com.example.developmentapp.screens.kotlin.KotlinCoroutinesScreen
import com.example.developmentapp.screens.kotlin.KotlinDataClassesScreen
import com.example.developmentapp.screens.kotlin.KotlinEnumsScreen
import com.example.developmentapp.screens.kotlin.KotlinReflectionsScreen
import com.example.developmentapp.screens.kotlin.KotlinFunctionsScreen
import com.example.developmentapp.screens.java.JavaAboutScreen
import com.example.developmentapp.screens.java.JavaHelloWorldScreen
import com.example.developmentapp.screens.java.JavaBasicTypesScreen
import com.example.developmentapp.screens.java.JavaConditionalsLoopsScreen
import com.example.developmentapp.screens.java.JavaBasicSyntaxScreen
import com.example.developmentapp.screens.java.JavaClasses101Screen
import com.example.developmentapp.screens.java.JavaPackagesScreen
import com.example.developmentapp.screens.java.JavaClasses2Screen
import com.example.developmentapp.screens.java.JavaInterfacesScreen
import com.example.developmentapp.screens.java.JavaJniScreen
import com.example.developmentapp.screens.java.JavaGenericsScreen
import com.example.developmentapp.screens.java.JavaLambdasScreen
import com.example.developmentapp.screens.java.JavaDataStructuresScreen
import com.example.developmentapp.screens.java.JavaExceptionsScreen
import com.example.developmentapp.screens.java.JavaThreadingSyncScreen
import com.example.developmentapp.screens.java.JavaEnumsScreen
import com.example.developmentapp.screens.java.JavaIOScreen
import com.example.developmentapp.screens.java.JavaAvailableLibrariesScreen
import com.example.developmentapp.screens.java.JavaAwtScreen
import com.example.developmentapp.screens.java.JavaSwingScreen
import com.example.developmentapp.screens.java.JavaReflectionsScreen
import com.example.developmentapp.screens.java.JavaLargeProjectScreen
import com.example.developmentapp.screens.RustHubScreen
import com.example.developmentapp.screens.rust.RustAboutScreen
import com.example.developmentapp.screens.rust.RustHelloWorldScreen
import com.example.developmentapp.screens.rust.RustVariablesScreen
import com.example.developmentapp.screens.rust.RustComparisonsLoopsScreen
import com.example.developmentapp.screens.rust.RustFunctionsScreen
import com.example.developmentapp.screens.rust.RustOwnershipBorrowingScreen
import com.example.developmentapp.screens.rust.RustEnumsStructsScreen
import com.example.developmentapp.screens.rust.RustTraitsScreen
import com.example.developmentapp.screens.rust.RustTuplesArraysSlicesScreen
import com.example.developmentapp.screens.rust.RustVectorsStringsScreen
import com.example.developmentapp.screens.rust.RustModulesCratesScreen
import com.example.developmentapp.screens.rust.RustClosuresScreen
import com.example.developmentapp.screens.rust.RustThreadsScreen
import com.example.developmentapp.screens.rust.RustOptionsResultScreen
import com.example.developmentapp.screens.rust.RustStdLibScreen
import com.example.developmentapp.screens.rust.RustCommentsDocsScreen
import com.example.developmentapp.screens.rust.RustUnsafeScreen

sealed class Screen(val route: String) {
    object Splash              : Screen("splash")
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
    object AsmMemorySections   : Screen("asm_memory_sections")
    object AsmLabels           : Screen("asm_labels")
    object AsmSpecialCommands  : Screen("asm_special_commands")
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
    object PythonSwitchCase    : Screen("python_switch_case")
    object PythonGenerators       : Screen("python_generators")
    object PythonBuiltInVariables : Screen("python_built_in_variables")
    object PythonUsefulPackages   : Screen("python_useful_packages")
    object PythonAiFrameworks     : Screen("python_ai_frameworks")
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
    object CppClassInheritance       : Screen("cpp_class_inheritance")
    object CppLoopsConditions        : Screen("cpp_loops_conditions")
    object CppReferences             : Screen("cpp_references")
    object CppRaiiSmartPtrs          : Screen("cpp_raii_smart_ptrs")
    object CppTemplates              : Screen("cpp_templates")
    object CppConstAutoMutable       : Screen("cpp_const_auto_mutable")
    object CppErrorHandling          : Screen("cpp_error_handling")
    object CppStlContainers          : Screen("cpp_stl_containers")
    object CppLambdasThreading           : Screen("cpp_lambdas_threading")
    object CppExpressionTypesCasting     : Screen("cpp_expression_types_casting")
    object StlHub                        : Screen("stl_hub")
    object StlAsyncPromiseFuture         : Screen("stl_async_promise_future")
    object StlAtomicMemoryBarriers       : Screen("stl_atomic_memory_barriers")
    object StlChrono                     : Screen("stl_chrono")
    object StlFilesystem                 : Screen("stl_filesystem")
    object StlMemoryStrings              : Screen("stl_memory_strings")
    object StlAlgorithms                 : Screen("stl_algorithms")
    // Algorithms
    object AlgorithmsHub        : Screen("algorithms_hub")
    object BasicGraphAlgorithms : Screen("basic_graph_algorithms")
    object MoreGraphAlgorithms  : Screen("more_graph_algorithms")
    object SortingAndLookup     : Screen("sorting_and_lookup")
    object FourierTransform     : Screen("fourier_transform")
    object AlphaBetaPruning     : Screen("alpha_beta_pruning")
    object Heap                 : Screen("heap")
    object TwoThreeTrees        : Screen("two_three_trees")
    object LsmBloomFilters      : Screen("lsm_bloom_filters")
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
    object Arp                 : Screen("arp")
    object Dhcp                : Screen("dhcp")
    object Quic                : Screen("quic")
    object Smtp                : Screen("smtp")
    object Http                : Screen("http")
    object Ftp                 : Screen("ftp")
    object Tftp                : Screen("tftp")
    object Wifi                : Screen("wifi")
    // Go
    object DebuggingProfilingTracing : Screen("debugging_profiling_tracing")
    // Go
    object GoHub              : Screen("go_hub")
    object GoAbout            : Screen("go_about")
    object GoGettingStarted   : Screen("go_getting_started")
    object GoDataTypes        : Screen("go_data_types")
    object GoLooping          : Screen("go_looping")
    object GoConditions          : Screen("go_conditions")
    object GoFunctionsGoto       : Screen("go_functions_goto")
    object GoAnonFuncs           : Screen("go_anon_funcs")
    object GoStructsArraysSlices : Screen("go_structs_arrays_slices")
    object GoPointersAddressable : Screen("go_pointers_addressable")
    object GoMethods             : Screen("go_methods")
    object GoPackagesImports     : Screen("go_packages_imports")
    object GoMaps                : Screen("go_maps")
    object GoGoroutinesSync      : Screen("go_goroutines_sync")
    object GoChannels            : Screen("go_channels")
    object GoNewMake             : Screen("go_new_make")
    object GoErrorHandling       : Screen("go_error_handling")
    object GoTypesInterfaces     : Screen("go_types_interfaces")
    object GoStandardLibrary     : Screen("go_standard_library")
    // Rust
    object RustHub               : Screen("rust_hub")
    object RustAbout             : Screen("rust_about")
    object RustHelloWorld        : Screen("rust_hello_world")
    object RustVariables         : Screen("rust_variables")
    object RustComparisonsLoops  : Screen("rust_comparisons_loops")
    object RustFunctions         : Screen("rust_functions")
    object RustOwnershipBorrowing: Screen("rust_ownership_borrowing")
    object RustEnumsStructs      : Screen("rust_enums_structs")
    object RustTraits            : Screen("rust_traits")
    object RustTuplesArraysSlices: Screen("rust_tuples_arrays_slices")
    object RustVectorsStrings    : Screen("rust_vectors_strings")
    object RustModulesCrates     : Screen("rust_modules_crates")
    object RustClosures          : Screen("rust_closures")
    object RustOptionsResult     : Screen("rust_options_result")
    object RustStdLib            : Screen("rust_std_lib")
    object RustCommentsDocs      : Screen("rust_comments_docs")
    object RustThreads           : Screen("rust_threads")
    object RustUnsafe            : Screen("rust_unsafe")
    // Kotlin
    object KotlinHub           : Screen("kotlin_hub")
    object KotlinAbout         : Screen("kotlin_about")
    object KotlinHelloWorld    : Screen("kotlin_hello_world")
    object KotlinVariableTypes : Screen("kotlin_variable_types")
    object KotlinBasicSyntax   : Screen("kotlin_basic_syntax")
    object KotlinClasses101    : Screen("kotlin_classes_101")
    object KotlinPackages      : Screen("kotlin_packages")
    object KotlinClasses2      : Screen("kotlin_classes_2")
    object KotlinLambdas       : Screen("kotlin_lambdas")
    object KotlinGenerics      : Screen("kotlin_generics")
    object KotlinCollections   : Screen("kotlin_collections")
    object KotlinExceptions    : Screen("kotlin_exceptions")
    object KotlinThreadingSync : Screen("kotlin_threading_sync")
    object KotlinIO            : Screen("kotlin_io")
    object KotlinAndroid101    : Screen("kotlin_android_101")
    object KotlinNullSafety    : Screen("kotlin_null_safety")
    object KotlinCoroutines    : Screen("kotlin_coroutines")
    object KotlinDataClasses   : Screen("kotlin_data_classes")
    object KotlinEnums         : Screen("kotlin_enums")
    object KotlinReflections   : Screen("kotlin_reflections")
    object KotlinMoreFunctions : Screen("kotlin_more_functions")
    // Java
    object JavaHub        : Screen("java_hub")
    object JavaAbout      : Screen("java_about")
    object JavaHelloWorld : Screen("java_hello_world")
    object JavaBasicTypes : Screen("java_basic_types")
    object JavaCondLoops  : Screen("java_cond_loops")
    object JavaBasicSyntax : Screen("java_basic_syntax")
    object JavaClasses101  : Screen("java_classes_101")
    object JavaPackages    : Screen("java_packages")
    object JavaClasses2    : Screen("java_classes_2")
    object JavaInterfaces  : Screen("java_interfaces")
    object JavaJni         : Screen("java_jni")
    object JavaGenerics       : Screen("java_generics")
    object JavaLambdas        : Screen("java_lambdas")
    object JavaDataStructures : Screen("java_data_structures")
    object JavaExceptions     : Screen("java_exceptions")
    object JavaThreadingSync  : Screen("java_threading_sync")
    object JavaEnums             : Screen("java_enums")
    object JavaIO                : Screen("java_io")
    object JavaAvailableLibraries: Screen("java_available_libraries")
    object JavaAwt               : Screen("java_awt")
    object JavaSwing             : Screen("java_swing")
    object JavaReflections       : Screen("java_reflections")
    object JavaLargeProject      : Screen("java_large_project")
    // AI and Neural Networks
    object AiHub                 : Screen("ai_hub")
    object Perceptron            : Screen("perceptron")
    object NeuralNetworks        : Screen("neural_networks")
    object InferenceForwardProp  : Screen("inference_forward_prop")
    object TrainingBackwardProp  : Screen("training_backward_prop")
    object GAN                   : Screen("gan")
    object AttentionTransformers : Screen("attention_transformers")
    object AnomalyDetection      : Screen("anomaly_detection")
    // Databases
    object DatabasesHub     : Screen("databases_hub")
    object DbHistory        : Screen("db_history")
    object DbSql            : Screen("db_sql")
    object DbDocumentDb     : Screen("db_document_db")
    object DbGraphDb        : Screen("db_graph_db")
    object DbKeyValueStores : Screen("db_key_value_stores")
    object DbWideColumn     : Screen("db_wide_column")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onCpp            = { navController.navigate(Screen.CppHub.route) },
                onRust           = { navController.navigate(Screen.RustHub.route) },
                onGo             = { navController.navigate(Screen.GoHub.route) },
                onPython         = { navController.navigate(Screen.PythonHub.route) },
                onAssembly       = { navController.navigate(Screen.AssemblyHub.route) },
                onDataStructures             = { navController.navigate(Screen.DataStructures.route) },
                onAlgorithms                 = { navController.navigate(Screen.AlgorithmsHub.route) },
                onTcpIp                      = { navController.navigate(Screen.TcpIpHub.route) },
                onDebuggingProfilingTracing  = { navController.navigate(Screen.DebuggingProfilingTracing.route) },
                onAiNeuralNetworks           = { navController.navigate(Screen.AiHub.route) },
                onJava                       = { navController.navigate(Screen.JavaHub.route) },
                onDatabases                  = { navController.navigate(Screen.DatabasesHub.route) },
                onKotlin                     = { navController.navigate(Screen.KotlinHub.route) }
            )
        }

        composable(
            route = Screen.ComingSoon.route,
            arguments = listOf(navArgument("label") { type = NavType.StringType })
        ) {
            ComingSoonScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.DebuggingProfilingTracing.route) {
            DebuggingProfilingTracingScreen(onBack = { navController.popBackStack() })
        }

        // ── Kotlin ───────────────────────────────────────────────────────
        composable(Screen.KotlinHub.route) {
            KotlinHubScreen(
                onBack           = { navController.popBackStack() },
                onAboutKotlin    = { navController.navigate(Screen.KotlinAbout.route) },
                onHelloWorld     = { navController.navigate(Screen.KotlinHelloWorld.route) },
                onVariableTypes  = { navController.navigate(Screen.KotlinVariableTypes.route) },
                onBasicSyntax    = { navController.navigate(Screen.KotlinBasicSyntax.route) },
                onClasses101     = { navController.navigate(Screen.KotlinClasses101.route) },
                onPackages       = { navController.navigate(Screen.KotlinPackages.route) },
                onClasses2       = { navController.navigate(Screen.KotlinClasses2.route) },
                onLambdas        = { navController.navigate(Screen.KotlinLambdas.route) },
                onGenerics       = { navController.navigate(Screen.KotlinGenerics.route) },
                onCollections    = { navController.navigate(Screen.KotlinCollections.route) },
                onExceptions     = { navController.navigate(Screen.KotlinExceptions.route) },
                onThreadingSync  = { navController.navigate(Screen.KotlinThreadingSync.route) },
                onAndroid101     = { navController.navigate(Screen.KotlinAndroid101.route) },
                onIO             = { navController.navigate(Screen.KotlinIO.route) },
                onNullSafety     = { navController.navigate(Screen.KotlinNullSafety.route) },
                onCoroutines     = { navController.navigate(Screen.KotlinCoroutines.route) },
                onDataClasses    = { navController.navigate(Screen.KotlinDataClasses.route) },
                onEnums          = { navController.navigate(Screen.KotlinEnums.route) },
                onReflections    = { navController.navigate(Screen.KotlinReflections.route) },
                onMoreFunctions  = { navController.navigate(Screen.KotlinMoreFunctions.route) }
            )
        }
        composable(Screen.KotlinAbout.route)         { KotlinAboutScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinHelloWorld.route)    { KotlinHelloWorldScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinVariableTypes.route) { KotlinVariableTypesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinBasicSyntax.route)   { KotlinBasicSyntaxScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinClasses101.route)    { KotlinClasses101Screen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinPackages.route)      { KotlinPackagesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinClasses2.route)      { KotlinClasses2Screen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinLambdas.route)       { KotlinLambdasScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinGenerics.route)      { KotlinGenericsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinCollections.route)   { KotlinCollectionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinExceptions.route)    { KotlinExceptionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinThreadingSync.route) { KotlinThreadingSyncScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinIO.route)            { KotlinIOScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinAndroid101.route)    { KotlinAndroid101Screen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinNullSafety.route)    { KotlinNullSafetyScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinCoroutines.route)    { KotlinCoroutinesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinDataClasses.route)   { KotlinDataClassesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinEnums.route)         { KotlinEnumsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinReflections.route)   { KotlinReflectionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.KotlinMoreFunctions.route) { KotlinFunctionsScreen(onBack = { navController.popBackStack() }) }

        // ── Java ─────────────────────────────────────────────────────────
        composable(Screen.JavaHub.route) {
            JavaHubScreen(
                onBack        = { navController.popBackStack() },
                onAboutJava   = { navController.navigate(Screen.JavaAbout.route) },
                onHelloWorld  = { navController.navigate(Screen.JavaHelloWorld.route) },
                onBasicTypes  = { navController.navigate(Screen.JavaBasicTypes.route) },
                onCondLoops   = { navController.navigate(Screen.JavaCondLoops.route) },
                onBasicSyntax = { navController.navigate(Screen.JavaBasicSyntax.route) },
                onClasses101  = { navController.navigate(Screen.JavaClasses101.route) },
                onPackages    = { navController.navigate(Screen.JavaPackages.route) },
                onClasses2    = { navController.navigate(Screen.JavaClasses2.route) },
                onInterfaces  = { navController.navigate(Screen.JavaInterfaces.route) },
                onJni         = { navController.navigate(Screen.JavaJni.route) },
                onGenerics        = { navController.navigate(Screen.JavaGenerics.route) },
                onLambdas         = { navController.navigate(Screen.JavaLambdas.route) },
                onDataStructures  = { navController.navigate(Screen.JavaDataStructures.route) },
                onExceptions      = { navController.navigate(Screen.JavaExceptions.route) },
                onThreadingSync   = { navController.navigate(Screen.JavaThreadingSync.route) },
                onEnums               = { navController.navigate(Screen.JavaEnums.route) },
                onIO                  = { navController.navigate(Screen.JavaIO.route) },
                onAvailableLibraries  = { navController.navigate(Screen.JavaAvailableLibraries.route) },
                onAwt                 = { navController.navigate(Screen.JavaAwt.route) },
                onSwing               = { navController.navigate(Screen.JavaSwing.route) },
                onReflections         = { navController.navigate(Screen.JavaReflections.route) },
                onLargeProject        = { navController.navigate(Screen.JavaLargeProject.route) }
            )
        }
        composable(Screen.JavaAbout.route)      { JavaAboutScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaHelloWorld.route) { JavaHelloWorldScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaBasicTypes.route) { JavaBasicTypesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaCondLoops.route)   { JavaConditionalsLoopsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaBasicSyntax.route) { JavaBasicSyntaxScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaClasses101.route)  { JavaClasses101Screen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaPackages.route)    { JavaPackagesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaClasses2.route)    { JavaClasses2Screen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaInterfaces.route)  { JavaInterfacesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaJni.route)         { JavaJniScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaGenerics.route)       { JavaGenericsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaLambdas.route)        { JavaLambdasScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaDataStructures.route) { JavaDataStructuresScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaExceptions.route)     { JavaExceptionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaThreadingSync.route)  { JavaThreadingSyncScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaEnums.route)             { JavaEnumsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaIO.route)                { JavaIOScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaAvailableLibraries.route){ JavaAvailableLibrariesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaAwt.route)               { JavaAwtScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaSwing.route)             { JavaSwingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaReflections.route)       { JavaReflectionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.JavaLargeProject.route)      { JavaLargeProjectScreen(onBack = { navController.popBackStack() }) }

        // ── Go ────────────────────────────────────────────────────────────
        composable(Screen.GoHub.route) {
            GoHubScreen(
                onBack           = { navController.popBackStack() },
                onAboutGo        = { navController.navigate(Screen.GoAbout.route) },
                onGettingStarted = { navController.navigate(Screen.GoGettingStarted.route) },
                onDataTypes      = { navController.navigate(Screen.GoDataTypes.route) },
                onLooping        = { navController.navigate(Screen.GoLooping.route) },
                onConditions          = { navController.navigate(Screen.GoConditions.route) },
                onFunctionsGoto       = { navController.navigate(Screen.GoFunctionsGoto.route) },
                onAnonFuncs           = { navController.navigate(Screen.GoAnonFuncs.route) },
                onStructsArraysSlices = { navController.navigate(Screen.GoStructsArraysSlices.route) },
                onPointersAddressable = { navController.navigate(Screen.GoPointersAddressable.route) },
                onMethods             = { navController.navigate(Screen.GoMethods.route) },
                onPackagesImports     = { navController.navigate(Screen.GoPackagesImports.route) },
                onMaps                = { navController.navigate(Screen.GoMaps.route) },
                onGoroutinesSync      = { navController.navigate(Screen.GoGoroutinesSync.route) },
                onChannels            = { navController.navigate(Screen.GoChannels.route) },
                onNewMake             = { navController.navigate(Screen.GoNewMake.route) },
                onErrorHandling       = { navController.navigate(Screen.GoErrorHandling.route) },
                onTypesInterfaces     = { navController.navigate(Screen.GoTypesInterfaces.route) },
                onStandardLibrary     = { navController.navigate(Screen.GoStandardLibrary.route) }
            )
        }
        composable(Screen.GoAbout.route)          { GoAboutScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoGettingStarted.route) { GoGettingStartedScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoDataTypes.route)      { GoDataTypesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoLooping.route)        { GoLoopingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoConditions.route)     { GoConditionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoFunctionsGoto.route)       { GoFunctionsGotoScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoAnonFuncs.route)           { GoAnonFuncsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoStructsArraysSlices.route) { GoStructsArraysSlicesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoPointersAddressable.route) { GoPointersAddressableScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoMethods.route)             { GoMethodsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoPackagesImports.route)     { GoPackagesImportsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoMaps.route)                { GoMapsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoGoroutinesSync.route)      { GoGoroutinesSyncScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoChannels.route)            { GoChannelsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoNewMake.route)             { GoNewMakeScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoErrorHandling.route)       { GoErrorHandlingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoTypesInterfaces.route)     { GoTypesInterfacesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GoStandardLibrary.route)     { GoStandardLibraryScreen(onBack = { navController.popBackStack() }) }

        // ── Rust ──────────────────────────────────────────────────────────
        composable(Screen.RustHub.route) {
            RustHubScreen(
                onBack                = { navController.popBackStack() },
                onAboutRust           = { navController.navigate(Screen.RustAbout.route) },
                onHelloWorld          = { navController.navigate(Screen.RustHelloWorld.route) },
                onVariables           = { navController.navigate(Screen.RustVariables.route) },
                onComparisonsLoops    = { navController.navigate(Screen.RustComparisonsLoops.route) },
                onFunctions           = { navController.navigate(Screen.RustFunctions.route) },
                onOwnershipBorrowing  = { navController.navigate(Screen.RustOwnershipBorrowing.route) },
                onEnumsStructs        = { navController.navigate(Screen.RustEnumsStructs.route) },
                onTraits              = { navController.navigate(Screen.RustTraits.route) },
                onTuplesArraysSlices  = { navController.navigate(Screen.RustTuplesArraysSlices.route) },
                onVectorsStrings      = { navController.navigate(Screen.RustVectorsStrings.route) },
                onModulesCrates       = { navController.navigate(Screen.RustModulesCrates.route) },
                onClosures            = { navController.navigate(Screen.RustClosures.route) },
                onOptionsResult       = { navController.navigate(Screen.RustOptionsResult.route) },
                onStdLib              = { navController.navigate(Screen.RustStdLib.route) },
                onCommentsDocs        = { navController.navigate(Screen.RustCommentsDocs.route) },
                onThreads             = { navController.navigate(Screen.RustThreads.route) },
                onUnsafe              = { navController.navigate(Screen.RustUnsafe.route) }
            )
        }
        composable(Screen.RustAbout.route)             { RustAboutScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustHelloWorld.route)        { RustHelloWorldScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustVariables.route)         { RustVariablesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustComparisonsLoops.route)  { RustComparisonsLoopsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustFunctions.route)         { RustFunctionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustOwnershipBorrowing.route){ RustOwnershipBorrowingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustEnumsStructs.route)      { RustEnumsStructsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustTraits.route)            { RustTraitsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustTuplesArraysSlices.route){ RustTuplesArraysSlicesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustVectorsStrings.route)    { RustVectorsStringsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustModulesCrates.route)     { RustModulesCratesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustClosures.route)          { RustClosuresScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustOptionsResult.route)     { RustOptionsResultScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustStdLib.route)            { RustStdLibScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustCommentsDocs.route)      { RustCommentsDocsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustThreads.route)           { RustThreadsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.RustUnsafe.route)            { RustUnsafeScreen(onBack = { navController.popBackStack() }) }

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
                onOperatorOverloading = { navController.navigate(Screen.CppOperatorOverloading.route) },
                onClassInheritance    = { navController.navigate(Screen.CppClassInheritance.route) },
                onLoopsConditions     = { navController.navigate(Screen.CppLoopsConditions.route) },
                onReferences          = { navController.navigate(Screen.CppReferences.route) },
                onRaiiSmartPtrs       = { navController.navigate(Screen.CppRaiiSmartPtrs.route) },
                onTemplates           = { navController.navigate(Screen.CppTemplates.route) },
                onConstAutoMutable    = { navController.navigate(Screen.CppConstAutoMutable.route) },
                onErrorHandling       = { navController.navigate(Screen.CppErrorHandling.route) },
                onStl                    = { navController.navigate(Screen.StlHub.route) },
                onExpressionTypesCasting = { navController.navigate(Screen.CppExpressionTypesCasting.route) }
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
        composable(Screen.CppClassInheritance.route)    { CppClassInheritanceScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppLoopsConditions.route)    { CppLoopsConditionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppReferences.route)         { CppReferencesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppRaiiSmartPtrs.route)      { CppRaiiSmartPtrsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppTemplates.route)          { CppTemplatesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppConstAutoMutable.route)   { CppConstAutoMutableScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppErrorHandling.route)      { CppErrorHandlingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppStlContainers.route)      { CppStlContainersScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppLambdasThreading.route)       { CppLambdasThreadingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CppExpressionTypesCasting.route) { CppExpressionTypesCastingScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.StlHub.route) {
            StlHubScreen(
                onBack                 = { navController.popBackStack() },
                onStlContainers        = { navController.navigate(Screen.CppStlContainers.route) },
                onAsyncPromiseFuture   = { navController.navigate(Screen.StlAsyncPromiseFuture.route) },
                onLambdasThreading     = { navController.navigate(Screen.CppLambdasThreading.route) },
                onAtomicMemoryBarriers = { navController.navigate(Screen.StlAtomicMemoryBarriers.route) },
                onChrono               = { navController.navigate(Screen.StlChrono.route) },
                onFilesystem           = { navController.navigate(Screen.StlFilesystem.route) },
                onMemoryStrings        = { navController.navigate(Screen.StlMemoryStrings.route) },
                onAlgorithms           = { navController.navigate(Screen.StlAlgorithms.route) }
            )
        }
        composable(Screen.StlAsyncPromiseFuture.route)     { StlAsyncPromiseFutureScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.StlAtomicMemoryBarriers.route)   { StlAtomicMemoryBarriersScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.StlChrono.route)                 { StlChronoScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.StlFilesystem.route)             { StlFilesystemScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.StlMemoryStrings.route)          { StlMemoryStringsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.StlAlgorithms.route)             { StlAlgorithmsScreen(onBack = { navController.popBackStack() }) }

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
                onNumberRepresentation = { navController.navigate(Screen.NumberRepresentation.route) },
                onMemorySections       = { navController.navigate(Screen.AsmMemorySections.route) },
                onLabels               = { navController.navigate(Screen.AsmLabels.route) },
                onSpecialCommands      = { navController.navigate(Screen.AsmSpecialCommands.route) }
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
        composable(Screen.AsmMemorySections.route)   { AsmMemorySectionsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AsmLabels.route)           { AsmLabelsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AsmSpecialCommands.route)  { AsmSpecialCommandsScreen(onBack = { navController.popBackStack() }) }

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
                onCoroutines        = { navController.navigate(Screen.PythonCoroutines.route) },
                onSwitchCase        = { navController.navigate(Screen.PythonSwitchCase.route) },
                onBuiltInVariables  = { navController.navigate(Screen.PythonBuiltInVariables.route) },
                onUsefulPackages    = { navController.navigate(Screen.PythonUsefulPackages.route) },
                onAiFrameworks      = { navController.navigate(Screen.PythonAiFrameworks.route) },
                onGenerators        = { navController.navigate(Screen.PythonGenerators.route) }
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
        composable(Screen.PythonSwitchCase.route)    { PythonSwitchCaseScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonGenerators.route)       { PythonGeneratorsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonBuiltInVariables.route) { PythonBuiltInVariablesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonUsefulPackages.route)   { PythonUsefulPackagesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PythonAiFrameworks.route)     { PythonAiFrameworksScreen(onBack = { navController.popBackStack() }) }

        // ── Algorithms ────────────────────────────────────────────────
        composable(Screen.AlgorithmsHub.route) {
            AlgorithmsHubScreen(
                onBack                  = { navController.popBackStack() },
                onBasicGraphAlgorithms  = { navController.navigate(Screen.BasicGraphAlgorithms.route) },
                onMoreGraphAlgorithms   = { navController.navigate(Screen.MoreGraphAlgorithms.route) },
                onSortingAndLookup      = { navController.navigate(Screen.SortingAndLookup.route) },
                onFourierTransform      = { navController.navigate(Screen.FourierTransform.route) },
                onAlphaBetaPruning      = { navController.navigate(Screen.AlphaBetaPruning.route) },
                onHeap                  = { navController.navigate(Screen.Heap.route) },
                onTwoThreeTrees         = { navController.navigate(Screen.TwoThreeTrees.route) },
                onLsmBloomFilters       = { navController.navigate(Screen.LsmBloomFilters.route) }
            )
        }
        composable(Screen.BasicGraphAlgorithms.route) { BasicGraphAlgorithmsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.MoreGraphAlgorithms.route)  { MoreGraphAlgorithmsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.SortingAndLookup.route)     { SortingAndLookupScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.FourierTransform.route)     { FourierTransformScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AlphaBetaPruning.route)     { AlphaBetaPruningScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Heap.route)                 { HeapScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.TwoThreeTrees.route)        { TwoThreeTreesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.LsmBloomFilters.route)      { LsmBloomFiltersScreen(onBack = { navController.popBackStack() }) }

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
                onProxy     = { navController.navigate(Screen.Proxy.route) },
                onArp       = { navController.navigate(Screen.Arp.route) },
                onDhcp      = { navController.navigate(Screen.Dhcp.route) },
                onQuic      = { navController.navigate(Screen.Quic.route) },
                onSmtp      = { navController.navigate(Screen.Smtp.route) },
                onHttp      = { navController.navigate(Screen.Http.route) },
                onFtp       = { navController.navigate(Screen.Ftp.route) },
                onTftp      = { navController.navigate(Screen.Tftp.route) },
                onWifi      = { navController.navigate(Screen.Wifi.route) }
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
        composable(Screen.Arp.route)      { ArpScreen(onBack  = { navController.popBackStack() }) }
        composable(Screen.Dhcp.route)     { DhcpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Quic.route)     { QuicScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Smtp.route)     { SmtpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Http.route)     { HttpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Ftp.route)      { FtpScreen(onBack  = { navController.popBackStack() }) }
        composable(Screen.Tftp.route)     { TftpScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Wifi.route)     { WifiScreen(onBack = { navController.popBackStack() }) }

        // ── AI and Neural Networks ────────────────────────────────────
        composable(Screen.AiHub.route) {
            AiHubScreen(
                onBack                 = { navController.popBackStack() },
                onPerceptron           = { navController.navigate(Screen.Perceptron.route) },
                onNeuralNetworks       = { navController.navigate(Screen.NeuralNetworks.route) },
                onInferenceForwardProp = { navController.navigate(Screen.InferenceForwardProp.route) },
                onTrainingBackwardProp = { navController.navigate(Screen.TrainingBackwardProp.route) },
                onGAN                  = { navController.navigate(Screen.GAN.route) },
                onAttentionTransformers = { navController.navigate(Screen.AttentionTransformers.route) },
                onAnomalyDetection      = { navController.navigate(Screen.AnomalyDetection.route) }
            )
        }
        composable(Screen.Perceptron.route)            { PerceptronScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.NeuralNetworks.route)        { NeuralNetworksScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.InferenceForwardProp.route)  { InferenceForwardPropScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.TrainingBackwardProp.route)  { TrainingBackwardPropScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.GAN.route)                   { GanScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AttentionTransformers.route) { AttentionTransformersScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AnomalyDetection.route)      { AnomalyDetectionScreen(onBack = { navController.popBackStack() }) }

        // ── Databases ─────────────────────────────────────────────────────
        composable(Screen.DatabasesHub.route) {
            DatabasesHubScreen(
                onBack           = { navController.popBackStack() },
                onDbHistory      = { navController.navigate(Screen.DbHistory.route) },
                onSql            = { navController.navigate(Screen.DbSql.route) },
                onDocumentDb     = { navController.navigate(Screen.DbDocumentDb.route) },
                onGraphDb        = { navController.navigate(Screen.DbGraphDb.route) },
                onKeyValueStores = { navController.navigate(Screen.DbKeyValueStores.route) },
                onWideColumnDb   = { navController.navigate(Screen.DbWideColumn.route) }
            )
        }
        composable(Screen.DbHistory.route)        { DbHistoryScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.DbSql.route)            { SqlScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.DbDocumentDb.route)     { DocumentDbScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.DbGraphDb.route)        { GraphDbScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.DbKeyValueStores.route) { KeyValueStoresScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.DbWideColumn.route)     { WideColumnDbScreen(onBack = { navController.popBackStack() }) }
    }
}
