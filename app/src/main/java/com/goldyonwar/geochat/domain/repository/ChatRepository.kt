package com.goldyonwar.geochat.domain.repository

import com.goldyonwar.geochat.domain.model.Chatroom
import com.goldyonwar.geochat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun createChatroom(chatRoom: Chatroom)

    fun getChatRooms(): Flow<List<Chatroom>>

    fun getMessages(chatroomId: String): Flow<List<Message>>

    suspend fun sendMessage(chatroomId: String, content: String)
}