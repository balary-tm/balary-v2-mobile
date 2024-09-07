package tm.com.balary.features.contact.domain.usecase

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.contact.domain.model.ChatModel
import tm.com.balary.features.contact.domain.repository.ChatRepository

class ChatUseCase(
    private val repository: ChatRepository
) {
    suspend fun getChats(): Flow<Resource<List<ChatModel>>> {
        return repository.getChats()
    }
}