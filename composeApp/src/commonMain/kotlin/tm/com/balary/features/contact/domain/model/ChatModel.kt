package tm.com.balary.features.contact.domain.model

enum class MessageType {
    TEXT,
    IMAGE,
    EMOJI,
    URL
}

data class ChatModel(
    val id: String,
    val message: String,
    val messageType: MessageType,
    val date: String,
    val time: String,
    val isOwn: Boolean
)
