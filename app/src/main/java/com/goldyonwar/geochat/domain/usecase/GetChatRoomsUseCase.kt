package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatRoomsUseCase @Inject constructor(
    val repository: ChatRepository
) {
    operator fun invoke() = repository.getChatRooms()
}