package tm.com.balary.features.contact.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.core.Resource
import tm.com.balary.features.contact.domain.usecase.ChatUseCase
import tm.com.balary.features.contact.presentation.state.ChatState

class ChatViewModel(
    private val useCase: ChatUseCase
) : ViewModel() {
    private val _chat = MutableStateFlow(ChatState())
    val chat = _chat.asStateFlow()

    fun getChats() {
        viewModelScope.launch {
            useCase.getChats().onEach {
                when(it) {
                    is Resource.Error -> {
                        _chat.value = _chat.value.copy(
                            loading = false,
                            error = it.message,
                            chats = it.data
                        )
                    }
                    is Resource.Loading -> {
                        _chat.value = _chat.value.copy(
                            loading = true,
                            error = it.message,
                            chats = it.data
                        )
                    }
                    is Resource.Success -> {
                        _chat.value = _chat.value.copy(
                            loading = false,
                            error = it.message,
                            chats = it.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}