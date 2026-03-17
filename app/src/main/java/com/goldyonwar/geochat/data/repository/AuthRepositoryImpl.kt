package com.goldyonwar.geochat.data.repository

import com.goldyonwar.geochat.data.remote.dto.UserDto
import com.goldyonwar.geochat.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun isUserLoggedIn() = auth.currentUser != null

    override suspend fun longin(
        email: String,
        pass: String
    ): Result<Unit> = runCatching {
        auth.signInWithEmailAndPassword(email, pass).await()
    }


    override suspend fun register(email: String, pass: String, username: String): Result<Unit> =
        runCatching {
            val result = auth.createUserWithEmailAndPassword(email, pass).await()
            val userId = result.user?.uid ?: throw Exception("Auth Failed")
            val userDto = UserDto(id = userId, email = email, username = username)
            firestore.collection("Users").document(userId).set(userDto).await()
        }

    override fun logout() = auth.signOut()
}