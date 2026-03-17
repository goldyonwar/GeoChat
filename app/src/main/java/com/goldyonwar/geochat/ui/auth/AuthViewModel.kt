package com.goldyonwar.geochat.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldyonwar.geochat.domain.usecase.LoginUseCase
import com.goldyonwar.geochat.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnLogin -> login(event.email, event.pass)
            is AuthEvent.OnRegister -> register(event.email, event.pass, event.username)
            AuthEvent.ClearError -> _state.update { it.copy(error = null) }
        }
    }

    private fun login(email: String, pass: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        loginUseCase(email, pass)
            .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
            .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
    }

    private fun register(email: String, pass: String, username: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        registerUseCase(email, pass, username)
            .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
            .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
    }
}