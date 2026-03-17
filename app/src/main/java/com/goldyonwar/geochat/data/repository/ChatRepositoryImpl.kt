package com.goldyonwar.geochat.data.repository

import com.goldyonwar.geochat.data.remote.dto.MessageDto
import com.goldyonwar.geochat.data.remote.dto.UserDto
import com.goldyonwar.geochat.data.remote.mapper.toDomain
import com.goldyonwar.geochat.domain.model.Chatroom
import com.goldyonwar.geochat.domain.model.Message
import com.goldyonwar.geochat.domain.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ChatRepository {

    override suspend fun createChatroom(chatRoom: Chatroom) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("Chatrooms").document(uid).set(chatRoom).await()
    }

    override fun getChatRooms(): Flow<List<Chatroom>> = callbackFlow {
        val listener = firestore.collection("Chatrooms")
            .addSnapshotListener { snapshot, _ ->
                val rooms = snapshot?.toObjects(Chatroom::class.java) ?: emptyList()
                trySend(rooms)
            }
        awaitClose { listener.remove() }
    }

    override fun getMessages(chatroomId: String): Flow<List<Message>> = callbackFlow {
        val listener = firestore.collection("Chatrooms").document(chatroomId)
            .collection("Messages").orderBy("timestamp")
            .addSnapshotListener { snapshot, _ ->
                val megs = snapshot?.mapNotNull { it.toObject(MessageDto::class.java).toDomain() }
                    ?: emptyList()
                trySend(megs)
            }
        awaitClose { listener.remove() }
    }

    override suspend fun sendMessage(chatroomId: String, content: String) {
        val uid = auth.currentUser?.uid ?: return
        // Fetch current user for avatar/username
        val user =
            firestore.collection("Users").document(uid).get().await().toObject(UserDto::class.java)
        val msgDto = MessageDto(
            userId = uid,
            username = user?.username ?: "Unknown",
            userAvatar = user?.avatar ?: "gyw_logo",
            content = content,
            timestamp = System.currentTimeMillis()
        )
        firestore.collection("Chatrooms").document(chatroomId).collection("Messages").add(msgDto)
            .await()
    }
}