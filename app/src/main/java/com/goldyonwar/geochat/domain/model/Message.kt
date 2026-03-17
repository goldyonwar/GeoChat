package com.goldyonwar.geochat.domain.model

data class Message(
    val id: String = "",
    val userId: String = "",
    val username: String = "",
    val userAvatar: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
