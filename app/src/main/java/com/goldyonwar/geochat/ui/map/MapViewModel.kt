package com.goldyonwar.geochat.ui.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldyonwar.geochat.domain.repository.UserRepository
import com.goldyonwar.geochat.service.LocationService
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MapUiState())
    val state = _state.asStateFlow()

    fun onPermissionResult(isGranted: Boolean) {
        _state.update { it.copy(isLocationPermissionGranted = isGranted) }
        if (isGranted) {
            startTrackingLocation()
        }
    }

    fun toggleTracking(context: Context, shouldTrack: Boolean) {
        val intent = Intent(context, LocationService::class.java)
        if (shouldTrack) {
            val hasPermission = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (hasPermission) {
                context.startForegroundService(intent)
            } else {
                // request permission from your Activity/Fragment instead
                Log.w("LocationService", "Location permission not granted")
            }
        } else {
            context.stopService(intent)
        }
    }

    private fun startTrackingLocation() = viewModelScope.launch {
        userRepository.getCurrentUser().collect { user ->
            user?.location?.let { loc ->
                _state.update { it.copy(userLocation = LatLng(loc.latitude, loc.longitude)) }
            }
        }
    }
}