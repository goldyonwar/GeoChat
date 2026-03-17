package com.goldyonwar.geochat.data.remote.dto

data class MessageDto(
    val id: String = "",
    val userId: String = "",
    val username: String = "",
    val userAvatar: String = "",
    val content: String = "",
    val timestamp: Long = 0L
)