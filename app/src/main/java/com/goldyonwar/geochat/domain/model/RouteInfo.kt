package com.goldyonwar.geochat.domain.model

import com.google.maps.model.Distance
import com.google.maps.model.Duration

data class RouteInfo(
    val distance: String,
    val duration: String,
    val polylinePoints: String // Encoded string from Google Maps
)
