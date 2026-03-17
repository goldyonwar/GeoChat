package com.goldyonwar.geochat.domain.repository

interface AuthRepository {

    fun isUserLoggedIn(): Boolean

    suspend fun longin(email: String, pass: String): Result<Unit>

    suspend fun register(email: String, pass: String, username: String): Result<Unit>

    fun logout()

}