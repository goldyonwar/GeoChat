package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.model.UserLocation
import com.goldyonwar.geochat.domain.repository.DirectionsRepository
import javax.inject.Inject

class GetDirectionUseCase @Inject constructor(
    val repository: DirectionsRepository
) {
    suspend operator fun invoke(origin: UserLocation, destination: UserLocation) =
        repository.getDirections(origin, destination)
}