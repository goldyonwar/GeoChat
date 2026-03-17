package com.goldyonwar.geochat.data.repository

import com.goldyonwar.geochat.domain.model.RouteInfo
import com.goldyonwar.geochat.domain.model.UserLocation
import com.goldyonwar.geochat.domain.repository.DirectionsRepository
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.model.LatLng as GoogleLatLng
import javax.inject.Inject

class DirectionsRepositoryImpl @Inject constructor(
    private val geoApiContext: GeoApiContext
) : DirectionsRepository {

    override suspend fun getDirections(origin: UserLocation, destination: UserLocation) =
        runCatching {
            val result = DirectionsApi.newRequest(geoApiContext)
                .origin(GoogleLatLng(origin.latitude, origin.longitude))
                .destination(
                    GoogleLatLng(
                        destination.latitude,
                        destination.longitude
                    )
                )
                .await()

            val route = result.routes[0]
            RouteInfo(
                distance = route.legs[0].distance.humanReadable,
                duration = route.legs[0].duration.humanReadable,
                polylinePoints = route.overviewPolyline.encodedPath
            )
        }
}