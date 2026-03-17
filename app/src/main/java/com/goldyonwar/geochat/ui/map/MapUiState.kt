package com.goldyonwar.geochat.ui.map

import com.google.android.gms.maps.model.LatLng

data class MapUiState(
    val userLocation: LatLng? = null,
    val isLocationPermissionGranted: Boolean = false,
    val isLoading: Boolean = false
)