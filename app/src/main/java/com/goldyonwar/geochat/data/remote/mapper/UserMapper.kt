package com.goldyonwar.geochat.data.remote.mapper

import com.goldyonwar.geochat.data.remote.dto.UserDto
import com.goldyonwar.geochat.domain.model.User
import com.goldyonwar.geochat.domain.model.UserLocation

fun UserDto.toDomain() = User(
    id = id,
    email = email,
    username = username,
    avatar = avatar,
    location = UserLocation(latitude, longitude)
)

fun UserLocation.toDtoMap() = mapOf(
    "latitude" to latitude,
    "longitude" to longitude,
    "timestamp" to timestamp
)