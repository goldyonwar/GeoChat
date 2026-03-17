package com.goldyonwar.geochat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.goldyonwar.geochat.ui.auth.LoginScreen
import com.goldyonwar.geochat.ui.auth.RegisterScreen
import com.goldyonwar.geochat.ui.chat.rooms.ChatroomListScreen
import com.goldyonwar.geochat.ui.chat.detail.ChatDetailScreen
import com.goldyonwar.geochat.ui.map.MapScreen
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object ChatRooms : Screen

    @Serializable
    data class Chatroom(val id: String) : Screen
    @Serializable
    data class Map(val chatroomId: String) : Screen
}

@Composable
fun AppNavHost() {
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

        composable<Screen.ChatRooms> {
            ChatroomListScreen(
                onNavigateToChat = { id ->
                    navController.navigate(Screen.Chatroom(id))
                }
            )
        }

        composable<Screen.Chatroom> {backStackEntry ->
            val chat: Screen.Chatroom = backStackEntry.toRoute()
            ChatDetailScreen(
                onNavigateToMap = { navController.navigate(Screen.Map(chat.id)) },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<Screen.Map> { backStackEntry ->
            val map: Screen.Map = backStackEntry.toRoute()
            MapScreen(chatroomId = map.chatroomId)
        }
    }
}