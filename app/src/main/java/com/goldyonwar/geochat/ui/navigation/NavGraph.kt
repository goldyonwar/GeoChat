package com.goldyonwar.geochat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goldyonwar.geochat.ui.auth.LoginScreen
import com.goldyonwar.geochat.ui.auth.RegisterScreen
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Login : Screen()
    @Serializable
    data object Register : Screen()
    @Serializable
    data object ChatRooms : Screen()
    @Serializable
    data class Chatroom(val id: String) : Screen()
}

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login
    ) {

        composable<Screen.Login> {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register) },
                onLoginSuccess = {
                    navController.navigate(Screen.ChatRooms) {
                        popUpTo<Screen.Login> { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.Register> {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Screen.ChatRooms) {
                        popUpTo<Screen.Login> { inclusive = true }
                    }
                }
            )
        }
    }
}