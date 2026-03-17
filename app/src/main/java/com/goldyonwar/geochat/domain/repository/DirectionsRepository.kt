package com.goldyonwar.geochat.domain.repository

import com.goldyonwar.geochat.domain.model.RouteInfo
import com.goldyonwar.geochat.domain.model.UserLocation

interface DirectionsRepository {

    suspend fun getDirections(origin: UserLocation, destination: UserLocation): Result<RouteInfo>

}