package com.goldyonwar.geochat.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.goldyonwar.geochat.domain.model.Message

@Composable
fun MessageBubble(message: Message, isMine: Boolean) {
    val alignment = if (isMine) Alignment.CenterEnd else Alignment.CenterStart
    val color = if (isMine) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp), contentAlignment = alignment) {
        Column(horizontalAlignment = if (isMine) Alignment.End else Alignment.Start) {
            Text(text = message.username, style = MaterialTheme.typography.labelSmall)
            Surface(color = color, shape = RoundedCornerShape(12.dp)) {
                Text(text = message.content, modifier = Modifier.padding(12.dp), color = if (isMine) Color.White else Color.Unspecified)
            }
        }
    }
}