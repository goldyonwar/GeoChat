package com.goldyonwar.geochat.ui.map

import com.goldyonwar.geochat.domain.model.User
import com.google.android.gms.maps.model.LatLng

data class MapUiState(
    val userLocation: LatLng? = null,
    val otherUsers: List<User> = emptyList(), // For displaying other markers
    val selectedRoute: List<LatLng>? = null,   // Decoded points
    val isLocationPermissionGranted: Boolean = false,
    val isLoading: Boolean = false
)