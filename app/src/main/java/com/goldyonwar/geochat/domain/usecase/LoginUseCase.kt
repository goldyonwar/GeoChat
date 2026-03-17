package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, pass: String) = repository.longin(email, pass)

}