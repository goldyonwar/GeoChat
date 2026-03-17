package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    val repository: ChatRepository
) {
    suspend operator fun invoke(chatroomId: String, content: String) {
        if (content.isNotBlank()) {
            repository.sendMessage(chatroomId, content)
        }
    }

}