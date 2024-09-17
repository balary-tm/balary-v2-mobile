package tm.com.balary.features.contact.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Resource
import tm.com.balary.features.contact.domain.model.ChatModel
import tm.com.balary.features.contact.domain.model.MessageType
import tm.com.balary.features.contact.domain.repository.ChatRepository

class ChatRepositoryImpl : ChatRepository {
    override suspend fun getChats(): Flow<Resource<List<ChatModel>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(
                data = List(100) { index->
                    ChatModel(
                        id = index.toString(),
                        message = if(index%5==0) "" else "Salam, men siziň söwda kömekçiňiz.\n" +
                                "Men size nädip kömek edip bilerin? ",
                        date = "${index/5}.09.2024",
                        time = "12.00",
                        isOwn = index%3 == 0,
                        messageType = MessageType.entries.random()
                    )
                }
            ))
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}