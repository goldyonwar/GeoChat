package com.goldyonwar.geochat.data.remote.mapper

import com.goldyonwar.geochat.data.remote.dto.MessageDto
import com.goldyonwar.geochat.domain.model.Message

fun MessageDto.toDomain() = Message(id, userId, username, userAvatar, content, timestamp)

fun Message.toDto() = MessageDto(id, userId, username, userAvatar, content, timestamp)