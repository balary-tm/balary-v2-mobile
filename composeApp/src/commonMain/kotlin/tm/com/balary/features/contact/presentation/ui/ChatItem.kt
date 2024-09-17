package tm.com.balary.features.contact.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tm.com.balary.features.contact.domain.model.ChatModel
import tm.com.balary.features.contact.domain.model.MessageType
import tm.com.balary.ui.ImageLoader

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    lastItem: ChatModel? = null,
    item: ChatModel,
) {
    var alignment: Alignment = if (item.isOwn) Alignment.TopEnd else Alignment.TopStart
    lastItem?.let {
        if (lastItem.date != item.date) {
            alignment = Alignment.Center
        }
    }
    Box(modifier = modifier, contentAlignment = alignment) {
        if (alignment == Alignment.Center) {
            Text(
                item.date,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.surfaceDim,
                    shape = RoundedCornerShape(10.dp)
                ).padding(horizontal = 22.dp, vertical = 4.dp)
            )
        }

        if (alignment == Alignment.TopStart) {
            Column (
                Modifier.background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp
                    )
                ).padding(8.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Message(
                    message = item.message,
                    messageType = item.messageType
                )

                Text(
                    item.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )

            }
        }

        if (alignment == Alignment.TopEnd) {
            Column(
                Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                ).padding(8.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Message(
                    message = item.message,
                    messageType = item.messageType
                )

                Text(
                    item.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )

            }
        }
    }
}

@Composable
fun Message(modifier: Modifier = Modifier, message: String, messageType: MessageType) {
    when (messageType) {
        MessageType.TEXT -> {
            Text(
                modifier = modifier,
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        MessageType.IMAGE -> {
            ImageLoader(
                modifier = modifier.size(200.dp),
                url = message,
                contentScale = ContentScale.Fit
            )
        }
        MessageType.EMOJI -> {
            Text(
                modifier = modifier,
                text = "\uD83D\uDE0A",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 30.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        MessageType.URL -> {
            Text(
                modifier = modifier.clickable {

                },
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}