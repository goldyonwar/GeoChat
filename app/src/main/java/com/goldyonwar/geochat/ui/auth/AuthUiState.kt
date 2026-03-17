package com.goldyonwar.geochat.ui.auth

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

sealed class AuthEvent {
    data class OnLogin(val email: String, val pass: String) : AuthEvent()
    data class OnRegister(val email: String, val pass: String, val username: String) : AuthEvent()
    data object ClearError : AuthEvent()
}