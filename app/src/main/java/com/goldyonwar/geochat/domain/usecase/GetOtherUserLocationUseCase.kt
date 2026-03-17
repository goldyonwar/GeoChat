package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.repository.UserRepository
import javax.inject.Inject

class GetOtherUserLocationUseCase @Inject constructor(
    val repository: UserRepository
) {
    operator fun invoke(chatroomId: String) = repository.getOtherUsersLocations(chatroomId)
}