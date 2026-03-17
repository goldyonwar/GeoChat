package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.model.UserLocation
import com.goldyonwar.geochat.domain.repository.UserRepository
import javax.inject.Inject

class UpdateLocationUseCase @Inject constructor(
    val repository: UserRepository
) {
    suspend operator fun invoke(location: UserLocation) = repository.updateLocation(location)
}