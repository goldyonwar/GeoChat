package com.goldyonwar.geochat.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.*
import com.goldyonwar.geochat.ui.components.RequestLocationPermission
import com.google.android.gms.maps.model.JointType

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
    chatroomId: String
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val cameraPositionState = rememberCameraPositionState()
    val context = LocalContext.current
    RequestLocationPermission(
        onPermissionGranted = { viewModel.onPermissionResult(context,true) },
        onPermissionDenied = { viewModel.onPermissionResult(context,false) }
    )
    LaunchedEffect(state.userLocation) {
        state.userLocation?.let {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(it, 15f)
            )
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = state.isLocationPermissionGranted
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = false
            )
        ){
            state.selectedRoute?.let { points ->
                Polyline(
                    points = points,
                    color = MaterialTheme.colorScheme.primary,
                    width = 12f,
                    jointType = JointType.ROUND
                )
            }
        }
    }
}