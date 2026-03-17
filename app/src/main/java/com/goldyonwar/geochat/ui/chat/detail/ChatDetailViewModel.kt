package com.goldyonwar.geochat.ui.chat.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldyonwar.geochat.domain.usecase.GetMessageUseCase
import com.goldyonwar.geochat.domain.usecase.SendMessageUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private val getMessageUseCase: GetMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val auth: FirebaseAuth,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val chatroomId: String = checkNotNull(savedStateHandle["id"])

    val state: StateFlow<ChatDetailUiState> = getMessageUseCase(chatroomId)
        .map { megs ->
            ChatDetailUiState(
                messages = megs,
                currentUserId = auth.currentUser?.uid ?: ""
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ChatDetailUiState(isLoading = true)
        )

    fun onSendMessage(content: String) = viewModelScope.launch {
        sendMessageUseCase(chatroomId, content)
    }
}