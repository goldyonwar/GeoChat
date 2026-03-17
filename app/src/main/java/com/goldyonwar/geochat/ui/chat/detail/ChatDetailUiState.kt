package com.goldyonwar.geochat.ui.chat.detail

import com.goldyonwar.geochat.domain.model.Message

data class ChatDetailUiState(
    val messages: List<Message> = emptyList(),
    val currentUserId: String = "",
    val isLoading: Boolean = false
)