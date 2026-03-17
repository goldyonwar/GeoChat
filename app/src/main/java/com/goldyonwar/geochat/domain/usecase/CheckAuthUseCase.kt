package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.repository.AuthRepository
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    val repository: AuthRepository
){
    operator fun invoke() = repository.isUserLoggedIn()
}