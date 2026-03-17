package com.goldyonwar.geochat.ui.chat.rooms

import com.goldyonwar.geochat.domain.model.Chatroom

data class ChatroomUiState(
    val chatRooms: List<Chatroom> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)