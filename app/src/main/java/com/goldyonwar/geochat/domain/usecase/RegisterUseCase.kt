package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, pass: String, username: String) =
        repository.register(email, pass, username)
}