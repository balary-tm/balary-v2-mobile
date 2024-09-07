package tm.com.balary.features.contact.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.contact.domain.model.ChatModel

interface ChatRepository {
    suspend fun getChats(): Flow<Resource<List<ChatModel>>>
}