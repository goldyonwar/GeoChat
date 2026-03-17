package com.goldyonwar.geochat.domain.repository

import com.goldyonwar.geochat.domain.model.User
import com.goldyonwar.geochat.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getCurrentUser(): Flow<User?>

    suspend fun updateLocation(location: UserLocation)

    fun getOtherUsersLocations(chatroomId: String): Flow<List<User>>

}