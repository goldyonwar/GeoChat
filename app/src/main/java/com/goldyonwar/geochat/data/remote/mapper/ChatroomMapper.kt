package com.goldyonwar.geochat.data.remote.mapper

import com.goldyonwar.geochat.data.remote.dto.ChatroomDto
import com.goldyonwar.geochat.domain.model.Chatroom

fun ChatroomDto.toDomain() = Chatroom(id, title, description)

fun Chatroom.toDto() = ChatroomDto(id, title, description)
