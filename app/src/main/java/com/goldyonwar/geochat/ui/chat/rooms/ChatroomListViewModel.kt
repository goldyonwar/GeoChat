package com.goldyonwar.geochat.ui.chat.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldyonwar.geochat.domain.model.Chatroom
import com.goldyonwar.geochat.domain.usecase.CreateChatRoomUseCase
import com.goldyonwar.geochat.domain.usecase.GetChatRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatroomListViewModel @Inject constructor(
    private val getChatRoomsUseCase: GetChatRoomsUseCase,
    private val createChatroomUseCase: CreateChatRoomUseCase
) : ViewModel() {

    val state: StateFlow<ChatroomUiState> = getChatRoomsUseCase()
        .map { ChatroomUiState(chatRooms = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ChatroomUiState(isLoading = true)
        )

    fun createChatroom(title: String, description: String) = viewModelScope.launch {
        val id = UUID.randomUUID().toString()
        val newRoom = Chatroom(id = id, title = title, description = description)
        createChatroomUseCase(newRoom)
    }

}