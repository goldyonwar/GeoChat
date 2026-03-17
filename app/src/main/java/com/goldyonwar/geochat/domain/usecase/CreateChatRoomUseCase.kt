package com.goldyonwar.geochat.domain.usecase

import com.goldyonwar.geochat.domain.model.Chatroom
import com.goldyonwar.geochat.domain.repository.ChatRepository
import javax.inject.Inject

class CreateChatRoomUseCase @Inject constructor(
    private val chatRoomRepository: ChatRepository
) {
    suspend operator fun invoke(chatRoom: Chatroom) = chatRoomRepository.createChatroom(chatRoom)
}