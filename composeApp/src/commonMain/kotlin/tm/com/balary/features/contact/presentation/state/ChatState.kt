package tm.com.balary.features.contact.presentation.state

import tm.com.balary.features.contact.domain.model.ChatModel

data class ChatState(
    val loading: Boolean = true,
    val error: String? = null,
    val chats: List<ChatModel>? = null
)
