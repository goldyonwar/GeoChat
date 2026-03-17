package com.goldyonwar.geochat.data.repository

import com.goldyonwar.geochat.data.remote.dto.UserDto
import com.goldyonwar.geochat.data.remote.mapper.toDomain
import com.goldyonwar.geochat.data.remote.mapper.toDtoMap
import com.goldyonwar.geochat.domain.model.User
import com.goldyonwar.geochat.domain.model.UserLocation
import com.goldyonwar.geochat.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    override fun getCurrentUser(): Flow<User?> = callbackFlow {
        val uid = auth.currentUser?.uid ?: return@callbackFlow
        val listener = firestore.collection("Users").document(uid)
            .addSnapshotListener { snapshot, _ ->
                trySend(snapshot?.toObject(UserDto::class.java)?.toDomain())
            }
        awaitClose { listener.remove() }
    }

    override suspend fun updateLocation(location: UserLocation) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("Users").document(uid)
            .update(location.toDtoMap()).await()
    }

    override fun getOtherUsersLocations(chatroomId: String): Flow<List<User>> = callbackFlow {
        // In this app, we get all users for simplicity
        val listener = firestore.collection("Users")
            .addSnapshotListener { snapshot, _ ->
                val users = snapshot?.mapNotNull {
                    it.toObject(UserDto::class.java).toDomain()
                }?.filter { it.id != auth.currentUser?.uid } ?: emptyList()
                trySend(users)
            }
        awaitClose { listener.remove() }
    }
}